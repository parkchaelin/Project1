package com.example.project1.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project1.Adapters.WorkTimeAdapter;
import com.example.project1.Classes.myTime;
import com.example.project1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkTimeFragment extends Fragment {
    ViewPager viewPager;
    public static boolean check = false;
    static int myColor = Color.WHITE;
    ArrayList<myTime> myTimeList;
    String str1; String str2;
    int myint = 0;
    public WorkTimeFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_work_time, container, false);
        final Button Btn = (Button) view.findViewById(R.id.Button1);
        Btn.setBackgroundColor(myColor);

        InitializeData();
        final ListView worktimeview = (ListView) view.findViewById(R.id.workTimeView);
        final WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
        worktimeview.setAdapter(workTimeAdapter);

        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(check == false)
                {
                    Btn.setBackgroundColor(Color.RED);
                    check = true;
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String getTime = simpleDate.format(mDate);
                    str1 = getTime;
                }
                else {
                Btn.setBackgroundColor(Color.WHITE);
                check = false;
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String getTime = simpleDate.format(mDate);
                str2 = getTime;
                myTimeList.add(new myTime(str1, str2));
                WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
                worktimeview.setAdapter(workTimeAdapter);
                }



            }
        });
        return view;

    }
    public void InitializeData()
    {
        myTimeList = new ArrayList<myTime>();
    }
}