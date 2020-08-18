package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.musicplayer_hezhao.model.Info;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/18 9:30
 */
public class MyMusicShowAdapter extends RecyclerView.Adapter {
    private List<String> List_Sings;
    private List<Integer>imageViewList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private static View view;

    public MyMusicShowAdapter(List<String> infos,List<Integer>list, Context context) {
        List_Sings = infos;
        imageViewList=list;
        mContext = context;
    }

    @NonNull
    @Override
    public FindMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(mContext, R.layout.mymusicshowlayout, null);
        return new FindMusicViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder  holder, int position) {
        FindMusicViewHolder findMusicViewHolder= (FindMusicViewHolder) holder;
        findMusicViewHolder.music_describe.setText(List_Sings.get(position));
        findMusicViewHolder.music_imageView.setImageResource(imageViewList.get(position));
    }

    @Override
    public int getItemCount() {
        return List_Sings.size();
    }

    static class FindMusicViewHolder extends RecyclerView.ViewHolder {
        private ImageView music_imageView;
        private TextView music_describe;

        public FindMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            music_imageView = view.findViewById(R.id.image);
            music_describe = view.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
