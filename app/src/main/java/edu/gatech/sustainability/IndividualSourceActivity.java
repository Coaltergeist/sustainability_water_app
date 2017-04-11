package edu.gatech.sustainability;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.report.WaterReport;
import edu.gatech.sustainability.model.sources.WaterSource;

/**
 * Created by coalt on 4/5/2017.
 */

public class IndividualSourceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // source is SourcesActivity.selectedSource;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_source);

        ListView waterReportList = (ListView) findViewById(R.id.listView);
        ListView qualityReportList = (ListView) findViewById(R.id.listView2);

        ArrayAdapter<WaterReport> waterReportArrayAdapter = new ArrayAdapter<WaterReport>(this,
                android.R.layout.simple_list_item_1, SourcesActivity.selectedSource.waterReports);
        ArrayAdapter<QualityReport> qualityReportArrayAdapter = new ArrayAdapter<QualityReport>(this,
                android.R.layout.simple_list_item_1, SourcesActivity.selectedSource.waterPurityReports);

        waterReportList.setAdapter(waterReportArrayAdapter);
        qualityReportList.setAdapter(qualityReportArrayAdapter);
    }

    /**
     * Return to main listing of sources
     * @param view View originated from
     */
    public void returnToList(View view) {
        Intent intent = new Intent(this, SourcesActivity.class);
        startActivity(intent);
    }
}
