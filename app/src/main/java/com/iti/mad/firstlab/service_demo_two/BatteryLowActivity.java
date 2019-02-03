package com.iti.mad.firstlab.service_demo_two;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iti.mad.firstlab.R;

public class BatteryLowActivity extends AppCompatActivity {


    private BroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_low);
      // mReceiver = new BatteryLowReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(getPackageName() + "android.intent.action.BATTERY_LOW");
//        filter.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        BatteryLowReceiver myReceiver = new BatteryLowReceiver();
        registerReceiver(myReceiver, filter);


//
//
//        Intent broadCastIntent = new Intent();
//        broadCastIntent.setAction(Intent.ACTION_BATTERY_LOW);
//        broadCastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        sendBroadcast(broadCastIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
