package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DBHandler db;

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

        mMap = googleMap;
        db = new DBHandler(this);
        Geocoder coder = new Geocoder(this);
        List<Address> address;

        List<Buffet> buffetList = db.getAllBuffets();

        for(Buffet obj : buffetList) {

            try {

                //Obtem Latitude e Longitude pelo endereço
                address = coder.getFromLocationName(obj.getAddress(), 5);
                if (address == null) {
                    return;
                }

                Address location  = address.get(0);
                location.getLatitude();
                location.getLongitude();

                // Add a marker in Sydney and move the camera
                //LatLng buffet_address = new LatLng(-34, 151);
                LatLng buffet_address = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(buffet_address).title(obj.getSubsidiary()));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LatLng sp = new LatLng( -23.3251, -46.3810);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sp));
    }
}
