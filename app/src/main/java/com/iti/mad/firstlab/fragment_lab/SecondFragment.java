package com.iti.mad.firstlab.fragment_lab;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iti.mad.firstlab.R;

import static com.iti.mad.firstlab.fragment_lab.CounterActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }

    TextView incText;
    int counter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_second, container, false);

        incText=view.findViewById(R.id.counterId);
        if (savedInstanceState!=null){
incText.setText(savedInstanceState.get("counter")+"");
        }
        return  view;
    }

    void changeCounter(int num){
        incText.setText(num+"");
        counter=num;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
    }



//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        if (savedInstanceState!=null) {
//
//            counter = savedInstanceState.getInt("counter");
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        incText.setText(counter+"");
//
//
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG," second fragment onAttach");

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"second fragment onStart");
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"second fragment onResume");

    }




    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"second fragment onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"second fragment onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG," second fragment onDestroy");

    }
}
