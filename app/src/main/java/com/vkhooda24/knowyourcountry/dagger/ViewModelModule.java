package com.vkhooda24.knowyourcountry.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.apis.CountryDetailApi;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.viewmodel.CountriesViewModel;
import com.vkhooda24.knowyourcountry.viewmodel.CountryDetailViewModel;
import com.vkhooda24.knowyourcountry.viewmodel.provider.AppViewModelProvider;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountriesViewModelImpl;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountryDetailViewModelImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
@Module(includes = {ViewModelProviderModule.class, ApiModule.class})
public class ViewModelModule {

    //These view models provided using @Provide doesn't needed if using @Bind into map and getting from ViewModel factory. check ViewModelProviderModule
    //Keeping this commented code for future poc

//    @Provides
//    ViewModelProvider.Factory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
//        return new AppViewModelProvider(providerMap);
//    }

//    @Singleton
//    @Provides
//    CountriesViewModel provideCountriesViewModel(CountriesApi countriesApi, AppDataSource<String, String> appDataSource) {
//        return new CountriesViewModelImpl(countriesApi, appDataSource);
//    }
//
//    @Singleton
//    @Provides
//    CountryDetailViewModel provideCountryDetailViewModel(CountryDetailApi countryDetailApi,  AppDataSource<String, String> appDataSource) {
//        return new CountryDetailViewModelImpl(countryDetailApi, appDataSource);
//    }
}
