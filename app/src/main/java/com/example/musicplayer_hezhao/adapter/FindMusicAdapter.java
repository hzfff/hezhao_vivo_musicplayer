package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.ShowSongList;
import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.util.RoundImageView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/31 14:09
 */
public class FindMusicAdapter extends RecyclerView.Adapter<FindMusicAdapter.FindMusicViewHolder> {
    private List<Info> List_Sings;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private static View view;
    private boolean index=false;

    public FindMusicAdapter(List<Info> infos, Context context,boolean indexs) {
        List_Sings = infos;
        mContext = context;
        index=indexs;
    }

    @NonNull
    @Override
    public FindMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(!index) {
            view = View.inflate(mContext, R.layout.findmusicadapterlayout, null);
        }else{
            view = View.inflate(mContext, R.layout.findmusiclayout, null);
        }
        return new FindMusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindMusicViewHolder holder, int position) {
        if(!index){
        Glide.with(mContext).load(List_Sings.get(position).getCoverImgUrl()).into(holder.music_imageView);
        holder.music_describe.setText(List_Sings.get(position).getName());
        int num = List_Sings.get(position).getPlayCount();
        if (num < 10000) {
            holder.music_listen_num.setText(num);
        } else {
            holder.music_listen_num.setText(num / 10000 + "万");
        }
        }else{
            Glide.with(mContext).load(List_Sings.get(position).getCoverImgUrl()).into(holder.music_imageView);
            holder.music_describe.setText(List_Sings.get(position).getName());
        }
        holder.music_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info info=List_Sings.get(position);
                Intent intent=new Intent(mContext.getApplicationContext(), ShowSongList.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("info", (Serializable) info);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(index){
            return List_Sings.size();
        }
        return 10;
    }

    static class FindMusicViewHolder extends RecyclerView.ViewHolder {
        private ImageView music_imageView;
        private TextView music_describe;
        private TextView music_listen_num;

        public FindMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            music_imageView = view.findViewById(R.id.imagess);
            music_describe = view.findViewById(R.id.text);
            music_listen_num = view.findViewById(R.id.text1);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
