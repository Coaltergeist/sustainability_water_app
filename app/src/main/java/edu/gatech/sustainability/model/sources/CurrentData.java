package edu.gatech.sustainability.model.sources;

import com.google.firebase.database.PropertyName;

import edu.gatech.sustainability.model.report.ConditionTypes;
import edu.gatech.sustainability.model.report.WaterTypes;

/**
 * Created by Paul on 4/4/2017.
 * Current condition of source
 */

public class CurrentData {
    @PropertyName("waterCondition")
    public ConditionTypes waterCondition;
    public WaterTypes waterType;

    public CurrentData() {

    }

    public CurrentData(ConditionTypes waterCondition, WaterTypes waterType) {
        this.waterCondition = waterCondition;
        this.waterType = waterType;
    }
}
