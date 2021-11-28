package com.tushar.countrylist.model;

import com.tushar.countrylist.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountryService {

    private static CountryService instance;

    @Inject
    public CountryAPI api;

    private CountryService() {
        DaggerApiComponent.create().inject(this);
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
