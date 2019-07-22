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
    public Observable<Object> getCacheAndRemoteData(Object key) {
        return timeoutCache.get(key);
    }

    @Override
    public Observable<Object> getRemoteData(Observable observable) {
        return observable;
    }

    /*
     * This method return first if there any cache data and then update with remote data. Even there is cache data present this
     * will also make network call and will update the cache for given key
     * */
    @Override
    public Observable<Object> getCacheAndRemoteData(Object key, Observable observable) {
        return Observable.just(1).flatMap((Function<Integer, ObservableSource<Object>>) integer -> {
            Observable<Object> cacheData = getCacheAndRemoteData(key);
            return observable.startWith(cacheData).doOnNext(remoteData -> updateCache(key, remoteData)).distinctUntilChanged();
        });
    }

    /*
     * This method would first check cache data and if exist then network call will not make.
     * If cache data doesn't exist then network call will make.
     * */
    @Override
    public Observable<Object> getCacheOrRemoteData(Object key, Observable observable) {
        Observable<Object> cacheData = getCacheAndRemoteData(key);
        return cacheData.switchIfEmpty(observable).doOnNext(remoteData -> updateCache(key, remoteData));
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
