package edu.gatech.sustainability.model.sources;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.sustainability.MainActivity;
import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.report.WaterReport;

/**
 * Created by paul on 4/4/2017.
 */

public class WaterSource implements Serializable {
    @Exclude
    private String sourceId;

    @PropertyName("Coordinates")
    public Coordinates coordinates;
    @PropertyName("WaterPurityReports")
    public List<QualityReport> waterPurityReports = new ArrayList<>();
    @PropertyName("WaterReports")
    public List<WaterReport> waterReports = new ArrayList<>();
    @PropertyName("currentData")
    public CurrentData currentData;
    // user name that discovered
    public String discoveredBy;
    // name of the source
    public String name;



    public WaterSource() {

    }

    public WaterSource(String sourceId, Coordinates coordinates, CurrentData currentData) {
        this.sourceId = sourceId;
        this.coordinates = coordinates;
        this.currentData = currentData;
    }

    public WaterSource(String sourceId, Coordinates coordinates, List<QualityReport> waterPurityReports,
                       List<WaterReport> waterReports, CurrentData currentData) {
        this.sourceId = sourceId;
        this.coordinates = coordinates;
        this.waterPurityReports = waterPurityReports;
        this.waterReports = waterReports;
        this.currentData = currentData;
    }

    public String getSourceId() {
        return this.sourceId;
    }
    public void setSourceId(String sourceId) { this.sourceId = sourceId; }

    public void addQualityReport(QualityReport qualityReport) {
        waterPurityReports.add(qualityReport);
        MainActivity.reportDatabase.child(this.getSourceId()).child("WaterPurityReports").setValue(waterReports);
    }

    /**
     * Add a water report and update the database
     * @param report
     */
    public void addWaterReport(WaterReport report) {
        waterReports.add(report);
        MainActivity.reportDatabase.child(this.getSourceId()).child("WaterReports").setValue(waterReports);
    }

    @Override
    public String toString() {
        if (name == null || name.isEmpty()) {
            return String.format("%.00f | %.00f", coordinates.latitude, coordinates.longitude);
        }
        return name;
    }
    
    public static WaterSource getWaterSourceById(String id) {
        for (WaterSource source : MainActivity.waterSources) {
            if (source.getSourceId().equals(id)) {
                return source;
            }
        }
        return null;
    }
}
