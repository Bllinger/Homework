package com.example.blinger.homework_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/8.
 */

public class Myfragment extends Fragment {
    private String name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
             name = bundle.get("name").toString();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,null);

        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(name);
        return view;
    }

    public static Myfragment newInstance(String name){
        Bundle args = new Bundle();
        args.putString("name",name);
        Myfragment myfragment = new Myfragment();
        myfragment.setArguments(args);
        return myfragment;
    }
}
