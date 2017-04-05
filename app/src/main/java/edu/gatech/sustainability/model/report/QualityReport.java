package edu.gatech.sustainability.model.report;

import com.google.firebase.database.PropertyName;

import edu.gatech.sustainability.MainActivity;

/**
 * Created by Paul on 3/27/2017.
 */

public class QualityReport {

    private String id;
    @PropertyName("userID")
    private String userId;
    @PropertyName("overallCondition")
    private QualityReportCondition condition;
    @PropertyName("contaminantPPM")
    private double contPpm;
    @PropertyName("virusPPM")
    private double virPpm;

    public QualityReport(String reportId, String userId, QualityReportCondition condition, double contPpm, double virPpm) {
        this.id = reportId;
        this.userId = userId;
        this.condition = condition;
        this.contPpm = contPpm;
        this.virPpm = virPpm;
    }

    public String getId() {
        return this.id;
    }

    public QualityReportCondition getCondition() {
        return condition;
    }

    public void setCondition(QualityReportCondition condition) {
        this.condition = condition;
    }

    public double getContPpm() {
        return contPpm;
    }

    public void setContPpm(double contPpm) {
        this.contPpm = contPpm;
    }

    public double getVirPpm() {
        return virPpm;
    }

    public void setVirPpm(double virPpm) {
        this.virPpm = virPpm;
    }
}
