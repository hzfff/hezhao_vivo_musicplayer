package com.example.musicplayer_hezhao.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 14:10
 */
public class downmusicrecycleradapter extends RecyclerView.Adapter<downmusicrecycleradapter.myViewHolder> {
    private List<String> list_name;
    private List<String> list_singer;
    private Context context;
    private OnItemClickListener onItemClickListener;
    public downmusicrecycleradapter(List<String> list_name, List<String> list_singer, Context context) {
        this.list_name = list_name;
        this.list_singer = list_singer;
        this.context = context;
    }
    //在adapter自定义一个接口实现想要实现的方法
    public interface  OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.downmusicrecycleradapter, null);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {
        holder.music_name.setText(list_name.get(position));
        holder.singer_name.setText(list_singer.get(position));
        if(onItemClickListener!=null)
        {
            holder.music_name.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.music_name,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list_name.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
       private TextView music_name;
       private TextView singer_name;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            music_name=itemView.findViewById(R.id.music_name);
            singer_name=itemView.findViewById(R.id.singer_name);
        }
    }
}
