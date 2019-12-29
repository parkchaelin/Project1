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
        //버튼과 리스트 화면에 띄움
        final View view = inflater.inflate(R.layout.fragment_work_time, container, false);

        //start, end 버튼에 대한 참조 획득
        final Button startButton = (Button) view.findViewById(R.id.startButton);
        final Button endButton = (Button) view.findViewById(R.id.endButton);

        //{start time, end time} class의 List
        myTimeList = new ArrayList<myTime>();

        //오른쪽의 ListView에 대한 참조 획득
        final ListView worktimeview = (ListView) view.findViewById(R.id.workTimeView);

        //ListView의 각 List를 관리하는 adapter 연결(없어도문제없음)
//        final WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
//        worktimeview.setAdapter(workTimeAdapter);

        //StartButton 눌렀을 때의 기능 구현
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

        //endButton 눌렀을 때의 기능 구현
        endButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startButton.setVisibility(view.VISIBLE);
                endButton.setVisibility(view.INVISIBLE);
                long now = System.currentTimeMillis();
                endTime = new Date(now);
                endTimeText = format.format(endTime);
                Log.d("end time", "start time : " + endTimeText);
                myTimeList.add(0, new myTime(startTimeText, endTimeText));
                WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
                worktimeview.setAdapter(workTimeAdapter);

            }
        });
        //????
        //worktimeview.setAdapter(workTimeAdapter);

        //리스트 클릭했을 때의 기능 구현
        worktimeview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WorkTimeActivity.class);
                String tempstartTimeText = myTimeList.get(position).getstartTime();
                String tempendTimeText = myTimeList.get(position).getEndTime();
                intent.putExtra("tempstartTimeText", tempstartTimeText);
                intent.putExtra("tempendTimeText", tempendTimeText);

                Log.d("end time", "****************8end time : " + tempendTimeText);
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
}