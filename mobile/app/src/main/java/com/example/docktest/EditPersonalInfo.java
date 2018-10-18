package com.example.docktest;

/**
 * @Haoyang Cui
 * @Purpose: this class is used for login user.
 * However, because of the change of API, we only leave LOGOUT function in this page
 * */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Utils.Data;

public class EditPersonalInfo extends Fragment {

    private Button mUpdateEmail;
    private Button mLogout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_edit_personal_info, container, false);
        // used as interface to edit personal info in our first version API
        /*mUpdateEmail = (Button)view.findViewById(R.id.Update_email);
        mUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new UpdateEmail());
            }
        });*/
        mLogout = (Button)view.findViewById(R.id.Logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.logOut();
                Intent intent2= new Intent(getActivity(),Login.class) ;
                startActivity(intent2);
                getActivity().finish();
            }
        });
        return view;
    }
}
