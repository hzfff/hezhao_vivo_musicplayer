package com.example.musicplayer_hezhao.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.HotMusicModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/8 17:51
 */
public class HotMusicAdapter extends RecyclerView.Adapter<HotMusicAdapter.HotMusicHolder> {
    private HotMusic hotMusic;
    private OnItemClickListener onItemClickListener;

    static class HotMusicHolder extends RecyclerView.ViewHolder {
        private TextView hot_music_Id;
        private TextView hotmusic_music_name;
        private TextView hotmusic_music_instruction;
        private TextView hot_music_number;

        public HotMusicHolder(@NonNull View itemView) {
            super(itemView);
            hot_music_Id = itemView.findViewById(R.id.hot_music_Id);
            hotmusic_music_name = itemView.findViewById(R.id.hotmusic_music_name);
            hotmusic_music_instruction = itemView.findViewById(R.id.hotmusic_music_instruction);
            hot_music_number = itemView.findViewById(R.id.hot_music_number);
        }
    }

    public HotMusicAdapter(HotMusic hotMusic) {
        this.hotMusic = hotMusic;
    }

    @NonNull
    @Override
    public HotMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotmusic, parent, false);
        final HotMusicHolder hotMusicHolder = new HotMusicHolder(view);
        return hotMusicHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotMusicHolder holder, int position) {
        HotMusic.DataMusic dataMusic = hotMusic.getData().get(position);
        holder.hot_music_Id.setText(position + 1 + "");
        holder.hotmusic_music_name.setText(dataMusic.getSearchWord());
        holder.hotmusic_music_instruction.setText(dataMusic.getContent());
        holder.hot_music_number.setText(dataMusic.getScore() + "");
        if (position < 3) {
            holder.hot_music_number.setTextColor(Color.RED);
        }
        if (onItemClickListener != null) {
            holder.hotmusic_music_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hotMusic.getData().size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
