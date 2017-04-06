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

import edu.gatech.sustainability.model.report.WaterReport;
import edu.gatech.sustainability.model.sources.WaterSource;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

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
                .setTitle(String.format("%s\n%f | %f", waterSource.name,
                        waterSource.coordinates.latitude, waterSource.coordinates.longitude))
                .setMessage(String.format("Condition: %s\nType: %s",
                        waterSource.currentData.waterCondition, waterSource.currentData.waterType))
                .setPositiveButton("Back", (dialogInterface, i) -> { })
                .show();

        return false;
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
        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);

        for (WaterSource source : MainActivity.waterSources) {
            LatLng latLng = new LatLng(source.coordinates.latitude, source.coordinates.longitude);
            Marker m = mMap.addMarker(new MarkerOptions().position(latLng)
                    .title(String.format("%s\n%.00f | %.00f", source.name, latLng.latitude, latLng.longitude)));
            m.setTag(source);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
        /*mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }


}
