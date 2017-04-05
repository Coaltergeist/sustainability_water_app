package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.sustainability.model.report.Condition;
import edu.gatech.sustainability.model.report.ConditionTypes;
import edu.gatech.sustainability.model.report.QualityReportCondition;
import edu.gatech.sustainability.model.report.WaterTypes;
import edu.gatech.sustainability.model.sources.Coordinates;
import edu.gatech.sustainability.model.sources.CurrentData;
import edu.gatech.sustainability.model.user.UserType;
import edu.gatech.sustainability.model.report.WaterReport;
import edu.gatech.sustainability.model.user.User;
import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.sources.WaterSource;

public class MainActivity extends AppCompatActivity {

    public static Map<Integer, User> userSet = new HashMap<>();
    public static User currentUser;
    public static List<WaterReport> waterReportList = new ArrayList<>();
    public static List<QualityReport> qualityReportList = new ArrayList<>();
    public static List<WaterSource> waterSources = new ArrayList<>();

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference userDatabase = database.getReference("Users");
    public static DatabaseReference reportDatabase = database.getReference("WaterSources");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // String key = reportDatabase.push().getKey();
        //reportDatabase.child(key)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        Button qualityReportButton = (Button) findViewById(R.id.submitQualityReport);
        if (currentUser.getUserType().equals(UserType.NORMALUSER)) {
            qualityReportButton.setVisibility(View.GONE);
        } else {
            qualityReportButton.setVisibility(View.VISIBLE);
        }
        ((TextView) findViewById(R.id.welcomeTextView)).setText("Welcome, " + currentUser.getUsername());
        if (intent.getSerializableExtra("reportNum") != null) {
            int j = (Integer) intent.getSerializableExtra("reportNum");
            if (j > 0) {
                for (int i = 0; i < j; i++) {
                    waterReportList.add((WaterReport) intent.getSerializableExtra("report" + i));
                }
            }
        }
        ValueEventListener sourceListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                waterSources.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        WaterSource source = snapshot.getValue(WaterSource.class);
                        source.setSourceId(snapshot.getKey());
                        waterSources.add(source);
                        Log.e("this", source.getSourceId() + " id");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Log.e("this2", waterSources.size() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("loaderror", "loadPost:onCancelled", databaseError.toException());
            }
        };
        reportDatabase.addValueEventListener(sourceListener);
        /*WaterSource ws = new WaterSource();
        ws.coordinates = new Coordinates(10, 10);
        ws.waterPurityReports.add(new QualityReport("abc", "123", QualityReportCondition.SAFE, .005, .005));
        WaterReport wr = new WaterReport("abc", 123123123, "");
        wr.condition = new Condition();
        wr.condition.color = "blue";
        wr.condition.waterCondition = ConditionTypes.POTABLE;
        wr.condition.waterType = WaterTypes.BOTTLED;
        ws.waterReports.add(wr);
        ws.currentData = new CurrentData("Good", "Bottled");
        ws.discoveredBy = "Paul";
        ws.name = "Tone River";
        reportDatabase.push().setValue(ws);*/
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
                intent.putExtra("report" + k, waterReportList.get(k).toString());
            }
        }
        startActivity(intent);
    }

    /**
     * Submit a quality report
     * @param view View this was done from
     */
    public void submitQualityReport(View view) {
        if (waterSources.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("No water reports have been submitted yet")
                    .setPositiveButton("close", (dialogInterface, i) -> {
                    })
                    .show();
            return;
        }
        Intent intent = new Intent(this, QualityReportActivity.class);
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
        reportDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Log.e("key", snap.getKey());
                    String key = snap.getKey();
                    String name = (String) snap.child("name").getValue();
                    String discoverer = (String) snap.child("discoveredBy").getValue();

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error);
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("Reports Submitted:")
                .setMessage(sb.toString())
                .setPositiveButton("close", (dialogInterface, i) -> {
                })
                .show();
    }

    public void showQualityReports(View view) {
        StringBuilder sb = new StringBuilder();
        /*for (QualityReport q : qualityReportList) {
            WaterReport w = WaterReport.getReportById(q.getId());
            sb.append(String.format("%d) %s | Lat: %.2f Long: %.2f\nVirus PPM: %.2f Contaminant PPM: %.2f",
                    q.getId(), q.getCondition().toString(), , w.getLongitude(), q.getVirPpm(), q.getContPpm()));
        }
        new AlertDialog.Builder(this)
                .setTitle("Reports Submitted:")
                .setMessage(sb.toString())
                .setPositiveButton("close", (dialogInterface, i) -> {
                })
                .show();*/
    }

    public void viewSources(View view) {
        Intent intent = new Intent(this, SourcesActivity.class);
        startActivity(intent);
    }
}
