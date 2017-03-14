package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.WaterReport;
import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.User;

public class MainActivity extends AppCompatActivity {

    public static Map<Integer, User> userSet = new HashMap<>();
    public static User currentUser;
    public static ArrayList<WaterReport> waterReportList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (currentUser == null)
            currentUser = userSet.get(intent.getIntExtra("userId", -1));
        ((TextView) findViewById(R.id.welcomeTextView)).setText("Welcome, " + currentUser.getUsername());
        if (intent.getSerializableExtra("reportNum") != null) {
            int j = (Integer) intent.getSerializableExtra("reportNum");
            if (j > 0) {
                for (int i = 0; i < j; i++) {
                    waterReportList.add((WaterReport) intent.getSerializableExtra("report" + i));
                }
            }
        }
    }

    /**
     * Button action for logging out. Clear user and return to splash/login screen
     * @param view View this was done from
     */
    public void logout(View view) {
        currentUser = null;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * View user profile and allow them to edit from said view
     * @param view View this was done from
     */
    public void viewProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Show the map of water reports
     * @param view View this was done from
     */
    public void showMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * Start a report
     * @param view View this was done from
     */
    public void startReport(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        intent.putExtra("reportNum", waterReportList.size());
        if(waterReportList.size() > 0) {
            for(int k = 0; k < waterReportList.size(); k++) {
                intent.putExtra("report" + k, waterReportList.get(k));
            }
        }
        startActivity(intent);
    }

    /**
     * Show reports
     * @param view View this was done from
     */
    public void showReports(View view) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < waterReportList.size(); i++) {
           sb.append((waterReportList.get(i)).toString() + "\n");
        }
        new AlertDialog.Builder(this)
                .setTitle("Reports Submitted:")
                .setMessage(sb.toString())
                .setPositiveButton("close", (dialogInterface, i) -> {
                })
                .show();
    }
}
