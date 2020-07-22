package com.example.musicplayer_hezhao.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
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
 * Created by 11120555 on 2020/7/21 10:33
 */
public class LocalMusic_Singer_Adapter extends RecyclerView.Adapter<LocalMusic_Singer_Adapter.MusicSingerViewHolder> {
    private OnItemClickListener OnItemClickListener;
    private Context Context;
    private List<List<Music>> Music_list = new ArrayList<>();
    private ImageView imageView;

    public LocalMusic_Singer_Adapter(List<List<Music>> music_list, Context context) {
        Music_list = music_list;
        Context = context;
    }

    @NonNull
    @Override
    public MusicSingerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(Context, R.layout.history_singer_item, null);
        imageView=view.findViewById(R.id.images);
        return new MusicSingerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicSingerViewHolder holder, int position) {
        holder.vedio_name.setText(Music_list.get(position).get(0).getArtist());
        holder.vedio_num.setText(String.valueOf(Music_list.get(position).size()) + "首");
        holder.vedio_img.setImageBitmap(Util.CreateBitmap(Context.getContentResolver(), Uri.parse(Music_list.get(position).get(0).MusicImage)));
        if (OnItemClickListener != null) {
           imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.vedio_name, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Music_list.size();
    }

    static class MusicSingerViewHolder extends RecyclerView.ViewHolder {
        private TextView vedio_name;
        private TextView vedio_num;
        private ImageView vedio_img;

        public MusicSingerViewHolder(@NonNull View itemView) {
            super(itemView);
            vedio_name = itemView.findViewById(R.id.text1);
            vedio_num = itemView.findViewById(R.id.text2);
            vedio_img = itemView.findViewById(R.id.image);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }
}
