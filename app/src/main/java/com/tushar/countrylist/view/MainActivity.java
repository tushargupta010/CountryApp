package com.tushar.countrylist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tushar.countrylist.R;
import com.tushar.countrylist.viewmodel.CountryViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.countriesList)
    RecyclerView countriesList;
    @BindView(R.id.list_error)
    TextView listError;
    @BindView(R.id.list_loading)
    ProgressBar loadingView;

    private CountryViewModel countryViewModel;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        countryAdapter = new CountryAdapter(new ArrayList<>());
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryViewModel.refresh();

        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(countryAdapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            countryViewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });

        observeViewModel();
        
    }

    private void observeViewModel() {
        countryViewModel.countries.observe(this, countries -> {
            if(countries != null) {
                countriesList.setVisibility(View.VISIBLE);
                countryAdapter.updateCountries(countries);
            }
        });
        countryViewModel.countryLoadError.observe(this, isError -> {
            if(isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        countryViewModel.loading.observe(this, isLoading -> {
            if(isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading) {
                    listError.setVisibility(View.GONE);
                    countriesList.setVisibility(View.GONE);
                }
            }
        });
    }
}