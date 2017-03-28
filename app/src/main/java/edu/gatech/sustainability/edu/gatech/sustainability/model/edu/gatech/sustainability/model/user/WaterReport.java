package edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user;

/**
 * Created by coalt on 3/2/2017.
 */
import java.io.Serializable;

import edu.gatech.sustainability.MainActivity;

public class WaterReport implements Serializable {
    private static final long serialVersionUID = 3802465091755147005L;
    private String name;
    private double longitude;
    private double latitude;
    private String condition;
    private String waterType;
    private int id;

    /**
     * Default no-arg constructor
     */
    public WaterReport() {

    }

    /**
     *
     * @param name name to register
     * @param longitude
     * @param latitude
     * @param condition
     * @param waterType
     */
    public WaterReport(String name, double longitude, double latitude,
                       String condition, String waterType) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.condition = condition;
        this.waterType = waterType;
        this.id = MainActivity.waterReportList.size() + 1;
    }

    public String getName() { return name; }
    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }
    public String getCondition() { return condition; }
    public String getWaterType() { return waterType; }
    public int getId() { return this.id; }

    public void setName(String name) { this.name = name; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setCondition(String condition) { this.condition = condition; }
    public void setWaterType(String waterType) { this.waterType = waterType; }

    public String toString() {
        return String.format("%d: %f | %f", this.id, this.latitude, this.longitude);
    }

    /**
     * Return a water report by ID. Null if ID isn't found
     * @param id ID to search for
     * @return WaterReport if found, null if not
     */
    public static WaterReport getReportById(int id) {
        for (WaterReport w : MainActivity.waterReportList) {
            if (w.getId() == id)
                return w;
        }
        return null;
    }
}
