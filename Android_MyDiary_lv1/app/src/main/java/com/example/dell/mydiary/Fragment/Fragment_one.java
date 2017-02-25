package com.example.dell.mydiary.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.mydiary.Activity.CreateDiary_Activity;
import com.example.dell.mydiary.Adapter.RecyclerViewAdapter;
import com.example.dell.mydiary.MySQL.MyDatabaseHelper;
import com.example.dell.mydiary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/2/18.
 */

public class Fragment_one extends Fragment implements View.OnClickListener {
    View mView;
    private MyDatabaseHelper dbHelper;
    private List<String> Titles = new ArrayList<>();
    private List<String> Content = new ArrayList<>();
    private List<Integer> mDay = new ArrayList<>();
    private List<Integer> mWeek = new ArrayList<>();
    private List<Integer> mMonth  = new ArrayList<>();
    private List<String> mContent = new ArrayList<>();
    private RecyclerView  recyclerView;
    private FloatingActionButton fab;
    //private List<String>
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_one,container,false);

        fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        fab.setOnClickListener(this);
        dbHelper = new MyDatabaseHelper(getActivity(),"DiaryList.db",null,1);

        recyclerView = (RecyclerView) mView.findViewById(R.id.diary_review);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        initData();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mContent,Titles,mMonth,mDay,mWeek);
        recyclerView.setAdapter(recyclerViewAdapter);



        return mView;
    }
    private void initData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Diary",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String titles = cursor.getString(cursor.getColumnIndex("title"));
                int Month = cursor.getInt(cursor.getColumnIndex("month")) + 1;
                int day = cursor.getInt(cursor.getColumnIndex("day"));
                int week = cursor.getInt(cursor.getColumnIndex("week")) - 1;
                String content = cursor.getString(cursor.getColumnIndex("content"));
                mContent.add(content);
                Titles.add(titles);
                mMonth.add(Month);
                mDay.add(day);
                mWeek.add(week);
            }while (cursor.moveToNext());
            cursor.close();
        }
    }

    @Override
    public void onClick(View v) {
        //
        dbHelper.getWritableDatabase();

        Intent i = new Intent(getActivity(),CreateDiary_Activity.class);
        startActivity(i);
    }
}
