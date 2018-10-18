package Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docktest.Login;
import com.example.docktest.MainActivity;
import com.example.docktest.R;
import com.example.docktest.ViewReportSelection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Utils.Data;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
/**
 * @author  Haoyang Cui
 * @purpose the fragment is used as a case report based on feature and show as pie chart
 * */
public class SuburbFragment extends Fragment {

    private PieChartView pieChart;
    private TextView text, text1;
    private double sum = 0;
    public JSONArray allTagsJson = new JSONArray();

    public static Fragment newInstance() {
        SuburbFragment fragment = new SuburbFragment();
        return fragment;
    }

    /**
     * initialise pie chart related setting
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] allCities = new String[100];
        String[] allNum = new String[100];
        View view = inflater.inflate(R.layout.suburb_fragment, container, false);
        pieChart = (PieChartView) view.findViewById(R.id.suburb_chart);
        text = (TextView) view.findViewById(R.id.suburb_text);
        text1 = (TextView) view.findViewById(R.id.suburb_text1);
        Button mBack = (Button)view.findViewById(R.id.back_to_view_select_from_suburb);
        List<SliceValue> values = new ArrayList<>();
        final List<Integer> colorData = new ArrayList<>();
        final List<String> titleData = new ArrayList<>();
        getCityData();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        },1000);
        try {
            allTagsJson = new JSONArray(Data.getCityTagsData());
            int j = 0;
            int i;
            for (i = 0; i < allTagsJson.length(); i++){
                JSONObject myjObject = allTagsJson.getJSONObject(i);
                allCities[i] = myjObject.getString("tag_name");
                allNum[i] = myjObject.getString("post_num");
                titleData.add(allCities[i]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        // set pie chart colors
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



        for (int i = 0; i < titleData.size(); i++) {
            double v =  Double.valueOf(allNum[i].toString());
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

    /**
     *  get cases statistic data using HttpURLConnection protocol for internet request
     * */
    public void getCityData() {
        //new thread for http requirement
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    // API for get cases statistic data
                    String urlString = Utils.Data.getUrl() + "/tags";
                    URL url = new URL(urlString);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    // read the input stream
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    String str = response.toString();
                    JSONArray myJsonArray = new JSONArray(str);
                    Data.sendCityTagsData(str);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
