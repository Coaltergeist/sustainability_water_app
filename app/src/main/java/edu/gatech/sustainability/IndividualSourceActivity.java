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

    public static WaterSource source1;

    protected void onCreate(Bundle savedInstanceState) {
        source1 = SourcesActivity.source1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_source);
        Intent intent = getIntent();
        source1 = (WaterSource) intent.getSerializableExtra("sent source");
    }

    public void returnToList(View view) {
        Intent intent = new Intent(this, SourcesActivity.class);
        startActivity(intent);
    }
}
