package com.example.project1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.project1.Classes.myTime;
import com.example.project1.R;
import com.example.project1.Classes.Tel;

import java.util.List;

public class WorkTimeAdapter extends BaseAdapter {
    //String TAG = TelAdapter.class.getSimpleName();

    Context context;
    List<myTime> items;
    LayoutInflater inflter;


    public WorkTimeAdapter(Context context, List<myTime> data) {
        this.context = context;
        this.items = data;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
        //return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
        //return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.time_item, null);
        TextView stt = (TextView)view.findViewById(R.id.stt);
        TextView edt = (TextView)view.findViewById(R.id.edt);
        stt.setText(items.get(i).getstartTime());
        edt.setText(items.get(i).getEndTime());

        return view;
    }
}