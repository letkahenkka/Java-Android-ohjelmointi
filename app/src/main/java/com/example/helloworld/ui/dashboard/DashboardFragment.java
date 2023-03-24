package com.example.helloworld.ui.dashboard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.databinding.FragmentDashboardBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {


    private FragmentDashboardBinding binding;

    public static final String TAG = "FragmentActivity";
    private static final int REQUEST_FINE_LOCATION = 100;

    LocationManager locationManager;
    LocationListener locationListener;

    TextInputEditText longitudeField, latitudeField, addressField;
    Button locationButton;

    Location lastLocation;
    private String getAddress(Location location){
        String currentAddress = "";
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
            Address address = addresses.get(0);
            currentAddress = address.getAddressLine(0);
        }
        catch (Exception e){
            Log.e("error", "Ei löydy");
        }
        return currentAddress;
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        longitudeField = binding.longitudeField;
        latitudeField = binding.latitudeField;
        addressField = binding.addressField;
        locationButton = binding.locationButton;

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latValue = location.getLatitude();
                String latConv = String.valueOf(latValue);
                double lonValue = location.getLongitude();
                String lonConv = String.valueOf(lonValue);
                latitudeField.setText(latConv);
                longitudeField.setText(lonConv);
                addressField.setText(getAddress(location));
                lastLocation = location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return root;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
        lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastLocation != null){
            latitudeField.setText(Double.toString(lastLocation.getLatitude()));
            longitudeField.setText(Double.toString(lastLocation.getLongitude()));
            addressField.setText(getAddress(lastLocation));
        }

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:" + lastLocation.getLatitude() + "," + lastLocation.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}