package com.tushar.countrylist.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountryAPI {
    // https://raw.githubusercontent.com/DevTides/countries/master/countriesV2.json
    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<Country>> getCountries();
}
