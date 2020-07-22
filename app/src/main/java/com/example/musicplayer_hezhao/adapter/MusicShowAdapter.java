package com.example.musicplayer_hezhao.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.model.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/20 9:01
 */
public class MusicShowAdapter extends RecyclerView.Adapter<MusicShowAdapter.MusicShowViewHolder> {
    private List<Music> MusicList = new ArrayList<>();
    private OnItemClickListener OnItemClickListener;
    private Context Context;
    private View view;
    private final  int TEMP_FIRST=1;
    private final  int TEMP_SECOND=2;
    private ImageView imageView;
    public MusicShowAdapter(List<Music>musicList, Context context) {
        this.MusicList=musicList;
        this.Context=context;
    }

    @NonNull
    @Override
    public MusicShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(Context, R.layout.history_vedio_item, null);
        imageView=view.findViewById(R.id.image);
        return new MusicShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicShowViewHolder holder, int position) {
        holder.vedio_name.setText(MusicList.get(position).getName());
        holder.vedio_create.setText(MusicList.get(position).getArtist());
        if(OnItemClickListener!=null)
        {
            imageView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(TEMP_FIRST,holder.vedio_name,position);
                }
            });

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(TEMP_SECOND,holder.imageView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return MusicList.size();
    }

    static class MusicShowViewHolder extends RecyclerView.ViewHolder {
        private TextView vedio_name;
        private TextView  vedio_create;
        private ImageView imageView;
        public MusicShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_thumb);
            vedio_name=itemView.findViewById(R.id.text1);
            vedio_create=itemView.findViewById(R.id.text2);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int index,View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }
}
