package com.vkhooda24.knowyourcountry.dagger;

import android.content.Context;
import com.vkhooda24.knowyourcountry.app.KnowYourCountryApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Vikram Hooda on 12/25/18.
 */
@Module
public class GlobalModule {

    @Provides
    @Singleton
    Context provideAppContext() {
        return KnowYourCountryApplication.getApplication();
    }

}
