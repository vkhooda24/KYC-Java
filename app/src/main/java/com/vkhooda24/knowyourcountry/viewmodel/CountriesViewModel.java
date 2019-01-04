package com.vkhooda24.knowyourcountry.viewmodel;

import com.vkhooda24.knowyourcountry.domain.model.Countries;
import io.reactivex.Observable;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public interface CountriesViewModel {

    Observable<List<Countries>> getCountriesList(String regionName);
}
