package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;

import java.util.ArrayList;

/**
 * Created by 11120555 on 2020/7/9 16:24
 */

public class MyMusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private GyroscopeObserver gyroscopeObserver;
        private Context mContext;
        private ArrayList<String> mDatas;
        private int mCreatedHolder=0;
        private int[] mPics = {R.mipmap.favorite_music_pic1,R.mipmap.favorite_music_pic2,
                R.mipmap.favorite_music_pic3,R.mipmap.favorite_music_pic4,R.mipmap.favorite_music_pic5,R.mipmap.favorite_music_pic6};
        public MyMusicAdapter(Context context, ArrayList<String> datas) {
            mContext = context;
            mDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            mCreatedHolder++;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            return new NormalHolder(inflater.inflate(R.layout.mymusic_middle_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.mTV.setText(mDatas.get(position));
            normalHolder.mImg.setImageDrawable(mContext.getResources().getDrawable(mPics[position]));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public class NormalHolder extends RecyclerView.ViewHolder {
            public TextView mTV;
            public ImageView mImg;

            public NormalHolder(View itemView) {
                super(itemView);

//                mTV = (TextView) itemView.findViewById(R.id.my_favorite_music_text);
                mImg = (ImageView)itemView.findViewById(R.id.favorite_image_view1);

            }
        }
    }


