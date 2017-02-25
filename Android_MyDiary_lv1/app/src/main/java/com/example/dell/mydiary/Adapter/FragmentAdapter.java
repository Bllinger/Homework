package com.example.dell.mydiary.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dell on 2017/2/18.
 */

public class FragmentAdapter extends FragmentPagerAdapter{
    String [] mTitiles;
    List<Fragment> mFragment;

    public void setTitiles(String[] titiles){
        mTitiles = titiles;
    }
    public void setFragment(List<Fragment> fragment) {
        mFragment = fragment;
    }
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment != null ? mFragment.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitiles[position];
    }
}
