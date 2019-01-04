package com.vkhooda24.knowyourcountry.domain.services;

import com.vkhooda24.knowyourcountry.domain.model.Countries;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public interface CountriesListService {

    @GET("region/{regionName}")
    Observable<List<Countries>> getRegionCountries(@Path("regionName") String regionName);

    @GET("all")
    Observable<List<Countries>> getAllCountries();
}
