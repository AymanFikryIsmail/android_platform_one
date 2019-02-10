package com.iti.mad.firstlab.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ayman on 2019-02-04.
 */

public interface GsonResponse {

    @GET("tutorial/jsonparsetutorial.txt")
    Call<CountryResponse> getCountries();

}
