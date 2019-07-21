package com.vkhooda24.knowyourcountry.dagger;

import com.vkhooda24.knowyourcountry.app.KnowYourCountryApplication;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
@Singleton
@Component(modules = {
        UiModule.class,
        AppDataSourceModule.class,
        GlobalModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<KnowYourCountryApplication> {

}
