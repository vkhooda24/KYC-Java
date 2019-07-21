package com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl;

import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSourceImpl;
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
    private AppDataSource<String, String> mapDataSource;
    private AppDataSource<String, String> valueDataSource;

    public CountriesViewModelImpl(CountriesApi countriesApi) {
        this.countriesApi = countriesApi;
        mapDataSource = new AppDataSourceImpl(String.class, String.class);
        valueDataSource = new AppDataSourceImpl(String.class);

    }

    @Override
    public Observable<List<Countries>> getCountriesList(String regionName) {

        Observable<List<Countries>> countriesListService = countriesApi.getCountriesList(regionName);

        mapDataSource.updateCache("Hello");
        String cacheValue = mapDataSource.get();

        return countriesListService
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
