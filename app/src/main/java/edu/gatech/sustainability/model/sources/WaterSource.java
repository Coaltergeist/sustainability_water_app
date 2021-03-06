package edu.gatech.sustainability.model.sources;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.gatech.sustainability.MainActivity;
import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.report.WaterReport;

/**
 * Created by paul on 4/4/2017.
 * Water source model. Contains info on a location
 */

public class WaterSource implements Serializable {
    private static final long serialVersionUID = 2855283883592868005L;
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

    /**
     * Get the ID for this water source
     * @return Water Source ID
     */
    @Exclude
    public String getSourceId() {
        return this.sourceId;
    }

    /**
     * Set the ID for this water source
     * @param sourceId Water Source ID
     */
    @Exclude
    public void setSourceId(String sourceId) { this.sourceId = sourceId; }

    /**
     * Add a quality report to this source and sync to the database
     * @param qualityReport QualityReport to add
     */
    public void addQualityReport(QualityReport qualityReport) {
        waterPurityReports.add(qualityReport);
        MainActivity.reportDatabase.child(this.getSourceId()).child("WaterPurityReports").push().setValue(qualityReport);
    }

    /**
     * Add a water report and update the database
     * @param report Report to add to database
     */
    public void addWaterReport(WaterReport report) {
        waterReports.add(report);
        MainActivity.reportDatabase.child(this.getSourceId()).child("WaterReports").push().setValue(report);
    }

    @Override
    public String toString() {
        if (name == null || name.isEmpty()) {
            return String.format(Locale.US, "%.00f | %.00f", coordinates.latitude, coordinates.longitude);
        }
        return name;
    }

    /**
     * Get a water source by its ID
     * @param id ID to search for
     * @return WaterSource if found, null if not
     */
    public static WaterSource getWaterSourceById(String id) {
        for (WaterSource source : MainActivity.waterSources) {
            if (source.getSourceId().equals(id)) {
                return source;
            }
        }
        return null;
    }
}
