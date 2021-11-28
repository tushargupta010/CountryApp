package com.tushar.countrylist.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    String name;
    String capital;
    @SerializedName("flagPNG")
    String flag;

    public Country(String name, String capital, String flag) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }
}
