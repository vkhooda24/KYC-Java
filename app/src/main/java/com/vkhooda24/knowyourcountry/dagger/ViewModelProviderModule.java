package com.vkhooda24.knowyourcountry.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.vkhooda24.knowyourcountry.viewmodel.provider.AppViewModelProvider;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountriesViewModelImpl;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountryDetailViewModelImpl;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Vikram Hooda on 2019-07-25.
 */
@Module
public abstract class ViewModelProviderModule {

    @Binds
    abstract ViewModelProvider.Factory appViewModelProvider(AppViewModelProvider appViewModelProvider);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelKey = CountriesViewModelImpl.class)
    abstract ViewModel provideCountriesViewModel(CountriesViewModelImpl countriesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelKey = CountryDetailViewModelImpl.class)
    abstract ViewModel provideCountryDetailViewModelImpl(CountryDetailViewModelImpl countriesViewModel);

}
