package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.model.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/29 15:06
 */
public class playmusicadapter extends RecyclerView.Adapter<playmusicadapter.MusicSingerViewHolder> {

    public OnItemClickListener OnItemClickListener;
    public Context Context;
    public List<Music> Music_list = new ArrayList<>();
    public View view;
    public boolean index=true;
    public playmusicadapter(List<Music> music_list, Context context) {
        Music_list = music_list;
        Context = context;
    }

    @NonNull
    @Override
    public MusicSingerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_layout_item, parent, false);
        return new MusicSingerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicSingerViewHolder holder, int position) {
        holder.name.setText(Music_list.get(position).getName());
        holder.imageView.setImageBitmap(Util.CreateBitmap(Context.getContentResolver(),
                Uri.parse(Music_list.get(position).getAlbumUri())));
        if (OnItemClickListener != null) {
            //点击歌名查看歌曲播放信息
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.name, position, 1);
                }
            });

            //点击播放
            holder.image_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    if (index) {
                        OnItemClickListener.onItemClick(holder.image_Btn, position, 2);
                        holder.image_Btn.setImageResource(R.mipmap.play);
                        index = false;
                    } else {
                        OnItemClickListener.onItemClick(holder.image_Btn, position, 3);
                        holder.image_Btn.setImageResource(R.mipmap.stop);
                        index = true;
                    }
                }
            });
            //歌曲表单
            holder.detail_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.detail_Btn, position, 4);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return Music_list.size();
    }

    static class MusicSingerViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        ImageView image_Btn;
        ImageView detail_Btn;

        public MusicSingerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.play_music_text);
            imageView = itemView.findViewById(R.id.play_music_img);
            image_Btn = itemView.findViewById(R.id.play_music_buttom);
            detail_Btn = itemView.findViewById(R.id.play_music);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position,int index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }
}
