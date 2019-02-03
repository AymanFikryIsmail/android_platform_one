package com.iti.mad.firstlab.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iti.mad.firstlab.R;

public class ServiceActivity extends AppCompatActivity {

    Button startService , stopSerive  ,startHandleService , startBound;


    BoundService boundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        startService=findViewById(R.id.startService);
        stopSerive=findViewById(R.id.stopService);

        startHandleService=findViewById(R.id.startHandleService);

        startBound=findViewById(R.id.startBound);

        ServiceConnection serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                BoundService.MyLocalBinder localBinder =(BoundService.MyLocalBinder)iBinder;
                boundService.getTime();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        startBound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ServiceActivity.this, ServiceClass.class);
                startService(intent);
            }
        });





        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ServiceActivity.this, ServiceClass.class);
                startService(intent);
            }
        });

        startHandleService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ServiceActivity.this, MyIntentService.class);
                startService(intent);
            }
        });

        stopSerive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(ServiceActivity.this, ServiceClass.class));

            }
        });
    }
}
