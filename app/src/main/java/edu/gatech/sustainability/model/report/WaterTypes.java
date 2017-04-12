package edu.gatech.sustainability.model.report;

/**
 * Created by Paul on 4/4/2017.
 * Type of water
 */

public enum WaterTypes {
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");
    private final String type;

    WaterTypes(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
