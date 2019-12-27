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

    private ViewPager mViewPager;
    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.pager_content);
        setupViewPager(mViewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout_tab);
        tabLayout.setupWithViewPager(mViewPager);


    }

    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new TelFragment(), "연락처");
        adapter.addFragment(new ImageFragment(), "이미지");
        adapter.addFragment(new BlankFragment(), "01");
        viewPager.setAdapter(adapter);
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
