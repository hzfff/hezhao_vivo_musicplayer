package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/10 11:29
 */
public class MyMusicBottomAdapter extends RecyclerView.Adapter<MyMusicBottomAdapter.MyMusicHolder> {
    private List<MusicListModel> musicListModels;
    private Context mContext;
    private OnItemClickListener OnItemClickListener;

    public MyMusicBottomAdapter(List<MusicListModel> listModels, Context mContext) {
        this.musicListModels = listModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.my_music_recyclerview_item, null);
        return new MyMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyMusicHolder holder, int position) {
        holder.mymusic_induction.setText(musicListModels.get(position).getMusicListName());
        holder.mymusic_number.setText((musicListModels.get(position).getMusicName().size()) + "首");
        if (musicListModels.get(position).getMusicName().size() > 0) {
            holder.mymusic_image.setImageBitmap(Util.CreateBitmap(mContext.getContentResolver(),
                    Uri.parse(musicListModels.get(position).getMusicName().get(0).getAlbumUri())));
        } else {
            holder.mymusic_image.setImageResource(R.mipmap.pic12);
        }
        holder.mymusic_induction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        if (OnItemClickListener != null) {
            holder.mymusic_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.mymusic_image, position);
                }
            });
            holder.mymusic_induction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.mymusic_induction, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return musicListModels.size();
    }

    public class MyMusicHolder extends RecyclerView.ViewHolder {
        public ImageView mymusic_image;
        public TextView mymusic_induction;
        public TextView mymusic_number;

        public MyMusicHolder(@NonNull View itemView) {
            super(itemView);
            mymusic_image = itemView.findViewById(R.id.my_music_img);
            mymusic_induction = itemView.findViewById(R.id.my_music_text);
            mymusic_number = itemView.findViewById(R.id.my_music_number);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }

}
