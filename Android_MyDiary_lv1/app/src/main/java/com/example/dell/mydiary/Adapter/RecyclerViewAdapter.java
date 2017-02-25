package com.example.dell.mydiary.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.mydiary.Activity.SeeDiaryActivity;
import com.example.dell.mydiary.R;

import java.util.List;


/**
 * Created by dell on 2017/2/19.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>{
    private SeeDiaryActivity seeDiaryActivity;
    private List<String> mList;
    private List <Integer> ListMonth;
    private List<Integer> ListDay;
    private List<Integer> ListWeek;
    private List<String> ListContent;

    public RecyclerViewAdapter(List<String> Content,List<String> List , List<Integer> Month, List<Integer> Day, List<Integer> Week){
        mList = List;
        ListDay = Day;
        ListMonth = Month;
        ListWeek = Week;
        ListContent = Content;
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextView mTextViewMonth;
        TextView mTextViewDay;
        TextView mTextViewWeek;
        View diaryView;

        public MyHolder(View itemView) {
            super(itemView);
            diaryView = itemView;

            mTextView = (TextView) itemView.findViewById(R.id.item_view);
            mTextViewDay = (TextView) itemView.findViewById(R.id.itme_day);
            mTextViewMonth = (TextView) itemView.findViewById(R.id.item_month);
            mTextViewWeek = (TextView) itemView.findViewById(R.id.item_week);
        }
    }

    @Override
    public RecyclerViewAdapter.MyHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
         final MyHolder holder = new MyHolder(view);
        holder.diaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                seeDiaryActivity = new SeeDiaryActivity();
                seeDiaryActivity.receive(mList.get(position),ListContent.get(position),ListMonth.get(position)
                ,ListDay.get(position),ListWeek.get(position));
                //Toast.makeText(v.getContext(),test,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),SeeDiaryActivity.class);
                v.getContext().startActivity(intent);
                //Toast.makeText(v.getContext(),"you clicked view",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyHolder holder, int position) {
            holder.mTextView.setText(mList.get(position));
            holder.mTextViewWeek.setText("星期"+ListWeek.get(position));
            holder.mTextViewMonth.setText(ListMonth.get(position)+"月");
            holder.mTextViewDay.setText(ListDay.get(position)+"日");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}