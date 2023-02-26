package com.example.winterbreakproject.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winterbreakproject.R;
import com.example.winterbreakproject.vo.TodayTipVO;

import java.util.ArrayList;

public class TodayTipDAO extends RecyclerView.Adapter {

    Context context;
    ArrayList<TodayTipVO> voArrayList;

    public TodayTipDAO(Context context, ArrayList<TodayTipVO> voArrayList) {
        this.context = context;
        this.voArrayList = voArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.activity_recycler,parent,false);

        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;

        TodayTipVO vo = voArrayList.get(position);
        vh.tt_id.setText(Integer.toString(vo.getId()));
        vh.tt_title.setText(vo.getTitle());
        vh.tt_contents.setText(vo.getContents());
    }

    @Override
    public int getItemCount() {
        return voArrayList.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tt_id;
        TextView tt_title;
        TextView tt_contents;

        public VH(@NonNull View itemView) {
            super(itemView);

            tt_id=itemView.findViewById(R.id.tt_id);
            tt_title=itemView.findViewById(R.id.tt_title);
            tt_contents=itemView.findViewById(R.id.tt_contents);

        }
    }
}
