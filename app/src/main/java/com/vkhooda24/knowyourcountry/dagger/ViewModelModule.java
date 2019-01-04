package com.vkhooda24.knowyourcountry.dagger;

import com.vkhooda24.knowyourcountry.dagger.ApiModule;
import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.apis.CountryDetailApi;
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
    CountriesViewModel provideCountriesViewModel(CountriesApi countriesApi) {
        return new CountriesViewModelImpl(countriesApi);
    }

    @Singleton
    @Provides
    CountryDetailViewModel provideCountryDetailViewModel(CountryDetailApi countryDetailApi) {
        return new CountryDetailViewModelImpl(countryDetailApi);
    }
}
