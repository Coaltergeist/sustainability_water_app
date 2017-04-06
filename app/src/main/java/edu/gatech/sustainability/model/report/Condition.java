package edu.gatech.sustainability.model.report;

/**
 * Created by Paul on 4/4/2017.
 */

public class Condition {
    public String color;
    public ConditionTypes waterCondition;
    public WaterTypes waterType;

    public Condition() {}
    public Condition(String color, ConditionTypes waterCondition, WaterTypes waterType) {
        this.color = color;
        this.waterCondition = waterCondition;
        this.waterType = waterType;
    }

    @Override
    public String toString() {
        return this.color + ", " + waterCondition.toString() + ", " + waterType;
    }
}
