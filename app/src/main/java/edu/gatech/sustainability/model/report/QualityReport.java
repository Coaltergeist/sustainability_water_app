package edu.gatech.sustainability.model.report;

import com.google.firebase.database.PropertyName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    private long date;

    public QualityReport() {

    }
    public QualityReport(String userId, QualityReportCondition condition, double contPpm, double virPpm) {
        // this.id = reportId;
        this.userId = userId;
        this.condition = condition;
        this.contPpm = contPpm;
        this.virPpm = virPpm;
        this.date = System.currentTimeMillis() / 1000;
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

    public long getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        Date date = new Date(this.date * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy h:mm a");
        sdf.setTimeZone(TimeZone.getDefault());
        String formattedDate = sdf.format(date);
        if (this.condition == null)
            return formattedDate;
        return formattedDate + " | " + this.condition.toString();
    }
}
