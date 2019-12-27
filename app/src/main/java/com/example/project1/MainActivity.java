package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentsPagerAdapter;
    private ArrayList<String> array_tel;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);

        mTabLayout.addTab(mTabLayout.newTab().setText("연락처"));
        mTabLayout.addTab(mTabLayout.newTab().setText("이미지"));
        mTabLayout.addTab(mTabLayout.newTab().setText("00"));

        mViewPager = (ViewPager) findViewById(R.id.pager_content);
        mContentsPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(mContentsPagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }




}

//
//
//    private View createTabView(String tabName) {
//
//        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
//
//        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
//
//        txt_name.setText(tabName);
//
//        return tabView;
//
//    }
//}
