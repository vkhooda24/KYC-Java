package com.vkhooda24.knowyourcountry.app;

import android.app.Activity;
import android.app.Application;
import com.vkhooda24.knowyourcountry.dagger.DaggerAppComponent;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class KnowYourCountryApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    private static KnowYourCountryApplication CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;

        DaggerAppComponent.create().inject(this);
    }

    public static KnowYourCountryApplication getApplication() {
        return CONTEXT;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
