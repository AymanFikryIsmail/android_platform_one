package com.iti.mad.firstlab.fragment_lab;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iti.mad.firstlab.R;

import static com.iti.mad.firstlab.fragment_lab.CounterActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    Button incBtn;
    int counter ;
    FragmentInterface fragmentInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blank, container, false);

        incBtn=view.findViewById(R.id.counterBtn);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         fragmentInterface= (FragmentInterface) getActivity();

        incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                fragmentInterface.increment(counter);

            }
        });

    }

    void setCounter(int currentCounter){
        counter=currentCounter;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState!=null) {
           // counter = savedInstanceState.getInt("counter");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG," first fragment onAttach");

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"first fragment onStart");
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"first fragment onResume");

    }




    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"first fragment onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"first fragment onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG," first fragment onDestroy");

    }
}
