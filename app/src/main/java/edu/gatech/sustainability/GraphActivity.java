package edu.gatech.sustainability;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.sustainability.model.report.QualityReport;
import edu.gatech.sustainability.model.sources.WaterSource;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class GraphActivity extends AppCompatActivity {
    LineChartView contaminantChart = (LineChartView) findViewById(R.id.contaminantChart);
    LineChartView virusChart = (LineChartView) findViewById(R.id.virusChart);

    List<PointValue> contPoints = new ArrayList<>();
    List<PointValue> virusPoints = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        contaminantChart.setInteractive(true);
        contaminantChart.setZoomType(ZoomType.HORIZONTAL);
        contaminantChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        virusChart.setInteractive(true);
        virusChart.setZoomType(ZoomType.HORIZONTAL);
        virusChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        String id = (String) getIntent().getSerializableExtra("filler");
        WaterSource source = null;
        for (WaterSource w : MainActivity.waterSources) {
            if (w.getSourceId().equals(id)) {
                source = w;
            }
        }
        if (source != null) {
            List<QualityReport> qualityReports = source.waterPurityReports;
            qualityReports.sort((q1, q2) -> (int) (q1.getDate() - q2.getDate()));
            for (int i = 0; i < qualityReports.size(); i++) {
                contPoints.add(new PointValue(i, (float) qualityReports.get(i).getContPpm()));
                virusPoints.add(new PointValue(i, (float) qualityReports.get(i).getVirPpm()));
            }
            Line contLine = new Line(contPoints).setColor(Color.BLUE).setCubic(true);
            List<Line> contLines = new ArrayList<Line>();
            contLines.add(contLine);
            Line virLine = new Line(virusPoints).setColor(Color.BLUE).setCubic(true);
            List<Line> virLines = new ArrayList<Line>();
            virLines.add(virLine);

            LineChartData contData = new LineChartData();
            contData.setLines(contLines);
            LineChartData virusData = new LineChartData();
            virusData.setLines(virLines);

            contaminantChart.setLineChartData(contData);
            virusChart.setLineChartData(virusData);
        }

    }
}
