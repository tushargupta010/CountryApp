package com.tushar.countrylist.di;

import com.tushar.countrylist.model.CountryService;
import com.tushar.countrylist.viewmodel.CountryViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountryService countryService);
    void inject(CountryViewModel countryViewModel);

}
