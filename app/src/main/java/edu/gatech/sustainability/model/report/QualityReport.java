package edu.gatech.sustainability.model.report;

import edu.gatech.sustainability.MainActivity;

/**
 * Created by Paul on 3/27/2017.
 */

public class QualityReport {

    private int id;
    private int reportId;
    private QualityReportCondition condition;
    private double contPpm;
    private double virPpm;

    public QualityReport(int reportId, QualityReportCondition condition, double contPpm, double virPpm) {
        this.reportId = reportId;
        this.condition = condition;
        this.contPpm = contPpm;
        this.virPpm = virPpm;
        this.id = MainActivity.qualityReportList.size() + 1;
    }

    public int getId() {
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
