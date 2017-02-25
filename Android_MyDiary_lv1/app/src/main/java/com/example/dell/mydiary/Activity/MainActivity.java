package com.example.dell.mydiary.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dell.mydiary.Adapter.FragmentAdapter;
import com.example.dell.mydiary.Fragment.Fragment_one;
import com.example.dell.mydiary.Fragment.Fragment_two;
import com.example.dell.mydiary.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout linearLayout;
    private FragmentAdapter mFragmentAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private String [] titles = new String[] {"日记","日历"};
    private List<Fragment> mFragments  = new ArrayList<>();
    private Button mMenuButton;
    private DrawerLayout mDrawerLayout;
    private EditText editText;
    private Button mButtonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlphaAnimation animation  = new AlphaAnimation(1.0f,1.0f);
        animation.setDuration(2000);
        linearLayout  = (LinearLayout) findViewById(R.id.welcome_ll);
        linearLayout.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                linearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        initView();

        mMenuButton = (Button) findViewById(R.id.button_menu);
        //mButtonName = (Button) findViewById(R.id.button_name);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        mMenuButton.setOnClickListener(this);
        //mButtonName.setOnClickListener(this);

        navigationView.setCheckedItem(R.id.nav_diary);

        View headerView = navigationView.getHeaderView(R.layout.nav_header);

        //editText = (EditText) headerView.findViewById(R.id.edit_View);
        //mButtonName = (Button) headerView.findViewById(R.id.button_name);
        //headerView.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_friends:
                        Intent intent = new Intent(MainActivity.this,FriendsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_setting:
                        Intent intent1 = new Intent(MainActivity.this,SettingActivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });


    }
    private void initView(){
        mTabLayout = (TabLayout) findViewById(R.id.table_layout);
        mViewpager = (ViewPager) findViewById(R.id.view_pager);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        mFragmentAdapter.setTitiles(titles);

        mFragments = new ArrayList<>();
        mFragments.add(new Fragment_one());
        mFragments.add(new Fragment_two());

        mFragmentAdapter.setFragment(mFragments);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewpager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.addCategory(Intent.CATEGORY_HOME);

            startActivity(intent);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
//            case R.id.button_name:
//                //
//                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
//                break;
            default:
                break;
        }
    }
}
