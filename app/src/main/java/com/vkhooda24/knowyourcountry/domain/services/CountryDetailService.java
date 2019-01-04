package com.vkhooda24.knowyourcountry.domain.services;

import com.vkhooda24.knowyourcountry.domain.model.CountryDetail;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public interface CountryDetailService {

    @GET("name/{countryName}?fullText=true")
    Observable<List<CountryDetail>> getCountryDetail(@Path("countryName") String countryName);
}
