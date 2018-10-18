package Fragments;

/**
 * @author Haoyang Cui
 * @Purpose: the fragment of cases report by features.
 * This class is a demo version of column Chart.
 * */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;


import com.example.docktest.MainActivity;
import com.example.docktest.R;
import com.example.docktest.ViewReportSelection;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

public class CityFragment extends Fragment {
    private ColumnChartView chart;
    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = true;
    private Button NextFragment;

    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();

    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN};
    // set demo data
    private int[] score1 = {88, 77, 99, 56, 48, 51, 33, 62, 56, 89, 98, 88, 97, 95, 81, 86, 94, 75, 86, 44};
    private int[] score2 = {98, 77, 89, 86, 48, 51, 13, 82, 58, 89, 98, 88, 87, 95, 81, 86, 94, 85, 86, 44};
    private int[] score3 = {88, 97, 99, 56, 98, 51, 33, 22, 56, 99, 98, 88, 97, 95, 81, 86, 24, 75, 26, 44};
    private int[] scoreNum1 = new int[3];
    private int[] scoreNum2 = new int[3];
    private int[] scoreNum3 = new int[3];


    public static Fragment newInstance() {
        StreetFragment fragment = new StreetFragment();
        return fragment;
    }

    // initialise the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_fragment, container, false);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);

        view.findViewById(R.id.tv1).setVisibility(View.VISIBLE);
        view.findViewById(R.id.tv2).setVisibility(View.VISIBLE);
        view.findViewById(R.id.tv3).setVisibility(View.VISIBLE);

        tv_title.setText("");
        chart = (ColumnChartView) view.findViewById(R.id.columnChart);
        initData();
        initEvent();
        NextFragment = (Button)view.findViewById(R.id.back_to_view_select_from_city);

        NextFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(getActivity(), "success2", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).replaceFragment(new ViewReportSelection());

            }
        });
        return view;
    }


    // initialise data
    private void initData() {
        calculate(score1, scoreNum1);
        calculate(score2, scoreNum2);
        calculate(score3, scoreNum3);


        initXY();

        generateSubcolumnsData();

    }

    // initialise Axis
    private void initXY() {
        //data of X
        mAxisXValues.add(new AxisValue(0).setLabel("Sydney"));
        mAxisXValues.add(new AxisValue(1).setLabel("Newcastle"));
        mAxisXValues.add(new AxisValue(2).setLabel("Melbourne"));

        //data of Y
        for (int i = 0; i < 20; i++) {
            mAxisYValues.add(new AxisValue(i).setLabel("" + i));
        }


    }

    // initialise event
    private void initEvent() {
        chart.setOnValueTouchListener(new ValueTouchListener());
    }

    // handle data
    private void calculate(int[] score, int[] scoreNum) {
        for (int i = 0; i < score.length; i++) {
            int s = score[i];
            if (s < 60) {
                scoreNum[0]++;
            } else if (s < 80) {
                scoreNum[1]++;
            } else if (s <= 100) {
                scoreNum[2]++;
            }
        }

    }

    // set column chart data
    private void generateSubcolumnsData() {
        int numColumns = 3;
        // Column can have many subcolumns, here I use 4 subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;


        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();

            if (i == 0) {
                values.add(new SubcolumnValue(scoreNum1[0], colors[0]));
                values.add(new SubcolumnValue(scoreNum2[0], colors[1]));
                values.add(new SubcolumnValue(scoreNum3[0], colors[2]));
            }

            if (i == 1) {
                values.add(new SubcolumnValue(scoreNum1[1], colors[0]));
                values.add(new SubcolumnValue(scoreNum2[1], colors[1]));
                values.add(new SubcolumnValue(scoreNum3[1], colors[2]));
            }

            if (i == 2) {
                values.add(new SubcolumnValue(scoreNum1[2], colors[0]));
                values.add(new SubcolumnValue(scoreNum2[2], colors[1]));
                values.add(new SubcolumnValue(scoreNum3[2], colors[2]));
            }


            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Cities");
                axisY.setName("Number");
            }

            axisX.setValues(mAxisXValues);

            axisY.setValues(mAxisYValues);

            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setColumnChartData(data);

    }


    // set toast while touching the chart
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            showToast("Selected: " + value);
        }

        @Override
        public void onValueDeselected() {

        }

    }


    Toast toast;

    public void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }


}