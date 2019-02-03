package com.iti.mad.firstlab.location;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iti.mad.firstlab.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback
        , LocationListener , GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener {

    Button locBtn ,sendSMSBtn ,gotoMap;

    LocationManager mgr;
    private GoogleMap mMap;

    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locBtn = findViewById(R.id.locBtn);
        sendSMSBtn = findViewById(R.id.sendSMSBtn);

        gotoMap = findViewById(R.id.gotoMap);
        gotoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LocationActivity.this, MapsActivity.class);
                i.putExtra("longitude",longitude);
                i.putExtra("latitude",latitude);
                startActivity(i);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        sendSMSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(LocationActivity.this, Locale.getDefault());
                String address = "";

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    if (addresses.size() > 0 && addresses != null) {
                        address = addresses.get(0).getAddressLine(0);
                    } else {
                        address = "address is not determined ";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", address);
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
            }
        });
        mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // to support the old api
                    ActivityCompat.requestPermissions(LocationActivity.this ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
                }else {
                    mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, LocationActivity.this);


                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 111:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                }else{
                    ActivityCompat.requestPermissions(LocationActivity.this ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
                }
                break;
                }

        }

    @Override
    public void onLocationChanged(Location location) {
            longitude=location.getLongitude();
            latitude=location.getLatitude();
        Toast.makeText(LocationActivity.this, "logitude :" + location.getLongitude()
                + "Latitude :" + location.getLatitude(), Toast.LENGTH_LONG).show();
         mgr.removeUpdates(LocationActivity.this);

         //moveMap();
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // to support the old api
            ActivityCompat.requestPermissions(LocationActivity.this ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
        }else {
            mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, LocationActivity.this);


        }
        latitude = mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
        longitude = mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();

        // Add a marker in Sydney and move the camera
        LatLng here = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(here).title("I'm here "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(here)             // Sets the center of the map to location user
                .zoom(20)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        // Add Marker to Map
        MarkerOptions option = new MarkerOptions();
        option.title(" Current Location");
        option.snippet("....");
        option.position(here);
        option   .draggable(true) ;//Making the marker draggable

        Marker currentMarker = mMap.addMarker(option);
        currentMarker.showInfoWindow();






        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {
                // Creating a marker
                mMap.clear();
                //Adding a new marker to the current pressed position we are also making the draggable true
                mMap.addMarker(new MarkerOptions().position(latLng).draggable(true).title("My location")).showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                latitude = latLng.latitude;
                longitude = latLng.longitude;


            }
        });
    }


    //Function to move the map
    private void moveMap() {
        //String to display current latitude and longitude
        String msg = latitude + ", "+longitude;
        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(latitude, longitude);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)             // Sets the center of the map to location user
                .zoom(15)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        // Add Marker to Map
        MarkerOptions option = new MarkerOptions();
        option.title(" Current Location");
        option.snippet("....");
        option.position(latLng);
        option   .draggable(true) ;//Making the marker draggable

        Marker currentMarker = mMap.addMarker(option);
        currentMarker.showInfoWindow();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }




    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }
}
