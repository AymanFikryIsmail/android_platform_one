package com.iti.mad.firstlab.retrofit;

import com.iti.mad.firstlab.list_view.Cake;

import java.util.List;

/**
 * Created by ayman on 2019-02-04.
 */

public class CountryResponse {


    private List<CountryClass> worldpopulation;
    private List<Cake> cakes;

    public List<Cake> getCakes() {
        return cakes;
    }

    public List<CountryClass> getWorldpopulation() {
        return worldpopulation;
    }
}
