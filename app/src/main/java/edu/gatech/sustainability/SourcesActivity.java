package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.sustainability.model.sources.WaterSource;

/**
 * Created by coalt on 4/5/2017.
 */

public class SourcesActivity extends AppCompatActivity {

    Spinner spinner;
    public static WaterSource source1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        Intent intent = getIntent();
        spinner = (Spinner) findViewById(R.id.sourceSpinner);
        //for loop to set spinner contents to be added
        //test code below
        ArrayAdapter<WaterSource> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MainActivity.waterSources);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void viewIndividualSource(View view) {
        source1 = (WaterSource) spinner.getSelectedItem();
        if (source1 != null) {
            Intent intent = new Intent(this, IndividualSourceActivity.class);
            //intent.putExtra("sent source", source1);
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

    public void cancelSourceView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
