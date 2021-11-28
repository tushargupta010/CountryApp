package com.tushar.countrylist.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryService {

    private static final String BASE_URL = "https://raw.githubusercontent.com";

    private static CountryService instance;

    private CountryAPI api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CountryAPI.class);

    private CountryService() {

    }

    public static CountryService getInstance() {
        if (instance == null) {
            instance = new CountryService();
        }
        return instance;
    }

    public Single<List<Country>> getCountries() {
        return api.getCountries();
    }

}
