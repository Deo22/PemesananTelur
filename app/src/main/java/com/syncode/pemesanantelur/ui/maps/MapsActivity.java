package com.syncode.pemesanantelur.ui.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.TrackingModel;
import com.syncode.pemesanantelur.data.model.order.Order;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.LatLngInterpolator;
import com.syncode.pemesanantelur.utils.MarkerAnimation;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Observer<TrackingModel> {

    private GoogleMap mMap;

    private LocationManager lm;

    private static final int CODE_PERMISSION_GPS = 1;
    private Marker courierMarker;
    private Order order;
    private MapsViewModel mapsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        Intent intent = getIntent();
        order = intent.getParcelableExtra("order");
        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (order != null) {
            String idTransaction = order.getIdTransaksi();
            mapsViewModel.getTracking(idTransaction).observe(this, this);
        }
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
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15.0f));
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

        mMap.setMyLocationEnabled(true);
    }


    private void alertDialogYesOrNo() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.alert_location_yes, null, false);
        AlertDialog.Builder builder = DialogClass.dialog(this, v);
        builder.setPositiveButton("Simpan", (dialogInterface, i) -> onBackPressed()).setNegativeButton("Tidak", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onChanged(TrackingModel trackingModel) {
        if (trackingModel != null) {
            LatLng latLng = new LatLng(trackingModel.getLat(), trackingModel.getLont());
            if (mMap != null) {
                if (courierMarker == null) {
                    courierMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.car2)).position(latLng));
                } else {
                    MarkerAnimation.animateMarkerToGB(courierMarker, latLng, new LatLngInterpolator.Spherical());
                }
            }

        } else {
            System.out.println("Selesai Order");
        }
    }
}
