package com.example.apnakissan;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Maps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        searchBar = findViewById(R.id.search_bar);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Initialize permission launcher for location permission
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        // Permission granted
                        getCurrentLocation();
                    } else {
                        Toast.makeText(Maps.this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true); // Enable "My Location" button
        getCurrentLocation();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Launch permission request
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            return;
        }

        mMap.setMyLocationEnabled(true); // Show blue dot for user's location

        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

                    // Move the camera to the user's location
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14));

                    // Add a custom marker at the user's location
                    mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));

                    loadNearbyShops(userLocation);
                } else {
                    Toast.makeText(Maps.this, "Unable to get location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadNearbyShops(LatLng userLocation) {
        LatLng shop1 = new LatLng(userLocation.latitude + 0.02, userLocation.longitude + 0.02);
        LatLng shop2 = new LatLng(userLocation.latitude - 0.02, userLocation.longitude - 0.02);

        mMap.addMarker(new MarkerOptions().position(shop1).title("Zain Agri Traders"));
        mMap.addMarker(new MarkerOptions().position(shop2).title("Malik Sapray Center"));
    }
}


