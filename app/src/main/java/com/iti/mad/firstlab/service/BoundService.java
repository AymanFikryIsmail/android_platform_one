package com.iti.mad.firstlab.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service {

    private final IBinder iBinder=new MyLocalBinder();
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return  iBinder;
    }

    public String getTime(){

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("HH:mm:ss MM/dd/yyyy" , Locale.US);

        return (simpleDateFormat.format(new Date()));
    }
    public class MyLocalBinder extends Binder{

        BoundService getService(){

            return  BoundService.this;
        }
    }
}
