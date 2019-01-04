package com.vkhooda24.knowyourcountry.dagger;

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
@Module
public class ServicesModule {

    @Singleton
    @Provides
    CountriesListService provideCountriesListService(Retrofit retrofit) {
        return retrofit.create(CountriesListService.class);
    }

    @Singleton
    @Provides
    CountryDetailService provideCountryDetailService(Retrofit retrofit) {
        return retrofit.create(CountryDetailService.class);
    }

    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return RetrofitBuilder.build();
    }
}
