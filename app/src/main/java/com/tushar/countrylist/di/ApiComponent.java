package com.tushar.countrylist.di;

import com.tushar.countrylist.model.CountryService;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountryService countryService);

}
