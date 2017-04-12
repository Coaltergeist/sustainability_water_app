package edu.gatech.sustainability.model.report;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import edu.gatech.sustainability.MainActivity;

public class WaterReport implements Serializable{
    private static final long serialVersionUID = 4243488023753902823L;
    @PropertyName("Condition")
    public Condition condition;
    // Epoch of date in seconds
    public long date;
    public String userId;
    private String id;


    /**
     * Default no-arg constructor
     */
    public WaterReport() {

    }

    public WaterReport(Condition condition, long date, String userId) {
        this.condition = condition;
        this.date = date;
        this.userId = userId;
    }

    /**
     * Get this report's ID
     * @return Report ID
     */
    public String getReportId() {
        return this.id;
    }

    /**
     * Return a water report by ID. Null if ID isn't found
     * @param id ID to search for
     * @return WaterReport if found, null if not
     */
    public static WaterReport getReportById(String id) {
        for (WaterReport w : MainActivity.waterReportList) {
            if (w.id.equals(id))
                return w;
        }
        return null;
    }


    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        Date date = new Date(this.date * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.US);
        sdf.setTimeZone(TimeZone.getDefault());
        String formattedDate = sdf.format(date);
        return formattedDate + " | " + condition.toString();
    }
}
