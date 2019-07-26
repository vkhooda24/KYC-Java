package com.vkhooda24.knowyourcountry.ui.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.vkhooda24.knowyourcountry.R;
import com.vkhooda24.knowyourcountry.constants.IntentKeys;
import com.vkhooda24.knowyourcountry.domain.model.Countries;
import com.vkhooda24.knowyourcountry.ui.activities.CountriesListActivity;
import com.vkhooda24.knowyourcountry.ui.activities.CountryDetailActivity;
import com.vkhooda24.knowyourcountry.ui.adapter.CountriesRecyclerAdapter;
import com.vkhooda24.knowyourcountry.viewmodel.viewmodelImpl.CountriesViewModelImpl;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Vikram Hooda on 2019-07-25.
 */
public class CountriesListFragment extends Fragment {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private CountriesRecyclerAdapter countriesRecyclerAdapter;
    private String regionName = "all";

    CountriesViewModelImpl countriesViewModel;

    @Inject
    ViewModelProvider.Factory appViewModelProvider;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //To get the same view model object in activity and it's child fragment, either use @Singleton annotation on view model
        // OR
        // instead using this(Fragment scope when use of() method) below, use getActivity like ViewModelProviders.of(getActivity(), appViewModelProvider)
//        countriesViewModel = ViewModelProviders.of(this, appViewModelProvider).get(CountriesViewModelImpl.class); // Fragment scope, new view model object creates
        countriesViewModel = ViewModelProviders.of(getActivity(), appViewModelProvider).get(CountriesViewModelImpl.class); // Activity scope, same view model object which create by parent activity

        regionName = countriesViewModel.getRegionName();

        compositeDisposable.add(this.countriesViewModel.getCountriesList(regionName)
                .subscribe(this::bindViewsData, throwable -> throwable.printStackTrace()));

    }

    private void bindViewsData(List<Countries> countries) {

        //Initialize views
        RecyclerView countriesRecyclerView = getActivity().findViewById(R.id.countries_recycler_view);
        TextView countriesCountTextView = getActivity().findViewById(R.id.countriesCountTextView);

        //Create recycler adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        countriesRecyclerView.setLayoutManager(linearLayoutManager);
        countriesRecyclerAdapter = new CountriesRecyclerAdapter((CountriesListActivity) getActivity(), countries);
        countriesRecyclerView.setAdapter(countriesRecyclerAdapter);

        countriesCountTextView.setText(getString(R.string.countries_list_heading, regionName, countries.size()));

        countriesRecyclerAdapter.setOnItemClickListener(countryName -> {
            Intent intent = new Intent(getActivity(), CountryDetailActivity.class);
            intent.putExtra(IntentKeys.COUNTRY_NAME, countryName);
            startActivity(intent);
        });
    }
}
