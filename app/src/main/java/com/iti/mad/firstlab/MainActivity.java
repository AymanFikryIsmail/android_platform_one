package com.iti.mad.firstlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iti.mad.firstlab.fragment_lab.CounterActivity;
import com.iti.mad.firstlab.list_view.FragmentListActivity;
import com.iti.mad.firstlab.list_view.ListViewActivity;
import com.iti.mad.firstlab.location.LocationActivity;
import com.iti.mad.firstlab.service_cast_demo_one.DownloadActivity;
import com.iti.mad.firstlab.service_demo_two.BatteryLowActivity;
import com.iti.mad.firstlab.service_demo_two.CaptureActivity;
import com.iti.mad.firstlab.thread.CountryListActivity;
import com.iti.mad.firstlab.thread.HandlerActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="LIFECYCLE";

    TextView firstCol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i=new Intent(MainActivity.this, DetailsActivity.class);//BatteryLowActivity
        startActivity(i);
        firstCol=findViewById(R.id.firstColId);
        firstCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, CountryListActivity.class);
                startActivity(i);
            }
        });
        Log.i(TAG,"onCreate");
    }

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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
