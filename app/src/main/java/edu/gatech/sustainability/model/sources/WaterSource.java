package edu.gatech.sustainability.model.sources;

import com.google.firebase.database.PropertyName;

import java.util.List;

import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.report.WaterReport;

/**
 * Created by p on 4/4/2017.
 */

public class WaterSource {
    @PropertyName("Coordinates")
    public Coordinates coordinates;
    @PropertyName("WaterPurityReports")
    public List<QualityReport> waterPurityReports;
    @PropertyName("WaterReports")
    public List<WaterReport> waterReports;
    @PropertyName("currentData")
    public CurrentData currentData;
    // user name that discovered
    public String discoveredBy;
    // name of the source
    public String name;



    public WaterSource() {

    }

    public WaterSource(Coordinates coordinates, List<QualityReport> waterPurityReports, List<WaterReport> waterReports, CurrentData currentData) {
        this.coordinates = coordinates;
        this.waterPurityReports = waterPurityReports;
        this.waterReports = waterReports;
        this.currentData = currentData;
    }
}
