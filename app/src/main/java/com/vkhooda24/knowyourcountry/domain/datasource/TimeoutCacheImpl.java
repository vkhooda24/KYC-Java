package com.vkhooda24.knowyourcountry.domain.datasource;

import android.util.Log;
import io.reactivex.Observable;

import java.util.concurrent.*;

/**
 * Created by Vikram Hooda on 07-21-2019.
 * Reference: Douglas Schmidt , POSA-15 assignment
 */
class TimeoutCacheImpl implements TimeoutCache {

    private static final String TAG = TimeoutCacheImpl.class.getSimpleName();

    //Default key, if key not present, value stores with this key
    private final String DEFAULT_VALUE = "cacheDefaultValue";

    //Default cache time-to-alive
    private static int TIMEOUT = 30;

    //Default time out and time unit if not provided explicitly in data source
    private final long timeout;
    private final TimeUnit timeUnit;

    //Thread-safe hash map
    private ConcurrentHashMap<Object, CacheData> concurrentHashMapCache = new ConcurrentHashMap<>();

    //Scheduled executor service to run a runnable after certain time, pool size 1
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /*
     * TimeoutCacheImpl constructor where added a shutdown hook
     * @param timeout
     * @param timeUnit
     * */
    TimeoutCacheImpl(long timeout, TimeUnit timeUnit) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;

        //Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                try {
                    if (executorService.awaitTermination(TIMEOUT * 4, TimeUnit.SECONDS)) {
                        executorService.shutdown();
                    }
                } catch (InterruptedException exception) {
                    executorService.shutdown();
                    Log.e(TAG, "AddShutdownHook InterruptedException:", exception);
                } catch (Exception e) {
                    Log.e(TAG, "AddShutdownHook Exception:", e);
                }

            }
        });
    }

    @Override
    public Observable<Object> get(Object ky) {

        //Check for key value and if key null then uses the default key to get value if any present
        Object key = ky == null ? DEFAULT_VALUE : ky;

        //Get Cache data object for given key
        CacheData cacheData = concurrentHashMapCache.get(key);

        //If cache data object not exist of given key, returns empty observable otherwise observable with actual value.
        return cacheData != null ? Observable.just(cacheData.value) : Observable.empty();
    }

    @Override
    public void put(Object key, Object value) {
        put(key, value, timeout);
    }

    @Override
    public void put(Object key, Object value, long timeout) {

        //Check for key. If null key then uses default key
        Object dataKey = key == null ? DEFAULT_VALUE : key;
        CacheData cacheData = new CacheData(value);

        //Runnable which will execute to clear the cache for scheduled future on cache object
        Runnable clearCacheDataRunnable = () -> concurrentHashMapCache.remove(key);

        //Creating a Scheduled future with given timeout and time unit which will run the runnable 'clearCacheDataRunnable' after given time elapses
        ScheduledFuture<?> future = executorService.schedule(clearCacheDataRunnable, timeout, timeUnit);

        //set the scheduled future on cache data object to clean up data for given key after given time elapses
        cacheData.setFuture(future);

        //Return old cache data object after putting new cache data object
        CacheData prevCacheValues = concurrentHashMapCache.put(key, cacheData);

        if (prevCacheValues != null) {
            prevCacheValues.scheduledFuture.cancel(true);
        }

        Log.d(TAG, "Data updated successfully for key: " + dataKey.toString());
    }

    @Override
    public void clear() {
        cancelFuture();
        concurrentHashMapCache.clear();
    }


    private void cancelFuture() {

        for (CacheData cacheData : concurrentHashMapCache.values()) {
            if (cacheData.scheduledFuture != null) {
                cacheData.scheduledFuture.cancel(true);
            }
        }
    }

    /*
     * Cache data model class which ScheduledFuture and value entity.
     * Set future on each CacheData to remove value for key after certain time period.
     * */
    class CacheData {

        //Cache value to store
        private final Object value;

        //Runnable that has been scheduled to clean up the value after certain time period elapses.
        private ScheduledFuture<?> scheduledFuture = null;

        CacheData(Object value) {
            this.value = value;
        }

        //Setter to schedule a future on cache data object
        void setFuture(ScheduledFuture<?> future) {
            scheduledFuture = future;
        }
    }
}
