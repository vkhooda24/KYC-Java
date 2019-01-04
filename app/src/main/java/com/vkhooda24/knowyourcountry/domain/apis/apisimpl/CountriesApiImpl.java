package com.vkhooda24.knowyourcountry.domain.apis.apisimpl;

import com.vkhooda24.knowyourcountry.domain.apis.CountriesApi;
import com.vkhooda24.knowyourcountry.domain.model.Countries;
import com.vkhooda24.knowyourcountry.domain.services.CountriesListService;
import io.reactivex.Observable;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class CountriesApiImpl implements CountriesApi {

    private CountriesListService countriesListService;

    public CountriesApiImpl(CountriesListService countriesListService) {
        this.countriesListService = countriesListService;
    }

    @Override
    public Observable<List<Countries>> getCountriesList(String regionName) {
        if (regionName.equalsIgnoreCase("all")) {
            return countriesListService.getAllCountries();
        } else {
            return countriesListService.getRegionCountries(regionName);
        }
    }
}
