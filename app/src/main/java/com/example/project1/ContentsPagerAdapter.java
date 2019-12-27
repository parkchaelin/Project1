package com.example.project1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount){
        super(fm);
        this.mPageCount = pageCount;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TelFragment telFragment = new TelFragment();
                return telFragment;
            case 1:
                ImageFragment imageFragment = new ImageFragment();
                return imageFragment;
            case 2:
                BlankFragment blankFragment = new BlankFragment();
                return blankFragment;
            default:
                return null;

        }
    }
    public int getCount(){
        return mPageCount;
    }

}
