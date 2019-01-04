package com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl;

import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
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

    public CountriesViewModelImpl(CountriesApi countriesApi) {
        this.countriesApi = countriesApi;

    }

    @Override
    public Observable<List<Countries>> getCountriesList(String regionName) {
        return countriesApi.getCountriesList(regionName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
