package edu.gatech.sustainability;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.sustainability.model.sources.WaterSource;

/**
 * Created by coalt on 4/5/2017.
 */

public class IndividualSourceActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        // source is SourcesActivity.selectedSource;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_source);
    }

    public void returnToList(View view) {
        Intent intent = new Intent(this, SourcesActivity.class);
        startActivity(intent);
    }
}
