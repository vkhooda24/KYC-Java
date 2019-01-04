package com.vkhooda24.knowyourcountry.dagger;

import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.apis.CountryDetailApi;
import com.vkhooda24.knowyourcountry.domain.apis.apisimpl.CountriesApiImpl;
import com.vkhooda24.knowyourcountry.domain.apis.apisimpl.CountryDetailImpl;
import com.vkhooda24.knowyourcountry.domain.retrofit.RetrofitBuilder;
import com.vkhooda24.knowyourcountry.domain.services.CountriesListService;
import com.vkhooda24.knowyourcountry.domain.services.CountryDetailService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
@Module(includes = ServicesModule.class)
public class ApiModule {

    @Singleton
    @Provides
    CountriesApi provideCountriesApi(CountriesListService countriesListService) {
        return new CountriesApiImpl(countriesListService);
    }

    @Singleton
    @Provides
    CountryDetailApi provideCountryDetailApi(CountryDetailService countryDetailService) {
        return new CountryDetailImpl(countryDetailService);
    }
}
