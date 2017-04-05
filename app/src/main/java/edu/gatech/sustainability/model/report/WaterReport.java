package edu.gatech.sustainability.model.report;

/**
 * Created by coalt on 3/2/2017.
 */
import com.google.firebase.database.PropertyName;

import java.io.Serializable;

import edu.gatech.sustainability.MainActivity;

public class WaterReport {
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

    public WaterReport(long date, String userId) {
        this.date = date;
        this.userId = userId;
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
}
