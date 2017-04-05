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
    private int reportId;
    @PropertyName("overallCondition")
    private QualityReportCondition condition;
    @PropertyName("contaminantPPM")
    private double contPpm;
    @PropertyName("virusPPM")
    private double virPpm;

    public QualityReport(String userId, QualityReportCondition condition, double contPpm, double virPpm) {
        this.userId = userId;
        this.condition = condition;
        this.contPpm = contPpm;
        this.virPpm = virPpm;
        // TODO: do something about the id
        this.id = "";
    }

    public String getId() {
        return this.id;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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
