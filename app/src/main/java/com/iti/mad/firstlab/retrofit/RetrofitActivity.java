package com.iti.mad.firstlab.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iti.mad.firstlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {


    RetrofitAdapter retrofitAdapter;
    List<CountryClass> countryArrayList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
//        retrofitAdapter = new RetrofitAdapter(this, orderArrayList);
       // countryArrayList=null;
        getCountries();
    }


    public  void getCountries(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.androidbegin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GsonResponse service =retrofit.create(GsonResponse.class);
        Call<CountryResponse>call=service.getCountries();

        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                countryArrayList = new ArrayList<>();
                countryArrayList = response.body().getWorldpopulation();
                retrofitAdapter = new RetrofitAdapter(RetrofitActivity.this, countryArrayList);
                recyclerView.setAdapter(retrofitAdapter);
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {

                Toast.makeText(RetrofitActivity.this, "",Toast.LENGTH_LONG).show();
            }
        });

    }
}
