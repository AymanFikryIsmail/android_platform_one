package com.iti.mad.firstlab.location;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by ayman on 2019-01-30.
 */

public class LocationHelper implements LocationListener {

    Context context;
    public LocationHelper() {
    }

    public LocationHelper(Context context) {
    }
    @Override
    public void onLocationChanged(Location location) {
        this.context=context;
    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
