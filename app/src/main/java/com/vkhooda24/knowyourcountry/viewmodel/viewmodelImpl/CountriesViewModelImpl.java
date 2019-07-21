package com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl;

import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.domain.model.Countries;
import com.vkhooda24.knowyourcountry.viewmodel.CountriesViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class CountriesViewModelImpl implements CountriesViewModel {


    private CountriesApi countriesApi;
    private AppDataSource<String, String> appDataSource;
    private AppDataSource<String, Integer> integerAppDataSource;

    public CountriesViewModelImpl(CountriesApi countriesApi, AppDataSource appDataSource) {
        this.countriesApi = countriesApi;
        this.appDataSource = appDataSource;
        this.integerAppDataSource = appDataSource;
    }

    @Override
    public Observable<List<Countries>> getCountriesList(String regionName) {

        Observable<List<Countries>> countriesListService = countriesApi.getCountriesList(regionName);

        appDataSource.updateCache("Hello");
        String cacheValue = appDataSource.get();

        integerAppDataSource.updateCache("intData", 1);
        Integer integerCacheValue = integerAppDataSource.get("intData");

        return countriesListService
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
