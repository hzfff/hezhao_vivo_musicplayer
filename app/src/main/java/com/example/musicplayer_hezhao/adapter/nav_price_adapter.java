package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/20 11:57
 */
public class nav_price_adapter extends RecyclerView.Adapter {
    private List<Integer> list = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public nav_price_adapter(List<Integer> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.nav_price_adapter_layout, null);
        return new nav_price_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        nav_price_viewholder nav_price_viewholder = (nav_price_adapter.nav_price_viewholder) holder;
        nav_price_viewholder.imageView.setImageResource(list.get(position));
        if (onItemClickListener != null) {
            nav_price_viewholder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = nav_price_viewholder.getLayoutPosition();
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class nav_price_viewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public nav_price_viewholder(View itemview) {
            super(itemview);
            imageView = itemview.findViewById(R.id.image);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
