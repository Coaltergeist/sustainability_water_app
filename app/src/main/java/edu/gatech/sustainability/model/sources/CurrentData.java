package edu.gatech.sustainability.model.sources;

/**
 * Created by Paul on 4/4/2017.
 */

public class CurrentData {
    public String waterCondition;
    public String waterType;

    public CurrentData() {

    }

    public CurrentData(String waterCondition, String waterType) {
        this.waterCondition = waterCondition;
        this.waterType = waterType;
    }
}
