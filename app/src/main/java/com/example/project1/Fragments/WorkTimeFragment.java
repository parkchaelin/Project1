package com.example.project1.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
    ArrayList<myTime> myTimeList;
    Date startTime;
    Date endTime;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String startTimeText;
    String endTimeText;
    SQLiteDatabase sqliteDB;
    String st; String ed;
    public WorkTimeFragment(){
    }
    public WorkTimeFragment(SQLiteDatabase sq){
        sqliteDB = sq;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //버튼과 리스트 화면에 띄움
        final View view = inflater.inflate(R.layout.fragment_work_time, container, false);

        //start, end 버튼에 대한 참조 획득
        final Button startButton = (Button) view.findViewById(R.id.startButton);
        final Button endButton = (Button) view.findViewById(R.id.endButton);
        final Button deleteButton = (Button) view.findViewById(R.id.deleteButton);

        //테이블 만들기
        String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS TIME_T (TIME CHAR(25), BOOL INTEGER)";
        sqliteDB.execSQL(sqlCreateTbl);

        //{start time, end time} class의 List
        myTimeList = new ArrayList<myTime>();

        //오른쪽의 ListView에 대한 참조 획득
        final ListView worktimeview = (ListView) view.findViewById(R.id.workTimeView);

        //DB의 TABLE 전부 리스트에 추가
        Cursor cursor = sqliteDB.rawQuery("SELECT * FROM TIME_T", null);
        String retString;
        retString = initialize(cursor);
        cursor.close();

        // Button 시작인지 끝인지 정하기 위해 last element의 int를 구하기
        Cursor cs = sqliteDB.rawQuery("SELECT * FROM TIME_T", null);
        int lastInt = getLastElementInt(cs);
        cs.close();

        // 버튼 시작,끝 정하기
        ButtonInitialize(startButton, endButton, lastInt, view);

        //리스트에 불러온 DB들 띄우기
        WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
        worktimeview.setAdapter(workTimeAdapter);


        //StartButton 눌렀을 때의 기능 구현
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startButton.setVisibility(view.GONE);
                endButton.setVisibility(view.VISIBLE);
                long now = System.currentTimeMillis();
                startTime = new Date(now);
                startTimeText = format.format(startTime);
                // StartTime TABLE에 추가
                String sqlInsert = "INSERT INTO TIME_T (TIME, BOOL) VALUES ('" + startTimeText + "', 0)";
                sqliteDB.execSQL(sqlInsert);
            }
        });

        //deleteButton 구현
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String myLastString = "myLastString";
                Cursor cs = sqliteDB.rawQuery("SELECT * FROM TIME_T", null);
                int myLastInt = getLastElementInt(cs);
                cs.close();

                if (myLastInt == 0) {
                    cs = sqliteDB.rawQuery("SELECT * FROM TIME_T", null);
                    myLastString = getLastElementString(cs);
                    cs.close();
                }

                String sqlDropTbl = "DROP TABLE TIME_T";
                sqliteDB.execSQL(sqlDropTbl);
                String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS TIME_T (TIME CHAR(25), BOOL INTEGER)";
                sqliteDB.execSQL(sqlCreateTbl);
                myTimeList = new ArrayList<myTime>();

                if (myLastInt == 0) {
                    String sqlInsert = "INSERT INTO TIME_T (TIME, BOOL) VALUES ('" + myLastString + "', 0)";
                    sqliteDB.execSQL(sqlInsert);
                }
                WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
                worktimeview.setAdapter(workTimeAdapter);
            }
        });

        //endButton 눌렀을 때의 기능 구현
        endButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startButton.setVisibility(view.VISIBLE);
                endButton.setVisibility(view.GONE);
                long now = System.currentTimeMillis();
                endTime = new Date(now);
                endTimeText = format.format(endTime);

                String sqlInsert = "INSERT INTO TIME_T (TIME, BOOL) VALUES ('" + endTimeText + "', 1)";//ENDTIME
                sqliteDB.execSQL(sqlInsert);

                Cursor cs = sqliteDB.rawQuery("SELECT * FROM TIME_T", null);
                while (cs.moveToNext()) {
                    // 첫 번째 컬럼(Column)이 INTEGER 타입인 경우.
                    if (cs.getInt(1) == 0) {
                        startTimeText = cs.getString(0);
                    }
                }
                cs.close();


                myTimeList.add(0, new myTime(startTimeText, endTimeText));
                WorkTimeAdapter workTimeAdapter = new WorkTimeAdapter(getActivity().getApplicationContext(), myTimeList);
                worktimeview.setAdapter(workTimeAdapter);
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////
        //            List 삭제, 초기화 구현하기                                              //
        //            Boolean 추가해서 버튼 start되었는지 확인. true일때 false일때 구현       //
        ////////////////////////////////////////////////////////////////////////////////////////


        //리스트 클릭했을 때의 기능 구현
        worktimeview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WorkTimeActivity.class);
                String tempstartTimeText = myTimeList.get(position).getstartTime();
                String tempendTimeText = myTimeList.get(position).getEndTime();
                intent.putExtra("tempstartTimeText", tempstartTimeText);
                intent.putExtra("tempendTimeText", tempendTimeText);
                startActivity(intent);
            }
        });
        return view;
    }


    public String initialize(Cursor cursor) {
        int myint = 0;
        while (cursor.moveToNext()) {
            if (cursor.getInt(1) == 0) {
                st = cursor.getString(0);
                myint = 1;
            }
            if (cursor.getInt(1) == 1) {
                ed = cursor.getString(0);
                myTimeList.add(0, new myTime(st, ed));
                myint = 2;
            }

        }
        if (myint == 0) {return "Nothing in the Table";}
        else if (myint == 1){return st;}
        else {return ed;}
    }
    public int getLastElementInt(Cursor cs) {
        int myint = 0;
        while (cs.moveToNext()) {
            // 첫 번째 컬럼(Column)이 INTEGER 타입인 경우.
            if (cs.getInt(1) == 0) {
                myint = 1;
            }
            if (cs.getInt(1) == 1) {
                myint = 2;
            }
        }
        if (myint == 0) {return -1;}
        else if (myint == 1){return 0;}
        else {return 1;}
    }

    public String getLastElementString(Cursor cs) {
        int myint = 0;
        while (cs.moveToNext()) {
            // 첫 번째 컬럼(Column)이 INTEGER 타입인 경우.
            if (cs.getInt(1) == 0) {
                myint = 1;
                st = cs.getString(0);
            }
            if (cs.getInt(1) == 1) {
                myint = 2;
                ed = cs.getString(0);
            }
        }
        if (myint == 0) {return "None";}
        else if (myint == 1){return st;}
        else {return ed;}
    }

    public void ButtonInitialize(Button startButton,Button endButton,int lastInt, View view) {
        if (lastInt == 0) {
            startButton.setVisibility(view.GONE);
            endButton.setVisibility(view.VISIBLE);
        } else {
            startButton.setVisibility(view.VISIBLE);
            endButton.setVisibility(view.GONE);
        }
    }

}