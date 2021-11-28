package com.tushar.countrylist.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tushar.countrylist.di.DaggerApiComponent;
import com.tushar.countrylist.model.Country;
import com.tushar.countrylist.model.CountryService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel extends ViewModel {

    public MutableLiveData<List<Country>> countries = new MutableLiveData<List<Country>>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    @Inject
    public CountryService countryService;
    //private CountryService countryService = CountryService.getInstance();
    private CompositeDisposable disposable;

    public CountryViewModel() {
        super();
        disposable = new CompositeDisposable();
        DaggerApiComponent.create().inject(this);
    }

    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries() {

        //tempApiData();
        loading.setValue(true);
        disposable.add(
                countryService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(@NonNull List<Country> countriesList) {
                        countries.setValue(countriesList);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );

    }

    private void tempApiData() {
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

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
