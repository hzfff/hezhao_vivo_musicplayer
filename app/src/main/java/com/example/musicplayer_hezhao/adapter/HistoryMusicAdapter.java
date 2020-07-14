package com.example.musicplayer_hezhao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.model.HistoryModel;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/8 17:08
 */
public class HistoryMusicAdapter extends RecyclerView.Adapter<HistoryMusicAdapter.HistoryMusicViewHolder> {
    private List<HistoryModel> historyModelList;
    static class HistoryMusicViewHolder  extends RecyclerView.ViewHolder{
        private TextView mHistoryMusic;
        public HistoryMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            mHistoryMusic=itemView.findViewById(R.id.hot_music_text);
        }
    }
    public HistoryMusicAdapter(List<HistoryModel> list) {
        this.historyModelList = list;
    }
    @NonNull
    @Override
    public HistoryMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historymusic,parent,false);
        final HistoryMusicViewHolder holder=new HistoryMusicViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryMusicViewHolder holder, int position) {
      HistoryModel historyModel=historyModelList.get(position);
      holder.mHistoryMusic.setText(historyModel.getMusicName());
    }


    @Override
    public int getItemCount() {
        return historyModelList.size();
    }
}
