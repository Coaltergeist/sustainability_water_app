package edu.gatech.sustainability.model.sources;

import com.google.firebase.database.PropertyName;

import java.util.List;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.Coordinates;
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
    public String discoveredBy;
    public String name;

    public class CurrentData {
        public String waterCondition;
        public String waterType;
        public CurrentData() {}
        public CurrentData(String waterCondition, String waterType) {
            this.waterCondition = waterCondition;
            this.waterType = waterType;
        }
    }

    public WaterSource() {

    }

    public WaterSource(Coordinates coordinates, List<QualityReport> waterPurityReports, List<WaterReport> waterReports, CurrentData currentData) {
        this.coordinates = coordinates;
        this.waterPurityReports = waterPurityReports;
        this.waterReports = waterReports;
        this.currentData = currentData;
    }
}
