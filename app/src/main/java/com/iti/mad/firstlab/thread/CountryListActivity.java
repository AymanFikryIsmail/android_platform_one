package com.iti.mad.firstlab.thread;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.fragment_lab.FragmentInterface;
import com.iti.mad.firstlab.list_view.Cake;
import com.iti.mad.firstlab.list_view.DetailFragment;

public class CountryListActivity extends AppCompatActivity  implements FragmentInterface{

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    CountryListFragment countryListFragment;
    CountrydetailFragment countrydetailFragment;

    Cake country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);



        countryListFragment=new CountryListFragment();
        fm=getSupportFragmentManager();

        fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.listFragment, countryListFragment,"listFragment");
        fragmentTransaction.commit();

        //fragmentTransaction.replace(R.id.detailsFragment, f1,"listFragment");


        pref = getApplicationContext().getSharedPreferences("Mobile", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();


        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){

            editor.putString("country","" ); // Storing boolean - true/false
            editor.putString("desc",  ""); // Storing string
            editor.putString("image",  ""); // Storing string
            editor.commit();
        }
        else {
            if (pref.getString("country", "")!=""){

                CountrydetailFragment countrydetailFragment=new CountrydetailFragment();
                fm=getSupportFragmentManager();

                fragmentTransaction=fm.beginTransaction();
                fragmentTransaction.replace(R.id.detailsFragment, countrydetailFragment,"detailsFragment");
                fragmentTransaction.commit();
                country = (Cake) getIntent().getSerializableExtra("country");


            }

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(getResources().getConfiguration().orientation!= Configuration.ORIENTATION_PORTRAIT) {

            if (pref.getString("country", "") != "") {
                CountrydetailFragment countrydetailFragment1 = (CountrydetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
                countrydetailFragment1.changeCountry(country);
            }
        }
    }

    @Override
    public void increment(int counter) {

    }

    @Override
    public void redirect(Cake country) {


        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){

            Intent intent =new Intent(this, CountryDetailActivity.class);
            intent.putExtra("country",country);
            startActivity(intent);

        }
        else {
            CountrydetailFragment countrydetailFragment1 = (CountrydetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
            countrydetailFragment1.changeCountry(country);

        }
    }


}
