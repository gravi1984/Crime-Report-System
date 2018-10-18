package com.example.docktest;

/**
 *
 * @author Haoyang Cui
 * @Purpose: this class is the function class of Create Case page.
 * */

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Utils.Data;

public class CreateCase extends Fragment {

    // components
    private Button mAddEvidenceButton;
    private Button mSubmitButton;
    private EditText mDescription;
    private EditText street;
    private EditText suburb;
    private Switch mAnonymous;
    private  Spinner type;
    private  Spinner city;
    private int year;
    private int month;
    private int day;
    private Spinner hour;
    private Spinner minute;

    private Button changeIP;

    /**
     *
     * initialise all the components and set listening event function
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create_case, container, false);
        mAddEvidenceButton = (Button)view.findViewById(R.id.add_evidence);
        mSubmitButton = (Button) view.findViewById(R.id.submit);
        street = (EditText) view.findViewById(R.id.street);
        suburb = (EditText) view.findViewById(R.id.suburb);
        mDescription = (EditText)view.findViewById(R.id.description);
        mAnonymous = (Switch)view.findViewById(R.id.anonymous);
        changeIP = (Button)view.findViewById(R.id.ipAddress);


        type = (Spinner) view.findViewById(R.id.crime_type);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        city = (Spinner) view.findViewById(R.id.City);
        city.setPrompt("State");
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        hour = (Spinner) view.findViewById(R.id.Hour);
        hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        minute = (Spinner) view.findViewById(R.id.Minute);
        minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        // set anonymous button function
        mAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    Toast.makeText(getActivity(), "You will not submit your personal information",
                            Toast.LENGTH_SHORT).show();
                    Data.setSubmitUsername("anonymous");

                } else {
                    Toast.makeText(getActivity(), "This case will be submitted together with your personal information",
                            Toast.LENGTH_SHORT).show();
                    Data.setSubmitUsername(Data.getUsername());

                }
            }});
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpURLConnection();
                ((MainActivity)getActivity()).replaceFragment(new Submit_Successfully());
            }
        });
        mAddEvidenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getActivity(),Select_Photo.class);
                startActivity(intent5);
                getActivity().finish();
            }
        });
        changeIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SetIPAddress.class) ;
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     *  event connected with SUBMIT button
     * */
    public void submit(View view){
        sendRequestWithHttpURLConnection();

    }

    /**
     *  Submit case information using httpURLConnection protocol
     * */
    private void sendRequestWithHttpURLConnection() {
        // open new thread http request
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    // set url with the post request API
                    String urlString = Utils.Data.getUrl() + "/posts/new";
                    URL url = new URL(urlString);
                    connection = (HttpURLConnection) url.openConnection();
                    // set method as "POST"
                    connection.setRequestMethod("POST");
                    // set requirement type
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    // create JSON object and used as output data storage
                    JSONObject person = new JSONObject();
                    String myType = type.getSelectedItem().toString();
                    String st = street.getText().toString();
                    String sub = suburb.getText().toString();
                    String sta = city.getSelectedItem().toString();
                    String time = hour.getSelectedItem().toString() +":"+ minute.getSelectedItem().toString();
                    String desc = mDescription.getText().toString();
                    person.put("title","Post from mobile" );
                    //person.put("time",date);
                    person.put("content",desc + "  \n" +"Location: "+ st+" "+sub + " "+sta
                    + "   \n" + "Time of Occurrence: " + time);
                    //person.put("user_id","5");
                    person.put("user_name", Data.getSubmitUsername());
                    person.put("cat_id","3");
                    person.put("tag",sta);
                    person.put("label_img",Data.getImageLabel());

                    OutputStream os = connection.getOutputStream();
                    os.write(person.toString().getBytes("UTF-8"));
                    os.close();
                    // set connection time out
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    // read from input stream
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    // store input data
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }


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

