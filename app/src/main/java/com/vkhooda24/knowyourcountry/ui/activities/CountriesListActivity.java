package com.vkhooda24.knowyourcountry.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.vkhooda24.knowyourcountry.R;
import com.vkhooda24.knowyourcountry.constants.IntentKeys;
import com.vkhooda24.knowyourcountry.domain.model.Countries;
import com.vkhooda24.knowyourcountry.ui.adapter.CountriesRecyclerAdapter;
import com.vkhooda24.knowyourcountry.viewmodel.CountriesViewModel;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class CountriesListActivity extends Activity {

    private CountriesRecyclerAdapter countriesRecyclerAdapter;

    @Inject
    CountriesViewModel countriesViewModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String regionName = "all";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().getExtras() != null) {
            regionName = getIntent().getExtras().getString(IntentKeys.REGION_NAME);
        }

        compositeDisposable.add(countriesViewModel.getCountriesList(regionName)
                .subscribe(countries -> {
                            bindViewsData(countries);
                        },
                        throwable -> throwable.printStackTrace()));

    }

    private void bindViewsData(List<Countries> countries) {

        //Initialize views
        RecyclerView countriesRecyclerView = findViewById(R.id.countries_recycler_view);
        TextView countriesCountTextView = findViewById(R.id.countriesCountTextView);

        //Create recycler adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        countriesRecyclerView.setLayoutManager(linearLayoutManager);
        countriesRecyclerAdapter = new CountriesRecyclerAdapter(this, countries);
        countriesRecyclerView.setAdapter(countriesRecyclerAdapter);

        countriesCountTextView.setText(getString(R.string.countries_list_heading, regionName, countries.size()));

        countriesRecyclerAdapter.setOnItemClickListener(countryName -> {
            Intent intent = new Intent(this, CountryDetailActivity.class);
            intent.putExtra(IntentKeys.COUNTRY_NAME, countryName);
            startActivity(intent);
        });
    }
}
