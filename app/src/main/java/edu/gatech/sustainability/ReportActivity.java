package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.report.WaterReport;
import edu.gatech.sustainability.model.report.WaterTypes;
import edu.gatech.sustainability.model.sources.Coordinates;
import edu.gatech.sustainability.model.sources.CurrentData;
import edu.gatech.sustainability.model.report.ConditionTypes;
import edu.gatech.sustainability.model.sources.WaterSource;

public class ReportActivity extends AppCompatActivity  {

    ArrayList<WaterSource> waterSourceList = new ArrayList<>();

    private String sourceId;
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
                android.R.layout.simple_spinner_item,WaterTypes.values());
        typeSpinner.setAdapter(adapter2);
    }

    public void cancelReport(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submitReport(View view) {
        String longitude1 = ((EditText) findViewById(R.id.longText)).getText().toString();
        String latitude1 = ((EditText) findViewById(R.id.latText)).getText().toString();
        double longitude = Double.parseDouble(longitude1);
        double latitude = Double.parseDouble(latitude1);
        String condition = conditionSpinner.getSelectedItem().toString();
        String waterType = typeSpinner.getSelectedItem().toString();

        coordinates = new Coordinates(longitude, latitude);
        currentData = new CurrentData(condition, waterType);

        WaterSource source1 = new WaterSource(sourceId, coordinates, currentData);

        MainActivity.waterSources.add(source1);

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
