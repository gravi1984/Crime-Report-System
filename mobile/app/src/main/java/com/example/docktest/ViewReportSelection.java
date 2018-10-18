package com.example.docktest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.docktest.MainActivity;
import com.example.docktest.R;

import Fragments.CityFragment;
import Fragments.StreetFragment;
import Fragments.SuburbFragment;
import Fragments.TimeFragment;
import Fragments.TypeFragment;

/**
 *  @author Haoyang Cui
 *  the fragment for case report selection function, we set chart based on five different features
 *  But only leave one (view by state/city) by the limitation of out final API.
 *  Leave the related codes as a interface for future development.
 * */
public class ViewReportSelection extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_report_selection, container, false);
        //Button viewByType = (Button)view.findViewById(R.id.ViewByType);
        //Button viewByStreet = (Button)view.findViewById(R.id.ViewByStreet);
        //Button viewBySuburb = (Button)view.findViewById(R.id.ViewBySuburb);
        Button viewByState = (Button)view.findViewById(R.id.ViewByState);
        //Button viewByTime = (Button)view.findViewById(R.id.ViewByTime);
        //mapView = (MapView) view.findViewById(R.id.MapView);
        /*viewByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new TypeFragment());
                Toast.makeText(getActivity(),"Not finished Yet, The data are not real Data from DB",
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewByStreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new StreetFragment());
                Toast.makeText(getActivity(),"Not finished Yet, The data are not real Data from DB",
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewBySuburb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new CityFragment());
                Toast.makeText(getActivity(),"Not finished Yet, The data are not real Data from DB",
                        Toast.LENGTH_SHORT).show();
            }
        });*/
        viewByState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).replaceFragment(new SuburbFragment());
            }
        });
       /* viewByTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new TimeFragment());
                Toast.makeText(getActivity(),"Not finished Yet, The data are not real Data from DB",
                        Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }
}
