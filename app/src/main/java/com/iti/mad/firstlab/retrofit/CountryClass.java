package com.iti.mad.firstlab.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ayman on 2019-02-04.
 */

public class CountryClass {

//    @SerializedName("rank")
    private int rank;
    private String country;
    private String population;
    private String flag;


    public CountryClass(int rank, String country, String population, String flag) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    public int getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        return population;
    }

    public String getFlag() {
        return flag;
    }
}
