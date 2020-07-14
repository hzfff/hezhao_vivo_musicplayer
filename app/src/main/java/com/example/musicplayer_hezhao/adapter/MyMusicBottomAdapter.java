package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/10 11:29
 */
public class MyMusicBottomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer>list_image=new ArrayList<>();
    private List<String> list_induction = new ArrayList<>();
    private List<String> list_number = new ArrayList<>();
    private Context mContext;

    public MyMusicBottomAdapter(List<Integer> list_image, List<String> list_induction, List<String> list_number, Context mContext) {
        this.list_image = list_image;
        this.list_induction = list_induction;
        this.list_number = list_number;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MyMusicHolder(inflater.inflate(R.layout.my_music_recyclerview_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyMusicHolder myMusicHolder = (MyMusicHolder) holder;
        myMusicHolder.mymusic_image.setImageDrawable(mContext.getResources().getDrawable(list_image.get(position)));
        myMusicHolder.mymusic_induction.setText(list_induction.get(position));
        myMusicHolder.mymusic_number.setText(list_number.get(position));
    }

    @Override
    public int getItemCount() {
        return list_image.size();
    }

    public class MyMusicHolder extends RecyclerView.ViewHolder {
        public ImageView mymusic_image;
        public TextView mymusic_induction;
        public TextView mymusic_number;

        public MyMusicHolder(@NonNull View itemView) {
            super(itemView);
            mymusic_image=itemView.findViewById(R.id.my_music_img);
            mymusic_induction=itemView.findViewById(R.id.my_music_text);
            mymusic_number=itemView.findViewById(R.id.my_music_number);
        }
    }
}
