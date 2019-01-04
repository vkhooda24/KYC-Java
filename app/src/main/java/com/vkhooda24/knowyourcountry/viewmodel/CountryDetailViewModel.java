package com.vkhooda24.knowyourcountry.viewmodel;

import com.vkhooda24.knowyourcountry.domain.model.CountryDetail;
import io.reactivex.Observable;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public interface CountryDetailViewModel {

    Observable<CountryDetail> getCountryDetail(String countryName);

}
