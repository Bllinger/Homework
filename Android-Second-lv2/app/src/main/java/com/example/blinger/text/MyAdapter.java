package com.example.blinger.text;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = mInflater.inflate(R.layout.item,parent,false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView mTextView;

        MyHolder(View itemView){
            super(itemView);
            mTextView = (TextView) itemView
                    .findViewById(R.id.tv);
        }
    }
}
