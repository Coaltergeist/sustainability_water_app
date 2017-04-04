package edu.gatech.sustainability.model.report;

/**
 * Created by coalt on 3/2/2017.
 */
import com.google.firebase.database.PropertyName;

import java.io.Serializable;

import edu.gatech.sustainability.MainActivity;
import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.Coordinates;

public class WaterReport implements Serializable {
    private static final long serialVersionUID = 3802465091755147005L;
    @PropertyName("Condition")
    public Condition condition;
    public String date;
    public String userId;
    private String id;

    class Condition {
        public String color;
        public String waterCondition;
        public String waterType;

        public Condition() {}
        public Condition(String color, String waterCondition, String waterType) {
            this.color = color;
            this.waterCondition = waterCondition;
            this.waterType = waterType;
        }
    }
    /**
     * Default no-arg constructor
     */
    public WaterReport() {

    }

    public WaterReport(String date, String userId) {
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
