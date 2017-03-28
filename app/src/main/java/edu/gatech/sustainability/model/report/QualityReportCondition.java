package edu.gatech.sustainability.model.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 3/27/2017.
 */

public enum QualityReportCondition {
    SAFE("Safe"),
    TREATABLE("Treatable"),
    UNSAFE("Unsafe");
    private String readable;

    private QualityReportCondition(String condition) {
        this.readable = condition;
    }

    public String toString() {
        return this.readable;
    }
}
