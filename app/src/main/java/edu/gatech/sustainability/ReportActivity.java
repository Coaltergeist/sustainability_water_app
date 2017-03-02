package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.Report;
import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.User;

public class ReportActivity extends AppCompatActivity  {

    ArrayList<Report> reportList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent intent = getIntent();
        int j = (Integer)intent.getSerializableExtra("reportNum");
        if (j > 0) {
            for (int i = 0; i < j; i++) {
                reportList.add((Report)intent.getSerializableExtra("report" + i));
            }
        }
    }

    public void cancelReport(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("reportNum", reportList.size());
        if(reportList.size() > 0) {
            for(int k = 0; k < reportList.size(); k++) {
                intent.putExtra("report" + k, reportList.get(k));
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
        Report report = new Report(name, longitude, latitude, condition, waterType);
        reportList.add(report);

        new AlertDialog.Builder(this)
                .setTitle("Report Submitted")
                .setMessage("Thank you")
                .setPositiveButton("Back", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("reportNum", reportList.size());
                    if(reportList.size() > 0) {
                        for(int k = 0; k < reportList.size(); k++) {
                            intent.putExtra("report" + k, reportList.get(k));
                        }
                    }
                    startActivity(intent);
                })
                .show();
    }
}
