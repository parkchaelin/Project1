package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ImageFragment extends Fragment {
    ViewPager viewPager;

    public ImageFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);

            }
        });


        return view;

    }

}