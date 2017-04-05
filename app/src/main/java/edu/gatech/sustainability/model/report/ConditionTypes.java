package edu.gatech.sustainability.model.report;

/**
 * Created by Paul on 4/4/2017.
 */

public enum ConditionTypes {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable-clear"),
    TREATABLE_MUDDY("Treatable-muddy"),
    POTABLE("Potable");
    private String readable;

    private ConditionTypes(String readable) {
        this.readable = readable;
    }

    @Override
    public String toString() {
        return this.readable;
    }

}
