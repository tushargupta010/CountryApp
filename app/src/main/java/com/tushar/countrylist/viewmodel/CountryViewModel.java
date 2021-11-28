package com.tushar.countrylist.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tushar.countrylist.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryViewModel extends ViewModel {

    public MutableLiveData<List<Country>> countries = new MutableLiveData<List<Country>>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries() {
        Country country1 = new Country("Albania", "Triana", "");
        Country country2 = new Country("Brazil", "Brasilia", "");
        Country country3 = new Country("Czechia", "Praha", "");

        List<Country> countryList = new ArrayList<>();
        countryList.add(country1);
        countryList.add(country2);
        countryList.add(country3);

        countries.setValue(countryList);
        countryLoadError.setValue(false);
        loading.setValue(false);

    }

}
