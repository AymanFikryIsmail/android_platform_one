package com.iti.mad.firstlab.location;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        , LocationListener , GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener {
    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;

    double latitude;
    double longitude;
    //Google ApiClient
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent i =getIntent();
        longitude=i.getDoubleExtra("longitude",0);
        latitude=i.getDoubleExtra("latitude",0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng here = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(here).title("I'm here "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
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
    public void onLocationChanged(Location location) {

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
