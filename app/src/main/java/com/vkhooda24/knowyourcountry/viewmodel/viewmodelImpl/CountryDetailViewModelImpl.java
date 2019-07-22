package com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl;

import com.vkhooda24.knowyourcountry.domain.apis.CountryDetailApi;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.domain.model.AppDataCacheKey;
import com.vkhooda24.knowyourcountry.domain.model.CountryDetail;
import com.vkhooda24.knowyourcountry.viewmodel.CountryDetailViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.operators.observable.ObservableFromIterable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class CountryDetailViewModelImpl implements CountryDetailViewModel {

    private CountryDetailApi countryDetailApi;
    private AppDataSource<String, List<CountryDetail>> appDataSource;

    public CountryDetailViewModelImpl(CountryDetailApi countryDetailApi, AppDataSource appDataSource) {
        this.countryDetailApi = countryDetailApi;
        this.appDataSource = appDataSource;
    }

    @Override
    public Observable<CountryDetail> getCountryDetail(String countryName) {

        return appDataSource.getCacheAndRemoteData(AppDataCacheKey.COUNTRY_DETAIL_.name() + countryName, countryDetailApi.getCountryDetail(countryName)).flatMap(countryDetails -> ObservableFromIterable.fromIterable(countryDetails))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
