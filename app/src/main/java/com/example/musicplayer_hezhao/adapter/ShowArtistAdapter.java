package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.model.findsongs;

/**
 * Created by 11120555 on 2020/8/7 11:44
 */
public class ShowArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private findsongs songs;
    private Context mContext;
    private static View mView;
    private OnItemClickListener onItemClickListener;

    public ShowArtistAdapter(findsongs songs, Context mContext) {
        this.songs = songs;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = View.inflate(mContext, R.layout.history_vedio_item, null);
        return new ShowArtistHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      ShowArtistHolder showArtistHolder= (ShowArtistHolder) holder;
      showArtistHolder.Song.setText(songs.getSongs().get(position).getName());
      showArtistHolder.Name.setText(songs.getSongs().get(position).getAr().get(0).getName());
      if(onItemClickListener!=null){
          showArtistHolder.Song.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int position=showArtistHolder.getLayoutPosition();
                  onItemClickListener.onItemClick(showArtistHolder.Song,position);
              }
          });
      }
    }

    @Override
    public int getItemCount() {
        return songs.getSongs().size();
    }

    static class ShowArtistHolder extends RecyclerView.ViewHolder {
        private TextView Song;
        private ImageView detail;
        private TextView  Name;

        public ShowArtistHolder(@NonNull View itemView) {
            super(itemView);
            Song = mView.findViewById(R.id.text1);
            Name = mView.findViewById(R.id.text2);
            detail = mView.findViewById(R.id.image_thumb);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
