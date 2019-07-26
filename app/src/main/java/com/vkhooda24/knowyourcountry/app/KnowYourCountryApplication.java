package com.vkhooda24.knowyourcountry.app;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import com.vkhooda24.knowyourcountry.dagger.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class KnowYourCountryApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

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

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }
}
