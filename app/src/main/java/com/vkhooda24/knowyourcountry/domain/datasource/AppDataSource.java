package com.vkhooda24.knowyourcountry.domain.datasource;


import io.reactivex.Observable;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
public interface AppDataSource<K, V> {

    V getCacheAndRemoteData(K k);

    Observable<V> getRemoteData(Observable<V> observable);

    Observable<V> getCacheAndRemoteData(K k, Observable<V> observable);

    Observable<V> getCacheOrRemoteData(K k, Observable<V> observable);

    void updateCache(K k, V v);

    void clearCache();
}
