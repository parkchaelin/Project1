package com.example.project1.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project1.Adapters.TelAdapter;
import com.example.project1.Classes.JsonModel;
import com.example.project1.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class TelFragment extends Fragment {
    ViewPager viewPager;
    //String TAG = MainActivity.class.getSimpleName();
    String TAG = TelFragment.class.getSimpleName();

    //객체의 현재 상태 정보 -> 연결 고리 역할을 함.
    Context context;

    ListView lstView;
    ////////////
  /////  List<Tel> items;

    public TelFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //fragment_tel을 화면에 띄워줌, 이의 참조 획득
        View view = inflater.inflate(R.layout.fragment_tel, container, false);
        /////////////
     /////   items = new ArrayList<>();

        try {
            //Fragment를 호출한 Activity의 현재 상태
            context = getActivity();

            //Json과 객체 간 변환을 해주는 Gson
            Gson gson = new Gson();

            JsonModel model = gson.fromJson(loadJSONFromAssets(), JsonModel.class);     //fromJson() : JSON형식의 데이터를 지정한 타입의 데이터로 변환.

            //fragment_tel에서 listView에 대한 참조 획득
            lstView = (ListView) view.findViewById(R.id.listView);
            //telAdapter의 getview에서 가져온 JSON의 정보를 보여줌.
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