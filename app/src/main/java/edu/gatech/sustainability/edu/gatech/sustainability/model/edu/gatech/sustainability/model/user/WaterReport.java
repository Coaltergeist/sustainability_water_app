package edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user;

/**
 * Created by coalt on 3/2/2017.
 */
import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class WaterReport implements Serializable{
    private String name;
    private double longitude;
    private double latitude;
    private String condition;
    private String waterType;

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
    }

    public String getName() { return name; }
    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }
    public String getCondition() { return condition; }
    public String getWaterType() { return waterType; }

    public void setName(String name) { this.name = name; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setCondition(String condition) { this.condition = condition; }
    public void setWaterType(String waterType) { this.waterType = waterType; }

    public String toString() {
        String printable = "";
        printable += name + "'s report: \n";
        printable += condition + " condition " + waterType + " water located at " + longitude + " "
                + latitude + "\n";
        return printable;
    }
}
