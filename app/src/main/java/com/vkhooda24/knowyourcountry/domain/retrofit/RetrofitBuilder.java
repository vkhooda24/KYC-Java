package com.vkhooda24.knowyourcountry.domain.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vikram Hooda on 12/22/18.
 */
public class RetrofitBuilder {

    private final static String BASE_URL = "https://restcountries.eu/rest/v2/";

    public static Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
