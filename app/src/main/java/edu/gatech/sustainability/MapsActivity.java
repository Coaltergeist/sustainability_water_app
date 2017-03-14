package edu.gatech.sustainability;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.WaterReport;

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
        WaterReport waterReport = (WaterReport) marker.getTag();
        new AlertDialog.Builder(this)
                .setTitle(String.format("%f | %f",
                        waterReport.getLatitude(), waterReport.getLongitude()))
                .setMessage(String.format("Type: %s\nCondition: %s\nReported by: %s",
                        waterReport.getWaterType(), waterReport.getCondition(), waterReport.getName()))
                .setPositiveButton("Back", (dialogInterface, i) -> { })
                .show();

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
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

        for (WaterReport w : MainActivity.waterReportList) {
            LatLng latLng = new LatLng(w.getLatitude(), w.getLongitude());
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(String.format("%f | %f", latLng.latitude, latLng.longitude)));

            m.setTag(w);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
        /*mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }


}
