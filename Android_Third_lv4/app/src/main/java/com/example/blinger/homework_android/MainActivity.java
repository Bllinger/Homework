package com.example.blinger.homework_android;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout mTabLayout;
    private Adapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager) findViewById(R.id.vp);
        mTabLayout = (TabLayout) findViewById(R.id.tab);

        mAdapter = new Adapter(getSupportFragmentManager());
        mFragments.add(Myfragment.newInstance("第一个Tab"));
        mFragments.add(Myfragment.newInstance("第二个Tab"));
        mFragments.add(Myfragment.newInstance("第三个Tab"));
        mFragments.add(Myfragment.newInstance("第四个Tab"));
        mFragments.add(Myfragment.newInstance("第五个Tab"));
        mFragments.add(Myfragment.newInstance("第六个Tab"));
        mFragments.add(Myfragment.newInstance("第七个Tab"));
        mFragments.add(Myfragment.newInstance("第八个Tab"));
        mFragments.add(Myfragment.newInstance("第九个Tab"));
        mFragments.add(Myfragment.newInstance("第十个Tab"));
        mAdapter.setFragments(mFragments);

        vp.setAdapter(mAdapter);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(vp);
        mTabLayout.setTabTextColors(Color.GRAY,Color.RED);
        mTabLayout.setSelectedTabIndicatorColor(Color.RED);
    }
}
