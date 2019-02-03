package com.iti.mad.firstlab.fragment_lab;

import  android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.list_view.Cake;

public class CounterActivity extends AppCompatActivity  implements  FragmentInterface{


    SecondFragment f2;
    int counter;
    public static final String TAG="LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter2);

        if (savedInstanceState==null){

        FirstFragment f1=new FirstFragment();
        FragmentManager fm=getSupportFragmentManager();

        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.add(R.id.firstFragmentId, f1,"firstFragment");
        fragmentTransaction.commit();


         f2=new SecondFragment();
         fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.add(R.id.secondFragmentId, f2,"secondFragment");
        fragmentTransaction.commit();
        }

    }

    @Override
    public void increment(int counter) {
       this.counter=counter;
        SecondFragment f3= (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.secondFragmentId);
        f3.changeCounter(counter);
    }

    @Override
    public void redirect(Cake country) {

    }


//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        counter= savedInstanceState.getInt("counter");
//        FirstFragment first= (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.firstFragmentId);
//        first.setCounter(counter);
//        SecondFragment second= (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.secondFragmentId);
//        second.changeCounter(counter);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("counter", counter);
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG,"onReStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"onResume");

    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG," onDestroy");

    }
}
