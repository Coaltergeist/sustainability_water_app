package edu.gatech.sustainability;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import edu.gatech.sustainability.model.sources.WaterSource;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        WaterSource waterSource = (WaterSource) marker.getTag();
        new AlertDialog.Builder(this)
                .setTitle(String.format(Locale.US, "%s\n%f | %f", waterSource.name,
                        waterSource.coordinates.latitude, waterSource.coordinates.longitude))
                .setMessage(String.format(Locale.US, "Condition: %s\nType: %s",
                        waterSource.currentData.waterCondition, waterSource.currentData.waterType))
                .setPositiveButton("Back", (dialogInterface, i) -> { })
                .show();

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);

        for (WaterSource source : MainActivity.waterSources) {
            LatLng latLng = new LatLng(source.coordinates.latitude, source.coordinates.longitude);
            Marker m = googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(String.format(Locale.US, "%s\n%.00f | %.00f", source.name, latLng.latitude, latLng.longitude)));
            m.setTag(source);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


}
