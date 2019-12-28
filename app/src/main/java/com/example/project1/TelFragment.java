package com.example.project1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TelFragment extends Fragment {
    ViewPager viewPager;
    //String TAG = MainActivity.class.getSimpleName();
    String TAG = TelFragment.class.getSimpleName();

    Context context;
    ListView lstView;
    ////////////
  /////  List<Tel> items;

    public TelFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tel, container, false);
        /////////////
     /////   items = new ArrayList<>();

        try {
            context = getActivity();
            Gson gson = new Gson();
            JsonModel model = gson.fromJson(loadJSONFromAssets(), JsonModel.class);     //fromJson() : JSON형식의 데이터를 지정한 타입의 데이터로 변환.

            lstView = (ListView) view.findViewById(R.id.listView);
            TelAdapter telAdapter = new TelAdapter(getActivity().getApplicationContext(), model.getItems());
            lstView.setAdapter(telAdapter);

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
        return view;

    }

    public String loadJSONFromAssets() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("Tel.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");     //InputStream을 열어 assets 폴더에 있는 Tel.json을 가져와 String 타입으로 데이터 담음.
        } catch (IOException e) {
            e.printStackTrace();
            ///
            return null;
        }

        return json;
    }

}