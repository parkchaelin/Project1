package com.example.project1.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project1.Activities.WorkTimeActivity;
import com.example.project1.Adapters.WorkTimeAdapter;
import com.example.project1.Classes.myTime;
import com.example.project1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkTimeFragment extends Fragment {
    ViewPager viewPager;

    ArrayList<myTime> myTimeList;

    Date startTime;
    Date endTime;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String startTimeText;
    String endTimeText;


    public WorkTimeFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_work_time, container, false);
        final Button startButton = (Button) view.findViewById(R.id.startButton);
        final Button endButton = (Button) view.findViewById(R.id.endButton);

        InitializeData();
        final ListView worktimeview = (ListView) view.findViewById(R.id.workTimeView);
        final WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
        worktimeview.setAdapter(workTimeAdapter);

        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startButton.setVisibility(view.INVISIBLE);
                endButton.setVisibility(view.VISIBLE);
                long now = System.currentTimeMillis();
                startTime = new Date(now);
                startTimeText = format.format(startTime);
                Log.d("start time", "start time : " + startTimeText);
            }
        });

        endButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startButton.setVisibility(view.VISIBLE);
                endButton.setVisibility(view.INVISIBLE);
                long now = System.currentTimeMillis();
                endTime = new Date(now);
                endTimeText = format.format(endTime);
                Log.d("end time", "start time : " + endTimeText);
                myTimeList.add(new myTime(startTimeText, endTimeText));
                WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
                worktimeview.setAdapter(workTimeAdapter);

            }
        });

        worktimeview.setAdapter(workTimeAdapter);

        worktimeview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WorkTimeActivity.class);
                startTimeText = myTimeList.get(position).getstartTime();
                endTimeText = myTimeList.get(position).getEndTime();
                intent.putExtra("startTimeText", startTimeText);
                intent.putExtra("endTimeText", endTimeText);

                Log.d("end time", "****************8end time : " + endTimeText);
                startActivity(intent);
            }
        });



//        Btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if(check == false)
//                {
//                    Btn.setBackgroundColor(Color.RED);
//                    check = true;
//                    long now = System.currentTimeMillis();
//                    Date mDate = new Date(now);
//                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                    String getTime = simpleDate.format(mDate);
//                    str1 = getTime;
//                }
//                else {
//                Btn.setBackgroundColor(Color.WHITE);
//                check = false;
//                    long now = System.currentTimeMillis();
//                    Date mDate = new Date(now);
//                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                    String getTime = simpleDate.format(mDate);
//                str2 = getTime;
//                myTimeList.add(new myTime(str1, str2));
//                WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
//                worktimeview.setAdapter(workTimeAdapter);
//                }
//
//
//
//            }
//        });
        return view;

    }
    public void InitializeData()
    {
        myTimeList = new ArrayList<myTime>();
    }
}