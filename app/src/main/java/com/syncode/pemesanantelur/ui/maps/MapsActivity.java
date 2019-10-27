package com.syncode.pemesanantelur.ui.maps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.utils.DialogClass;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationManager lm;

    private static final int CODE_PERMISSION_GPS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, CODE_PERMISSION_GPS);
            } else {
                boolean isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (isGpsEnabled) {
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 20.0f));
                    }
                }
            }
        }
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        mMap.setOnMapClickListener(latLng -> {
            List<Address> addresses;
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            try {
                addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String zip = addresses.get(0).getPostalCode();
                String country = addresses.get(0).getCountryName();
                String allAddress = address + " " + city + " " + state + " " + zip + " " + country;
                String latLong = latLng.latitude + "," + latLng.longitude;
                SystemDataLocal systemDataLocal = new SystemDataLocal(this);
                systemDataLocal.setCoordinate(allAddress, latLong);
            } catch (IOException e) {
                e.printStackTrace();
            }
            alertDialogYesOrNo();
        });
    }


    private void alertDialogYesOrNo() {
        View v = getLayoutInflater().inflate(R.layout.alert_location_yes, null, false);
        AlertDialog.Builder builder = DialogClass.dialog(this, v);
        builder.setPositiveButton("Simpan", (dialogInterface, i) -> {
            onBackPressed();
        }).setNegativeButton("Tidak", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
