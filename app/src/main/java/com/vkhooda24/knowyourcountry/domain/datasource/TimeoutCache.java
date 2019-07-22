package com.vkhooda24.knowyourcountry.domain.datasource;

import io.reactivex.Observable;

/**
 * Created by Vikram Hooda on 2019-07-21.
 */
public interface TimeoutCache<K, V> {

    Observable<V> get(K key);

    void put(K key, V value);

    void put(K key, V value, long timeout);

    void clear();
}
