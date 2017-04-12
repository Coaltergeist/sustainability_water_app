package edu.gatech.sustainability;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.report.WaterReport;

/**
 * Created by coalt on 4/5/2017.
 * Individual source activity controller
 */

public class IndividualSourceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // source is SourcesActivity.selectedSource;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_source);

        ListView waterReportList = (ListView) findViewById(R.id.listView);
        ListView qualityReportList = (ListView) findViewById(R.id.listView2);

        ArrayAdapter<WaterReport> waterReportArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, SourcesActivity.selectedSource.waterReports);
        ArrayAdapter<QualityReport> qualityReportArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, SourcesActivity.selectedSource.waterPurityReports);

        waterReportList.setAdapter(waterReportArrayAdapter);
        qualityReportList.setAdapter(qualityReportArrayAdapter);
    }

    /**
     * Return to main listing of sources
     */
    public void returnToList(View view) {
        Intent intent = new Intent(this, SourcesActivity.class);
        startActivity(intent);
    }

}
