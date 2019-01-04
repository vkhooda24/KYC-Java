package com.vkhooda24.knowyourcountry.domain.apis.apisimpl;

import com.vkhooda24.knowyourcountry.domain.apis.CountryDetailApi;
import com.vkhooda24.knowyourcountry.domain.model.CountryDetail;
import com.vkhooda24.knowyourcountry.domain.services.CountryDetailService;
import io.reactivex.Observable;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class CountryDetailImpl implements CountryDetailApi {

    private CountryDetailService countryDetailService;

    public CountryDetailImpl(CountryDetailService countryDetailService) {
        this.countryDetailService = countryDetailService;

    }

    @Override
    public Observable<List<CountryDetail>> getCountryDetail(String countryName) {
        return countryDetailService.getCountryDetail(countryName);
    }
}
