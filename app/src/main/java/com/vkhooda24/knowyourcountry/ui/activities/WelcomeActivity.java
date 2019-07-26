package com.vkhooda24.knowyourcountry.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.vkhooda24.knowyourcountry.R;
import com.vkhooda24.knowyourcountry.constants.IntentKeys;

/**
 * Created by Vikram Hooda on 1/3/19.
 */
public class WelcomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.asiaRegionCountriesButton).setOnClickListener(v -> launchCountriesListActivity("Asia"));

        findViewById(R.id.americasRegionCountriesButton).setOnClickListener(v -> launchCountriesListActivity("Americas"));

        findViewById(R.id.europeRegionCountriesButton).setOnClickListener(v -> launchCountriesListActivity("Europe"));

        findViewById(R.id.africaRegionCountriesButton).setOnClickListener(v -> launchCountriesListActivity("Africa"));

        findViewById(R.id.allCountriesButton).setOnClickListener(v -> launchCountriesListActivity("All"));

    }

    private void launchCountriesListActivity(String regionName) {
        CountriesListActivity.getCountriesListActivityIntent(this, regionName);
    }
}
