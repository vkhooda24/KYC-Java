package com.vkhooda24.knowyourcountry.domain.datasource;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.util.HashMap;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
public class AppDataSourceImpl implements AppDataSource {

    private static final String TAG = AppDataSourceImpl.class.getSimpleName();

    private final String DEFAULT_VALUE = "cacheDefaultValue";
    private HashMap<Object, Object> hashMap;

    public AppDataSourceImpl() {
        init();
    }

    private void init() {
        hashMap = new HashMap<>();
    }

    @Override
    public Observable<Object> getData(Object objKey) {
        Object key = objKey == null ? DEFAULT_VALUE : objKey;
        return hashMap.get(key) != null ? Observable.just(hashMap.get(key)) : Observable.empty();
    }

    @Override
    public Observable<Object> getData(Observable observable) {
        return getData(null, observable);
    }

    @Override
    public Observable<Object> getData(Object key, Observable observable) {
        return Observable.just(1).flatMap(new Function<Integer, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(Integer integer) throws Exception {
                Observable<Object> cacheData = getData(key);
                return observable.startWith(cacheData).doOnNext(networkData -> updateCache(key, networkData)).distinctUntilChanged();
            }
        });
    }

    @Override
    public void updateCache(Object key, Object value) {
        Object dataKey = key == null ? DEFAULT_VALUE : key;
        hashMap.put(dataKey, value);

        Log.d(TAG, "Data updated successfully for key: " + dataKey.toString());
    }

    @Override
    public void clearCache() {
        hashMap.clear();
    }
}
