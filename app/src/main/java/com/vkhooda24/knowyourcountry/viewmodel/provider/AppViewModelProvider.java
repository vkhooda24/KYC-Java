package com.vkhooda24.knowyourcountry.viewmodel.provider;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

/**
 * Created by Vikram Hooda on 2019-07-25.
 */
@Singleton
public class AppViewModelProvider implements ViewModelProvider.Factory {

    private Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap;

    @Inject
    public AppViewModelProvider(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {

        this.providerMap = providerMap;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) providerMap.get(modelClass).get();
    }
}
