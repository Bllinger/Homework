package com.example.blinger.homework_android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class Adapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private String[] titles = {"第一个TAB","第二个TAb","第三个TAB","第四个Tab","第五个Tab","第六个Tab","第七个Tab"
            ,"第八个Tab","第九个Tab","第十个Tab"};

    public Adapter (FragmentManager fm){
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
