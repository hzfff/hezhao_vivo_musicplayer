package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.util.RoundImageView;

import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by 11120555 on 2020/7/31 17:23
 */
public class MV_Adapter extends RecyclerView.Adapter {
    private List<VedioInformation>List_MV;
    private downmusicrecycleradapter.OnItemClickListener onItemClickListener;
    private Context mContext;
    private static View view;
    public boolean isLoadMore = false;
    private final int view_Normal=1;
    private boolean index;
    public MV_Adapter(List<VedioInformation> infos,boolean indexs, Context context) {
        List_MV = infos;
        index=indexs;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            view = View.inflate(mContext, R.layout.vedioplayerlayout, null);
            return new MV_AdapterHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MV_AdapterHolder holders = (MV_AdapterHolder) holder;
            Map<String, String> map = (Map<String, String>) List_MV.get(position).getData().getBrs();
            String URl = null;
            if (map.containsKey("240")) {
                URl = map.get("240");
            } else if (map.containsKey("480")) {
                URl = map.get("480");
            } else if (map.containsKey("720")) {
                URl = map.get("720");
            } else if (map.containsKey("1080")) {
                URl = map.get("1080");
            }
            if (URl != null) {
                boolean setUp = holders.playerStandard.setUp(URl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                if (setUp) {
                    Glide.with(mContext).load(List_MV.get(position).getData().getCover()).into(holders.playerStandard.thumbImageView);
                }
                holders.textView.setText(List_MV.get(position).getData().getName());
            }
    }


    @Override
    public int getItemCount() {
        if(index) {
            return 5;
        }
        return 10;
    }

    //用于监控adapter的数据变化
    public void addList(List<VedioInformation> vedio) {
        List_MV.addAll(vedio);
        notifyDataSetChanged();
    }
    static class MV_AdapterHolder extends RecyclerView.ViewHolder {
        TextView textView;
        JCVideoPlayerStandard playerStandard;
        public MV_AdapterHolder(@NonNull View itemView) {
            super(itemView);
            textView=view.findViewById(R.id.text);
            playerStandard=view.findViewById(R.id.player_list_video);
        }
    }
    static class RefreshHolder extends RecyclerView.ViewHolder {
        public RefreshHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setIsLoadMore() {
        this.isLoadMore = true;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(downmusicrecycleradapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
