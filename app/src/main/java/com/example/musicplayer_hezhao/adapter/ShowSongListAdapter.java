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

import com.example.musicplayer_hezhao.PlayMusicListActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.SongID;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/3 10:57
 */
public class ShowSongListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SongID> Song_ID;
    private List<String> Song_List;
    private List<MusicInfo> Music_Info;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private static View view;

    public ShowSongListAdapter(List<SongID> song_ID, List<String> song_list, List<MusicInfo> music_Info, Context context) {
        Song_ID = song_ID;
        Song_List = song_list;
        Music_Info = music_Info;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(mContext, R.layout.history_vedio_item, null);
        return new FindMusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FindMusicViewHolder findMusicViewHolder = (FindMusicViewHolder) holder;
        findMusicViewHolder.music_describe.setText(Music_Info.get(position).getSongs()[0].getName());
        findMusicViewHolder.music_listen_num.setText(Music_Info.get(position).getSongs()[0].getAr()[0].getName());
        if(onItemClickListener!=null) {
            findMusicViewHolder.music_describe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=holder.getLayoutPosition();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("musicUrl", (Serializable) Song_List);
                    bundle.putSerializable("musicInfo", (Serializable) Music_Info);
                    bundle.putInt("position",position);
                    Intent intent=new Intent(mContext, PlayMusicListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Music_Info.size();
    }

    static class FindMusicViewHolder extends RecyclerView.ViewHolder {
        private ImageView music_imageView;
        private TextView music_describe;
        private TextView music_listen_num;

        public FindMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            music_imageView = view.findViewById(R.id.image_thumb);
            music_describe = view.findViewById(R.id.text1);
            music_listen_num = view.findViewById(R.id.text2);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
