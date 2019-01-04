package com.vkhooda24.knowyourcountry.dagger;

import com.vkhooda24.knowyourcountry.ui.activities.CountriesListActivity;
import com.vkhooda24.knowyourcountry.ui.activities.CountryDetailActivity;
import com.vkhooda24.knowyourcountry.ui.activities.WelcomeActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
@Module(includes = ViewModelModule.class)
public abstract class UiModule {

    @ContributesAndroidInjector
    abstract WelcomeActivity provideWelcomeActivity();

    @ContributesAndroidInjector
    abstract CountriesListActivity provideCountriesListActivity();

    @ContributesAndroidInjector
    abstract CountryDetailActivity provideCountryDetailActivity();
}
