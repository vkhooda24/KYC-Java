package com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl;

import android.arch.lifecycle.ViewModel;
import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.domain.model.Countries;
import com.vkhooda24.knowyourcountry.viewmodel.CountriesViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
//@Singleton Find explaination about this annotation in CountryListFragment
public class CountriesViewModelImpl extends ViewModel implements CountriesViewModel {

    private CountriesApi countriesApi;
    private AppDataSource<String, List<Countries>> countriesListAppDataSource;
    private String regionName;

    @Inject
    public CountriesViewModelImpl(CountriesApi countriesApi, AppDataSource appDataSource) {
        this.countriesApi = countriesApi;
        this.countriesListAppDataSource = appDataSource;
    }

    @Override
    public Observable<List<Countries>> getCountriesList(String regionName) {

        return countriesListAppDataSource.getData(regionName, countriesApi.getCountriesList(regionName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setRegionName(String regionName) {

        this.regionName = regionName;
    }

    @Override
    public String getRegionName() {
        return regionName;
    }
}
