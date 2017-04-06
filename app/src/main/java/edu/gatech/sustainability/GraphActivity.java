package edu.gatech.sustainability;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.sources.WaterSource;
public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        GraphView graph2 = (GraphView) findViewById(R.id.graph2);
        graph.setTitle("Contaminant historical");
        graph2.setTitle("Virus historical");
        //graph.getViewport().setMaxY(1);

        String id = (String) getIntent().getSerializableExtra("id");
        WaterSource source = null;
        for (WaterSource w : MainActivity.waterSources) {
            if (w.getSourceId().equals(id)) {
                source = w;
                Log.e("graph", "Got here! " + source.waterPurityReports.size());
            }
        }
        if (source != null) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>();
            List<QualityReport> qualityReports = source.waterPurityReports;
            qualityReports.sort((q1, q2) -> (int) (q1.getDate() - q2.getDate()));
            for (int i = 0; i < qualityReports.size(); i++) {
                series.appendData(new DataPoint(qualityReports.get(i).getDate() * 1000,
                        qualityReports.get(i).getContPpm()), true, 100);
                series2.appendData(new DataPoint(qualityReports.get(i).getDate() * 1000,
                        qualityReports.get(i).getVirPpm()), true, 100);
                Log.e("Cont", qualityReports.get(i).getContPpm() + "");
                //contPoints.add(new PointValue(i, (float) qualityReports.get(i).getContPpm()));
                //virusPoints.add(new PointValue(i, (float) qualityReports.get(i).getVirPpm()));
            }
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
            graph2.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
            graph.addSeries(series);
            graph2.addSeries(series2);
            if (!qualityReports.isEmpty()) {
                graph.getViewport().setMinX(qualityReports.get(0).getDate() * 1000);
                graph.getViewport().setMaxX(qualityReports.get(qualityReports.size() - 1).getDate() * 1000);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getGridLabelRenderer().setHumanRounding(false);
                graph2.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
                graph2.getViewport().setMinX(qualityReports.get(0).getDate() * 1000);
                graph2.getViewport().setMaxX(qualityReports.get(qualityReports.size() - 1).getDate() * 1000);
                graph2.getViewport().setXAxisBoundsManual(true);
                graph2.getGridLabelRenderer().setHumanRounding(false);
            }

        } else {
            Log.e("nulled", "Nulled source");
        }

    }
}
