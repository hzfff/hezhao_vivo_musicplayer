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
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.ShowArtistActivity;
import com.example.musicplayer_hezhao.model.huayu;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 11120555 on 2020/8/7 8:47
 */
public class HuaYuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private huayu HuaYu;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private static View mView;

    public HuaYuAdapter(huayu huaYu, Context mContext) {
        HuaYu = huaYu;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = View.inflate(mContext, R.layout.huayuitemlayout, null);
        return new FindMusicViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FindMusicViewHolder findMusicViewHolder = (FindMusicViewHolder) holder;
        findMusicViewHolder.name.setText(HuaYu.getArtists().get(position).getName());
        Glide.with(mContext).load(HuaYu.getArtists().get(position).getImg1v1Url()).into(findMusicViewHolder.image);
        if(onItemClickListener!=null)
        {
            findMusicViewHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, ShowArtistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("HuaYu",HuaYu);
                    bundle.putInt("position",position);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return HuaYu.getArtists().size();
    }

    static class FindMusicViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;

        public FindMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            image = mView.findViewById(R.id.image);
            name = mView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
