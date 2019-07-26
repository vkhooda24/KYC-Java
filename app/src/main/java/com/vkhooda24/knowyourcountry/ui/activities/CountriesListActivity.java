package com.vkhooda24.knowyourcountry.ui.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.vkhooda24.knowyourcountry.R;
import com.vkhooda24.knowyourcountry.constants.IntentKeys;
import com.vkhooda24.knowyourcountry.ui.fragments.CountriesListFragment;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountriesViewModelImpl;
import dagger.android.AndroidInjection;

import javax.inject.Inject;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class CountriesListActivity extends FragmentActivity {

    CountriesViewModelImpl countriesViewModel;

    @Inject
    ViewModelProvider.Factory appViewModelProvider;

    public static void getCountriesListActivityIntent(FragmentActivity activity, String regionName) {
        Intent intent = new Intent(activity, CountriesListActivity.class);
        intent.putExtra(IntentKeys.REGION_NAME, regionName);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);

        //Getting view model from view model provider
        countriesViewModel = ViewModelProviders.of(this, appViewModelProvider).get(CountriesViewModelImpl.class);

        //Fragment added
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new CountriesListFragment(), CountriesListFragment.class.getSimpleName()).commit();

        String regionName = getIntent().getExtras().getString(IntentKeys.REGION_NAME);
        countriesViewModel.setRegionName(regionName);
    }
}
