package edu.gatech.sustainability.model.sources;

import com.google.firebase.database.PropertyName;

/**
 * Created by p on 4/4/2017.
 */

public class Coordinates {
    @PropertyName("Latitude")
    public double latitude;
    @PropertyName("Longitude")
    public double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
