package com.vkhooda24.knowyourcountry.dagger;

import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.apis.CountryDetailApi;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.viewmodel.CountriesViewModel;
import com.vkhooda24.knowyourcountry.viewmodel.CountryDetailViewModel;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountriesViewModelImpl;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountryDetailViewModelImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
@Module(includes = ApiModule.class)
public
class ViewModelModule {

    @Singleton
    @Provides
    CountriesViewModel provideCountriesViewModel(CountriesApi countriesApi, AppDataSource<String, String> appDataSource) {
        return new CountriesViewModelImpl(countriesApi, appDataSource);
    }

    @Singleton
    @Provides
    CountryDetailViewModel provideCountryDetailViewModel(CountryDetailApi countryDetailApi,  AppDataSource<String, String> appDataSource) {
        return new CountryDetailViewModelImpl(countryDetailApi, appDataSource);
    }
}
