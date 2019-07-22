package com.vkhooda24.knowyourcountry.domain.datasource;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
public class AppDataSourceImpl implements AppDataSource {

    private static final String TAG = AppDataSourceImpl.class.getSimpleName();

    private static final long TIMEOUT = 60 * 60; // 1 hour
    private TimeoutCache timeoutCache;

    public AppDataSourceImpl() {
        init();
    }

    private void init() {
        timeoutCache = new TimeoutCacheImpl(TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public Observable<Object> getData(Object key) {
        return timeoutCache.get(key);
    }

    @Override
    public Observable<Object> getData(Observable observable) {
        return getData(null, observable);
    }

    @Override
    public Observable<Object> getData(Object key, Observable observable) {
        return Observable.just(1).flatMap((Function<Integer, ObservableSource<Object>>) integer -> {
            Observable<Object> cacheData = getData(key);
            return observable.startWith(cacheData).doOnNext(networkData -> updateCache(key, networkData)).distinctUntilChanged();
        });
    }

    @Override
    public void updateCache(Object key, Object value) {
        timeoutCache.put(key, value, TIMEOUT);
    }

    @Override
    public void clearCache() {
        timeoutCache.clear();
    }
}
