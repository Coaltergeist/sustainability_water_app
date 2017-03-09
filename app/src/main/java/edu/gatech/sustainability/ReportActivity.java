package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.WaterReport;

public class ReportActivity extends AppCompatActivity  {

    ArrayList<WaterReport> waterReportList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent intent = getIntent();
        int j = (Integer)intent.getSerializableExtra("reportNum");
        if (j > 0) {
            for (int i = 0; i < j; i++) {
                waterReportList.add((WaterReport)intent.getSerializableExtra("report" + i));
            }
        }
    }

    public void cancelReport(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("reportNum", waterReportList.size());
        if(waterReportList.size() > 0) {
            for(int k = 0; k < waterReportList.size(); k++) {
                intent.putExtra("report" + k, waterReportList.get(k));
            }
        }
        startActivity(intent);
    }

    public void submitReport(View view) {
        String name = ((EditText) findViewById(R.id.nameText)).getText().toString();
        String longitude1 = ((EditText) findViewById(R.id.longText)).getText().toString();
        String latitude1 = ((EditText) findViewById(R.id.latText)).getText().toString();
        double longitude = Double.parseDouble(longitude1);
        double latitude = Double.parseDouble(latitude1);
        String condition = ((EditText) findViewById(R.id.conditionText)).getText().toString();
        String waterType = ((EditText) findViewById(R.id.typeText)).getText().toString();
        WaterReport waterReport = new WaterReport(name, longitude, latitude, condition, waterType);
        waterReportList.add(waterReport);

        new AlertDialog.Builder(this)
                .setTitle("WaterReport Submitted")
                .setMessage("Thank you")
                .setPositiveButton("Back", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("reportNum", waterReportList.size());
                    if(waterReportList.size() > 0) {
                        for(int k = 0; k < waterReportList.size(); k++) {
                            intent.putExtra("waterReport" + k, waterReportList.get(k));
                        }
                    }
                    startActivity(intent);
                })
                .show();
    }
}
