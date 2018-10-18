package com.example.docktest;

/**
 * @author Haoyang Cui
 * @purpose: the case submission success fragment
 * */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.docktest.MainActivity;
import com.example.docktest.R;

import com.example.docktest.CreateCase;

public class Submit_Successfully extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_submit__successfully, container, false);
        Button mBack = (Button)view.findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new CreateCase());
            }
        });
        return view;
    }


}
