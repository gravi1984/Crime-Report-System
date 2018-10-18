package Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.docktest.MainActivity;
import com.example.docktest.R;
import com.example.docktest.ViewReportSelection;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
/**
 * @author Haoyang Cui
 * @Purpose: the fragment of cases report by features.
 * This class is a demo version of line Chart.
 * */
public class TimeFragment extends Fragment {


    private  ColumnChartView columnchart;
    private LineChartView lineChart;
    private Button NextFragment;
    String[] weeks = {"00:00","03:00","06:00","09:00","12:00","15:00","18:00","21:00",};//X
    int[] weather = {7,9,7,6,7,8,6,8};//Y

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

    public static Fragment newInstance() {
        TimeFragment fragment = new TimeFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_fragment, container, false);
        lineChart = (LineChartView)view.findViewById(R.id.time_chart);

        getAxisLables();
        getAxisPoints();
        initLineChart();

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        NextFragment = (Button)getActivity().findViewById(R.id.back_to_view_select_from_time);

        NextFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(getActivity(), "success2", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).replaceFragment(new ViewReportSelection());

            }
        });
    }

    /**
     *  initialise line chart related information
     * */
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.WHITE).setCubic(false);
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);
        line.setCubic(true);
        line.setFilled(true);
//		line.setHasLabels(true);
        line.setHasLabelsOnlyForSelected(true);
        line.setHasLines(true);
        line.setHasPoints(true);
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);


        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.WHITE);
        axisX.setName("Street");
        axisX.setTextSize(7);
        axisX.setMaxLabelChars(7);
        axisX.setValues(mAxisValues);
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis();
        axisY.setMaxLabelChars(7);
        axisY.setName("Number");
        axisY.setTextSize(7);

        data.setAxisYLeft(axisY);

        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
    }


    /**
     * X axis
     */
    private void getAxisLables(){
        for (int i = 0; i < weeks.length; i++) {
            mAxisValues.add(new AxisValue(i).setLabel(weeks[i]));
        }
    }
    /**
     * Y axis
     */
    private void getAxisPoints(){
        for (int i = 0; i < weather.length; i++) {
            mPointValues.add(new PointValue(i, weather[i]));
        }
    }

}
