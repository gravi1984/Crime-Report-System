package com.example.docktest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.docktest.EditPersonalInfo;
import com.example.docktest.MainActivity;
import com.example.docktest.R;

/**
 *  @author Haoyang Cui
 *  @purpose: used as a edit personal info fragment
 *  only used in our first version API
 * */
public class UpdateEmail extends Fragment {

    private Button mUpdateEmail;
    private Button mCancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_update_email, container, false);
        mUpdateEmail = (Button)view.findViewById(R.id.Update_email);
        mCancel = (Button)view.findViewById(R.id.Update_email_cancel);
        mUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new EditPersonalInfo());
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new EditPersonalInfo());
            }
        });
        return view;
    }
}
