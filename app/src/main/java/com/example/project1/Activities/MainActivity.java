package com.example.project1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project1.Adapters.SectionPageAdapter;
import com.example.project1.Classes.myTime;
import com.example.project1.Fragments.WorkTimeFragment;
import com.example.project1.Fragments.ImageFragment;
import com.example.project1.Fragments.TelFragment;
import com.example.project1.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    //SectionPageAdapter에 대한 객체를 생성하여 참조 획득
    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //activity_main.xml에서 ViewPager에 대한 참조 획득
        mViewPager = (ViewPager) findViewById(R.id.pager_content);

        //각 탭의 title과 Fragment를 SectionPageAdapter을 거쳐 ViewPager과 연결시켜주는 메소드
        setupViewPager(mViewPager);

        //activity_main에서 TabLayout에 대한 참조 획득
        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout_tab);

        //위에서 세팅한 ViewPager과 TabLayout을 연동시키는 메소드
        tabLayout.setupWithViewPager(mViewPager);



    }
    //SectionPageAdapter에 Fragment들 추가
    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new TelFragment(), "연락처");
        adapter.addFragment(new ImageFragment(), "이미지");
        adapter.addFragment(new WorkTimeFragment(), "WorkTime");
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
