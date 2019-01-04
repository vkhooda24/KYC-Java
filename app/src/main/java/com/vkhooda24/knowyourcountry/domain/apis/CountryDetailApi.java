package com.vkhooda24.knowyourcountry.domain.apis;

import com.vkhooda24.knowyourcountry.domain.model.CountryDetail;
import io.reactivex.Observable;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public interface CountryDetailApi {

    Observable<List<CountryDetail>> getCountryDetail(String countryName);
}
