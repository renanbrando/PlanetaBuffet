package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.Buffet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DBHandler db;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

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

        try {

            //tratar resposta do cliente
            checkLocationPermission();

            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            Geocoder coder = new Geocoder(this, Locale.getDefault());
            List<Address> address;

            db = new DBHandler(this);
            List<Buffet> buffetList = db.getAllBuffets();

            for (Buffet obj : buffetList) {

                try {

                    //Obtem Latitude e Longitude pelo endereço
                    address = coder.getFromLocationName(obj.getAddress(), 5);
                    if (address == null) {
                        String error = "Endereço nao cadastrado para o buffet [" + obj.getSubsidiary() + "]";
                        Log.e("LOCATION_ERROR", error);
                        continue;
                    }

                    if(address.isEmpty()) {
                        String error = "Endereço nao cadastrado para o buffet [" + obj.getSubsidiary() + "]";
                        Log.e("LOCATION_ERROR", error);
                        continue;
                    }
                    Address location = address.get(0);

                    LatLng buffet_address = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(buffet_address).title(obj.getSubsidiary()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //usar a localizacao do usuario
            LatLng sp = new LatLng( -23.3251, -46.3810);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sp));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

}
