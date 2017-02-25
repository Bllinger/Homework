package com.example.dell.mydiary.Activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.dell.mydiary.MySQL.MyDatabaseHelper;
import com.example.dell.mydiary.R;

import java.io.FileNotFoundException;

import java.util.Calendar;

/**
 * Created by dell on 2017/2/21.
 */

public class CreateDiary_Activity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    private FloatingActionButton fab;
    private FloatingActionButton fabAdd;
    private ScrollView scrollView;
    private EditText editTextTitle;
    private EditText editTextContent;
    private TextView textViewMonth;
    private TextView textViewDay;
    private TextView textViewWeek;
    private int month;
    private int week;
    private int day;
    private int year;
    private static final int PHOTO_SUCCESS = 1;
    private static final int CAMERA_SUCCESS = 2;
    private String [] change = {"一","二","三","四","五","六","七",
            "八","九","十","十一","十二"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        scrollView = (ScrollView) findViewById(R.id.diary_sv);
        editTextContent = (EditText) findViewById(R.id.diary_content);
        editTextTitle = (EditText) findViewById(R.id.diary_title);
        textViewDay = (TextView) findViewById(R.id.diary_day_text);
        textViewMonth = (TextView) findViewById(R.id.diary_month_text);
        textViewWeek = (TextView) findViewById(R.id.diary_week_text);
        fab = (FloatingActionButton) findViewById(R.id.diary_fab);
        fabAdd = (FloatingActionButton) findViewById(R.id.diary_fab_add);

        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        fab.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
        fabAdd.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        dbHelper = new MyDatabaseHelper(this,"DiaryList.db",null,1);
        Calendar calendar = Calendar.getInstance();

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        week = calendar.get(Calendar.DAY_OF_WEEK);

        initTime(month,week,day);

        scrollView.setOnTouchListener(this);

    }

    private void initTime(int mmonth,int mweek,int mday){
        textViewMonth.setText(change[mmonth]+"月");
        textViewWeek.setText("星期"+change[mweek-2]);
        textViewDay.setText(mday+"日");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.diary_content:
            case R.id.diary_title:
                v.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        //

        switch (v.getId()){
            case R.id.diary_fab:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("title",editTextTitle.getText().toString());
                values.put("content",editTextContent.getText().toString());
                values.put("month",month);
                values.put("day",day);
                values.put("week",week);
                db.insert("Diary",null,values);
                values.clear();

                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateDiary_Activity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.diary_fab_add:
                //

                if (ContextCompat.checkSelfPermission(CreateDiary_Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CreateDiary_Activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                final CharSequence[] items = { "手机相册", "相机拍摄" };
                AlertDialog dlg = new AlertDialog.Builder(CreateDiary_Activity.this).setTitle("选择图片").setItems(items,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int item) {

                                if(item==1){
                                    Intent getImageByCamera= new Intent("android.media.action.IMAGE_CAPTURE");

                                    startActivityForResult(getImageByCamera, CAMERA_SUCCESS);
                                }else{
                                    Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                                    getImage.addCategory(Intent.CATEGORY_OPENABLE);
                                    getImage.setType("image/*");
                                    startActivityForResult(getImage, PHOTO_SUCCESS);
                                }
                            }
                        }).create();
                dlg.show();
                break;
            default:
                //
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        ContentResolver resolver = getContentResolver();
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_SUCCESS:

                    Uri originalUri = intent.getData();
                    Bitmap bitmap = null;
                    try {
                        Bitmap originalBitmap = BitmapFactory.decodeStream(resolver.openInputStream(originalUri));
                        bitmap = resizeImage(originalBitmap, 200, 200);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(bitmap != null){

                        ImageSpan imageSpan = new ImageSpan(CreateDiary_Activity.this, bitmap);

                        SpannableString spannableString = new SpannableString("[local]"+1+"[/local]");

                        spannableString.setSpan(imageSpan, 0, "[local]1[local]".length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        int index = editTextContent.getSelectionStart();
                        Editable edit_text = editTextContent.getEditableText();
                        if(index <0 || index >= edit_text.length()){
                            edit_text.append(spannableString);
                        }else{
                            edit_text.insert(index, spannableString);
                        }
                    }else{
                        Toast.makeText(CreateDiary_Activity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CAMERA_SUCCESS:
                    Bundle extras = intent.getExtras();
                    Bitmap originalBitmap1 = (Bitmap) extras.get("data");
                    if(originalBitmap1 != null){
                        bitmap = resizeImage(originalBitmap1, 200, 200);

                        ImageSpan imageSpan = new ImageSpan(CreateDiary_Activity.this, bitmap);

                        SpannableString spannableString = new SpannableString("[local]"+1+"[/local]");

                        spannableString.setSpan(imageSpan, 0, "[local]1[local]".length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        int index = editTextContent.getSelectionStart();
                        Editable edit_text = editTextContent.getEditableText();
                        if(index <0 || index >= edit_text.length()){
                            edit_text.append(spannableString);
                        }else{
                            edit_text.insert(index, spannableString);
                        }
                    }else{
                        Toast.makeText(CreateDiary_Activity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private Bitmap resizeImage(Bitmap originalBitmap, int newWidth, int newHeight){
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();

        float scanleWidth = (float)newWidth/width;
        float scanleHeight = (float)newHeight/height;

        Matrix matrix = new Matrix();

        matrix.postScale(scanleWidth,scanleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap,0,0,width,height,matrix,true);
        return resizedBitmap;
    }

}

