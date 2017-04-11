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

    /**
     * Set condition
     * @param condition Condition to set
     */
    public void setCondition(QualityReportCondition condition) {
        this.condition = condition;
    }

    /**
     * Get contaminant PPM
     * @return Contaminant PPM
     */
    public double getContPpm() {
        return contPpm;
    }

    /**
     * Set contaminant PPM
     * @param contPpm Contaminant PPM
     */
    public void setContPpm(double contPpm) {
        this.contPpm = contPpm;
    }

    /**
     * Get virus PPM
     * @return Virus PPM
     */
    public double getVirPpm() {
        return virPpm;
    }

    /**
     * Set virus PPM
     * @param virPpm Virus PPM
     */
    public void setVirPpm(double virPpm) {
        this.virPpm = virPpm;
    }

    /**
     * Get creation date of this report
     * @return Long of epoch in seconds of date
     */
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
