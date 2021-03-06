package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.sustainability.model.report.Condition;
import edu.gatech.sustainability.model.report.WaterReport;
import edu.gatech.sustainability.model.report.WaterTypes;
import edu.gatech.sustainability.model.sources.Coordinates;
import edu.gatech.sustainability.model.sources.CurrentData;
import edu.gatech.sustainability.model.report.ConditionTypes;
import edu.gatech.sustainability.model.sources.WaterSource;

public class ReportActivity extends AppCompatActivity  {

    private Coordinates coordinates;
    private CurrentData currentData;
    Spinner conditionSpinner;
    Spinner typeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        ArrayAdapter<ConditionTypes> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ConditionTypes.values());
        conditionSpinner.setAdapter(adapter1);

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        ArrayAdapter<WaterTypes> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, WaterTypes.values());
        typeSpinner.setAdapter(adapter2);
    }

    /**
     * Cancel report and return to main screen
     */
    public void cancelReport(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private String sourceName = "";

    /**
     * Submit this water report and save it to the database.
     * <p>
     * If the coordinates do not exist as a water source, then prompt the user to enter
     * a name for a new water source and add it to the database
     */
    public void submitReport(View view) {
        String longitude1 = ((EditText) findViewById(R.id.longText)).getText().toString();
        String latitude1 = ((EditText) findViewById(R.id.latText)).getText().toString();
        double longitude = Double.parseDouble(longitude1);
        double latitude = Double.parseDouble(latitude1);
        String color = ((EditText) findViewById(R.id.colorText)).getText().toString();
        // String condition = conditionSpinner.getSelectedItem().toString();
        // String waterType = typeSpinner.getSelectedItem().toString();
        ConditionTypes conditionType = (ConditionTypes) conditionSpinner.getSelectedItem();
        WaterTypes waterType = (WaterTypes) typeSpinner.getSelectedItem();

        Condition condition = new Condition(color, conditionType, waterType);
        coordinates = new Coordinates(longitude, latitude);
        currentData = new CurrentData(conditionType, waterType);
        WaterReport report = new WaterReport(condition, System.currentTimeMillis() / 1000,
                MainActivity.currentUser.getUserId());
        WaterSource source = preexistingSource(coordinates);
        if (source != null) {
            source.currentData = currentData;
            source.addWaterReport(report);
            showSuccess();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name of this location");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> {
            sourceName = input.getText().toString();
            String sourceId = MainActivity.reportDatabase.push().getKey();
            WaterSource source1 = new WaterSource(sourceId, coordinates, currentData);
            source1.waterReports.add(report);
            source1.name = sourceName;
            source1.discoveredBy = MainActivity.currentUser.name;
            MainActivity.reportDatabase.push().setValue(source1);
            MainActivity.waterSources.add(source1);
            showSuccess();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();

    }

    /**
     * Check if a preexisting source already exists
     * @param coordinates Coordinates to check
     * @return WaterSource if found, null if not
     */
    private WaterSource preexistingSource(Coordinates coordinates) {
        for (WaterSource source : MainActivity.waterSources) {
            if (source.coordinates.equals(coordinates)) {
                // We have a source already, just add this report to its list
                return source;
            }
        }
        return null;
    }

    /**
     * Show success dialog box
     */
    private void showSuccess() {
        new AlertDialog.Builder(this)
                .setTitle("Water Report Submitted")
                .setMessage("Thank you")
                .setPositiveButton("Back", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })
                .show();
    }

}
