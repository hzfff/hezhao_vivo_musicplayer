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
public class MV_Adapter extends RecyclerView.Adapter<MV_Adapter.MV_AdapterHolder> {
    private List<VedioInformation>List_MV;
    private downmusicrecycleradapter.OnItemClickListener onItemClickListener;
    private Context mContext;
    private static View view;

    public MV_Adapter(List<VedioInformation> infos, Context context) {
        List_MV = infos;
        mContext = context;
    }

    @NonNull
    @Override
    public MV_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(mContext, R.layout.vedioplayerlayout, null);
        return new MV_AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MV_AdapterHolder holder, int position) {
        Map<String,String> map= (Map<String, String>) List_MV.get(position).getData().getBrs();
        String URl=null;
        if(map.containsKey("1080")){
            URl=map.get("1080");
        }else if(map.containsKey("720"))
        {
            URl=map.get("720");
        }else if(map.containsKey("480"))
        {
            URl=map.get("480");
        }else if(map.containsKey("240"))
        {
            URl=map.get("240");
        }
        boolean setUp = holder.playerStandard.setUp(URl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(mContext).load(List_MV.get(position).getData().getCover()).into(holder.playerStandard.thumbImageView);
        }
        holder.textView.setText(List_MV.get(position).getData().getName());
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class MV_AdapterHolder extends RecyclerView.ViewHolder {
        TextView textView;
        JCVideoPlayerStandard  playerStandard;
        public MV_AdapterHolder(@NonNull View itemView) {
            super(itemView);
            textView=view.findViewById(R.id.text);
            playerStandard=view.findViewById(R.id.player_list_video);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(downmusicrecycleradapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
