package com.vkhooda24.knowyourcountry.domain.datasource;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
public class AppDataSourceImpl implements AppDataSource {

    private final String DEFAULT_VALUE = "cacheDefaultValue";
    private HashMap<Object, Object> hashMap = new HashMap<>();


    public AppDataSourceImpl(Type value) {
        this(null, value);
    }

    public AppDataSourceImpl(Type key, Type value) {
        init();
    }

    protected void init() {

    }

    @Override
    public Object get() {
        return get(null);
    }

    @Override
    public Object get(Object key) {
        if (key == null) {
            return hashMap.get(DEFAULT_VALUE);
        }
        return hashMap.get(key);
    }

    @Override
    public void updateCache(Object key, Object value) {

    }

    @Override
    public void updateCache(Object value) {
        hashMap.put(DEFAULT_VALUE, value);

    }

    @Override
    public void clear() {

    }
}
