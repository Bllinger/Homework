package com.example.dell.mydiary.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dell.mydiary.MySQL.MyDatabaseHelper;
import com.example.dell.mydiary.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by dell on 2017/2/23.
 */

public class SeeDiaryActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private ScrollView scrollView;
    private FloatingActionButton fab;
    private TextView textMonth;
    private TextView textDay;
    private TextView textWeek;
    private TextView textTitle;
    private TextView textContent;
    private MyDatabaseHelper dbHelper;
    private static String Title;
    private static String Content;
    private static int Month;
    private static int Day;
    private static int Week;
    private String[] change = {"一", "二", "三", "四", "五", "六", "七",
            "八", "九", "十", "十一", "十二"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_diary);
        scrollView = (ScrollView) findViewById(R.id.see_scrollview);
        textContent = (TextView) findViewById(R.id.see_content);
        textDay = (TextView) findViewById(R.id.see_day);
        textTitle = (TextView) findViewById(R.id.see_title);
        textMonth = (TextView) findViewById(R.id.see_month);
        textWeek = (TextView) findViewById(R.id.see_week);
        dbHelper = new MyDatabaseHelper(this,"DiaryList.db",null,1);
        fab = (FloatingActionButton) findViewById(R.id.see_fab);

        fab.setOnClickListener(this);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        scrollView.setOnTouchListener(this);


        Data();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void Data() {
        textTitle.setText(Title);
        textContent.setText(Content);
        textDay.setText(String.valueOf(Day) + "日");
        textWeek.setText("星期" + change[Week - 1]);
        textMonth.setText(change[Month] + "月");
    }


    public void receive(String title, String content, int month, int day, int week) {
        Title = title;
        Content = content;
        Month = month;
        Day = day;
        Week = week;
        Log.d("SeeDiaryActivity", String.valueOf(Day) + "日");

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

//        switch (v.getId()){
//            case R.id.diary_content:
//            case R.id.diary_title:
//                v.getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }

        return false;
    }

    @Override
    public void onClick(View v) {
        final CharSequence[] items = {"取消", "确定"};
        AlertDialog dialog = new AlertDialog.Builder(SeeDiaryActivity.this).setTitle("删除日记").setItems(items,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 1){
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            db.delete("Diary","content = ?",new String[] {Content});
                            Intent intent = new Intent(SeeDiaryActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            //
                        }

                    }
                }).create();
        dialog.show();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SeeDiary Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
