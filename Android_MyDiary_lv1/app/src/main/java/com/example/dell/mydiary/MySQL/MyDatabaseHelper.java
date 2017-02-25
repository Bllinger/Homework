package com.example.dell.mydiary.MySQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by dell on 2017/2/22.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_DIARY = "create table Diary("
            +"id integer primary key autoincrement,"
            +"title text,"
            +"content text,"
            +"month integer,"
            +"day integer,"
            +"week integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
