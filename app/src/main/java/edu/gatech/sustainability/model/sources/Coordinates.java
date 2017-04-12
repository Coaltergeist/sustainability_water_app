package edu.gatech.sustainability.model.sources;

import com.google.firebase.database.PropertyName;

/**
 * Created by p on 4/4/2017.
 * Coordinates for sources
 */

public class Coordinates {
    @PropertyName("Latitude")
    public double latitude;
    @PropertyName("Longitude")
    public double longitude;

    public Coordinates() {

    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Coordinates)) {
            return false;
        }
        final Coordinates other = (Coordinates) obj;
        return !(other.latitude != this.latitude && other.longitude != this.longitude);
    }
}
