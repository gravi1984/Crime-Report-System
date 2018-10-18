package Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.docktest.MainActivity;
import com.example.docktest.R;
import com.example.docktest.ViewReportSelection;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
/**
 * @author Haoyang Cui
 * @Purpose: the fragment of cases report by features.
 * This class is a demo version of pie Chart.
 * */
public class TypeFragment extends Fragment {

    private PieChartView pieChart;
    private TextView text, text1;
    private double sum = 0;

    public static Fragment newInstance() {
        TypeFragment fragment = new TypeFragment();

        return fragment;
    }

    /**
     * initialise pie chart related information
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.type_fragment, container, false);


        pieChart = (PieChartView) view.findViewById(R.id.pie_chart);
        text = (TextView) view.findViewById(R.id.chart_text);
        text1 = (TextView) view.findViewById(R.id.chart_text1);
        Button mBack = (Button)view.findViewById(R.id.back_to_view_select);
        List<SliceValue> values = new ArrayList<>();
        final List<Integer> colorData = new ArrayList<>();
        final List<String> titleData = new ArrayList<>();
        colorData.add(Color.parseColor("#85B74F"));
        colorData.add(Color.parseColor("#009BDB"));
        colorData.add(Color.parseColor("#FF0000"));
        colorData.add(Color.parseColor("#9569F8"));
        colorData.add(Color.parseColor("#F87C67"));
        colorData.add(Color.parseColor("#F1DA3D"));
        colorData.add(Color.parseColor("#87EA39"));
        colorData.add(Color.parseColor("#48AEFA"));
        colorData.add(Color.parseColor("#4E5052"));
        colorData.add(Color.parseColor("#D36458"));
        titleData.add("Case Type 1");
        titleData.add("Case Type 2");
        titleData.add("Case Type 3");
        titleData.add("Case Type 4");
        titleData.add("Case Type 5");
        titleData.add("Case Type 6");
        titleData.add("Case Type 7");
        titleData.add("Case Type 8");
        titleData.add("Case Type 9");
        titleData.add("Case Type 10");

        for (int i = 0; i < colorData.size(); i++) {
            double v = 100 * Math.random();
            SliceValue sliceValue = new SliceValue((float) (v), colorData.get(i));
            values.add(sliceValue);
            sum += v;
        }
        final PieChartData pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);

        pieChardata.setHasLabels(true);
        pieChardata.setHasLabelsOnlyForSelected(false);
        pieChardata.setHasLabelsOutside(true);
        pieChardata.setHasCenterCircle(true);
        pieChardata.setSlicesSpacing(5);
        pieChardata.setValues(values);

        pieChardata.setCenterCircleColor(Color.WHITE);
        pieChardata.setCenterCircleScale(0.3f);

        pieChart.setPieChartData(pieChardata);
        pieChart.setValueSelectionEnabled(true);
        pieChart.setAlpha(0.9f);
        pieChart.setCircleFillRatio(0.9f);


        pieChart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                text.setText(titleData.get(i));
                text1.setText(sliceValue.getValue()*100/sum + "%");
            }

            @Override
            public void onValueDeselected() {
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new ViewReportSelection());
            }
        });


        return view;
    }
}