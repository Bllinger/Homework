package com.example.blinger.text;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDates;
    private MyAdapter mMyAdapter;
    private Student mStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date(1000);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter = new MyAdapter(MainActivity.this,mDates));
    }

    protected void Date(int count)
    {
        mStudent = new Student();
        mDates = new ArrayList<String>();
//        for (int i = 'A'; i < 'z';i++){
//            mDates.add(""+(char) i);
//        }
        for (int i = 0;i < count;i++ ){
            String s = String.valueOf(i);
            mStudent.setName(s);
            mDates.add("Student"+mStudent.getName()+"   Grade  "+mStudent.getGrade());
        }
    }


}
