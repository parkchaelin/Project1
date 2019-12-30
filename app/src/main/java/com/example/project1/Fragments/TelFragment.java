package com.example.project1.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project1.Activities.MainActivity;
import com.example.project1.Adapters.TelAdapter;
import com.example.project1.Adapters.WorkTimeAdapter;
import com.example.project1.Classes.JsonModel;
import com.example.project1.Classes.Tel;
import com.example.project1.Classes.myTime;
import com.example.project1.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class TelFragment extends Fragment {
    ViewPager viewPager;


    ListView listView;
    ArrayList<Tel> telList;


    public TelFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //fragment_tel을 화면에 띄워줌, 이의 참조 획득
        View view = inflater.inflate(R.layout.fragment_tel, container, false);
        listView = view.findViewById(R.id.listView);
        telList = new ArrayList<Tel>();
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();

        return view;

    }
    class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<String>> {
//        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
//            pd = ProgressDialog.show(MainActivity.this, "Loading Contacts",
//                    "Please Wait");
        }
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ArrayList<String> contacts = new ArrayList<String>();

            Cursor c = getActivity().getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            while (c.moveToNext()) {

                String contactName = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phNumber = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                telList.add( new Tel(contactName, phNumber));

            }
            Collections.sort(contacts);
            c.close();

            return contacts;
        }

        @Override
        protected void onPostExecute(ArrayList<String> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);
//            pd.cancel();
//            ll.removeView(loadBtn);


            TelAdapter telAdapter = new TelAdapter(getActivity().getApplicationContext(), telList);
            listView.setAdapter(telAdapter);

        }

    }




}