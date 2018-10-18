package com.example.docktest;

/**
 *  @author Haoyang Cui
 *  @purpose: A set Ip address fragment for IP Address setting
 * */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetIPAddress extends AppCompatActivity {


    private Button mCancel;
    private Button mSetIP;
    private EditText IPaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ipaddress);
        this.mCancel = findViewById(R.id.cancel_setUp);
        this.mSetIP = findViewById(R.id.setUp);
        this.IPaddress = findViewById(R.id.IPAddress);
    }

    public void cancelSetup(View view){
        Intent intent = new Intent(SetIPAddress.this,MainActivity.class) ;
        startActivity(intent);
    }
    /**
     * set IP address, storage data into the class Data
     * */
    public void setup(View view){
        String ip = "http://"+ IPaddress.getText().toString() ;
        Utils.Data.url = ip;
        Intent intent = new Intent(SetIPAddress.this,MainActivity.class) ;
        startActivity(intent);
    }


}
