package com.vkhooda24.knowyourcountry.domain.datasource;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
public interface AppDataSource<K, V> {

    V get();

    V get(K k);

    void updateCache(K k, V v);

    void updateCache(V v);

    void clear();
}
