package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.gatech.sustainability.model.sources.WaterSource;

/**
 * Created by coalt on 4/5/2017.
 * Source controller
 */

public class SourcesActivity extends AppCompatActivity {
    public static WaterSource selectedSource;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        spinner = (Spinner) findViewById(R.id.sourceSpinner);
        //for loop to set spinner contents to be added
        //test code below
        ArrayAdapter<WaterSource> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MainActivity.waterSources);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    /**
     * Show an individual source
     */
    public void viewIndividualSource(View view) {
        selectedSource = (WaterSource) spinner.getSelectedItem();
        if (selectedSource != null) {
            Intent intent = new Intent(this, IndividualSourceActivity.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("No water source selected")
                    .setMessage("Please choose a water source.")
                    .setPositiveButton("close", (dialogInterface, i) -> {
                    })
                    .show();
        }
    }

    /**
     * View the history of a source
     */
    public void viewHistory(View view) {
        selectedSource = (WaterSource) spinner.getSelectedItem();
        if (selectedSource != null) {
            Intent intent = new Intent(this, GraphActivity.class);
            intent.putExtra("id", selectedSource.getSourceId());
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("No water source selected")
                    .setMessage("Please choose a water source.")
                    .setPositiveButton("close", (dialogInterface, i) -> {
                    })
                    .show();
        }
    }

    /**
     * Cancel and return to main screen
     */
    public void cancelSourceView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
