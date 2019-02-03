package com.iti.mad.firstlab.thread;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.list_view.Cake;

public class CountryDetailActivity extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Cake  country;
    CountrydetailFragment countrydetailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

          country = (Cake) getIntent().getSerializableExtra("country");

         countrydetailFragment=new CountrydetailFragment();
        fm=getSupportFragmentManager();

        fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.detailsFragment1, countrydetailFragment,"detailsFragment");
        fragmentTransaction.commit();

        pref = getApplicationContext().getSharedPreferences("Mobile", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        editor.putString("country",country.getCakeName() ); // Storing boolean - true/false
        editor.putString("desc",  country.getCakeDesc()); // Storing string
        editor.putString("image",  country.getImage()); // Storing string
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            CountrydetailFragment countrydetailFragment1= (CountrydetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment1);
            countrydetailFragment1.changeCountry(country);
        }
        else {
            Intent intent =new Intent(this, CountryListActivity.class);
            intent.putExtra("country",country);
            startActivity(intent);
        }
    }
}
