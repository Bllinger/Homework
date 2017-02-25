package com.example.dell.mydiary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.mydiary.R;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by dell on 2017/2/18.
 */

public class Fragment_two extends Fragment implements View.OnClickListener {
    View mView;
    private TextView mTextView_month;
    private TextView mTextView_day;
    private TextView mTextView_week;
    private Button mButton_Left;
    private Button mButton_Right;
    private int month;
    private int week;
    private int day;
    private int year;
    private String [] change = {"一","二","三","四","五","六","七",
            "八","九","十","十一","十二"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_two,container,false);

        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        week = calendar.get(Calendar.DAY_OF_WEEK);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        mButton_Left = (Button)mView.findViewById(R.id.Calendar_left_button);
        mButton_Right = (Button)mView.findViewById(R.id.Calendar_right_button);
        mButton_Left.setOnClickListener(this);
        mButton_Right.setOnClickListener(this);

        mTextView_month = (TextView)mView.findViewById(R.id.text_month);
        mTextView_day = (TextView)mView.findViewById(R.id.text_day);
        mTextView_week = (TextView)mView.findViewById(R.id.text_week);

        initData(month,day,week);
        return mView;
    }

    private void initData(int month,int day,int week){
        mTextView_month.setText(change[month]+"月");
        mTextView_day.setText(day+"日");
        mTextView_week.setText("星期"+change[week-2]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Calendar_left_button:
                day--;
                week--;
                if(week < 2){
                    week = 8;
                }
                if(day < 1){
                    month--;
                    if(month < 0){
                        month = 11;
                        year--;
                    }
                    switch (month + 1){
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            day = 31;
                            break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            day = 30;
                            break;
                        case 2:
                            if (year % 400 == 0  || year % 4 == 0 && year % 100 != 0){
                                day = 29;
                            }else {
                                day =28;
                            }
                            break;
                        default:
                    }
                }
                initData(month,day,week);
                break;
            case R.id.Calendar_right_button:
                day++;
                week++;
                if(week > 8){
                    week = 2;
                }
                switch (month + 1){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if(day > 31){
                            day = 1;
                            month++;
                            if(month > 11){
                                month = 0;
                            }
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if(day > 30) {
                            day = 1;
                            month++;
                        }
                        break;
                    case 2:
                        if (year % 400 == 0  || year % 4 == 0 && year % 100 != 0){
                            if(day > 29){
                                day = 1;
                                month++;
                            }
                        }else {
                            if(day > 28){
                                day = 1;
                                month++;
                            }
                        }
                        break;
                    default:
                }
                initData(month,day,week);
                break;
        }
    }
}
