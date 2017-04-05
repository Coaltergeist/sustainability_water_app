package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import edu.gatech.sustainability.model.report.ConditionTypes;
import edu.gatech.sustainability.model.report.WaterReport;
import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.report.QualityReportCondition;


public class QualityReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_report);
        ArrayAdapter<WaterReport> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, MainActivity.waterReportList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner locationItems = (Spinner) findViewById(R.id.spinner3);
        locationItems.setAdapter(adapter);

        List<QualityReportCondition> conditionList = Arrays.asList(QualityReportCondition.values());
        ArrayAdapter<QualityReportCondition> qAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, conditionList);

        qAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner qualityItems = (Spinner) findViewById(R.id.spinner);
        qualityItems.setAdapter(qAdapter);

    }

    public void submitQualityReport(View view) {
        Spinner locationItems = (Spinner) findViewById(R.id.spinner3);
        WaterReport location =  (WaterReport) locationItems.getSelectedItem();

        Spinner conditionItems = (Spinner) findViewById(R.id.spinner);
        QualityReportCondition condition = (QualityReportCondition) conditionItems.getSelectedItem();

        double contaminant = Double.parseDouble(((EditText) findViewById(R.id.contaminantPPM))
                .getText().toString());
        double virus = Double.parseDouble(((EditText) findViewById(R.id.virusPPM))
                .getText().toString());
        QualityReport q = new QualityReport(location.getId(), condition, contaminant, virus);
        MainActivity.qualityReportList.add(q);
        new AlertDialog.Builder(this)
                .setTitle("Quality Report Submitted")
                .setMessage("Thank you")
                .setPositiveButton("Back", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })
                .show();
    }
}
