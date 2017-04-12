package edu.gatech.sustainability.model.report;

/**
 * Created by Paul on 3/27/2017.
 * Condition for quality reports
 */

public enum QualityReportCondition {
    SAFE("Safe"),
    TREATABLE("Treatable"),
    UNSAFE("Unsafe");
    private final String readable;

    QualityReportCondition(String condition) {
        this.readable = condition;
    }

    /**
     * Return this quality report in its readable format
     * @return Readable format
     */
    public String toString() {
        return this.readable;
    }
}
