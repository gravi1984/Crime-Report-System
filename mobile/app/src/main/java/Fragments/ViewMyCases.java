package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.docktest.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Utils.Data;
/**
 * @author Haoyang Cui
 * @purpose: View my Cases page for login users, show related information toast if clicked on
 * */
public class ViewMyCases extends Fragment{


    private String[] Cases =new String[100];
    private String[] Types =new String[100];
    private String[] description = new String[100];
    public JSONArray allCases = new JSONArray();

    /**
     *  initialise and get users' cases data
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_fragment, container, false);


        ListView listView = (ListView)view.findViewById(R.id.list_view);
        selectMyCases();

        // parse input data
        final String[] allMyCases = new String[100];
        try {
            allCases = new JSONArray(Data.getCases());
            Log.v("output"," "+Data.casesString);
            Log.v("output",allCases.toString());
            int i = 0;
            int j = 0;
            for (i = 0; i < allCases.length(); i++){
                JSONObject myjObject = allCases.getJSONObject(i);
                if(myjObject.get("user_name") == null) {
                    continue;
                }
                else if(myjObject.getString("user_name").equals(Data.getUsername())) {
                    Cases[i] = myjObject.getString("id");
                    Types[i] = myjObject.getString("title");
                    description[i] = myjObject.getString("summary");
                    allMyCases[j] = "Case: " + Cases[i] + "  " + Types[i] + " \n" + description[i];
                    j++;
                }
            }
            String[] selectedMyCases = new String[j];
            int n = j;
            for(j=0;j<n;j++){
                selectedMyCases[j] = allMyCases[j];
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1, selectedMyCases);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    /*Toast.makeText(getActivity(), Utils.Data.getCase(Cases[position]),
                            Toast.LENGTH_SHORT).show();*/
                    Toast.makeText(getActivity(), allMyCases[position],
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    /**
     *  httpURLConnection request for my cases.
     * */
    private void selectMyCases() {
        //new thread for http requirement
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                //JSONObject myCase = new JSONObject();
                try {
                    String urlString = Utils.Data.getUrl() + "/posts";
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
                    Data.sendCases(str);
                    Log.v("output1",Data.casesString);


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
