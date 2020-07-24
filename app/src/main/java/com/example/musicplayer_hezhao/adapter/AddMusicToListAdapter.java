package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/24 8:22
 */
public class AddMusicToListAdapter extends RecyclerView.Adapter<AddMusicToListAdapter.AddMusicToListViewHolder> {
    private Music Music;
    private List<MusicListModel> MusicListModel = new ArrayList<>();
    private Context Context;
    private OnItemClickListener OnItemClickListener;
    private ImageView img_title;
    private ImageView img_detail;
    private TextView title;

    public AddMusicToListAdapter(Music music, List<MusicListModel> musicListModels, Context context) {
        this.Music = music;
        this.MusicListModel = musicListModels;
        this.Context = context;
    }

    @NonNull
    @Override
    public AddMusicToListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(Context, R.layout.addmusictolistitem, null);
        return new AddMusicToListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddMusicToListViewHolder holder, int position) {
        holder.title.setText(MusicListModel.get(position).getMusicListName());
        holder.size.setText(""+MusicListModel.get(position).getMusicName().size()+"首");
        if (MusicListModel.get(position).getMusicName().size() > 0) {
            holder.img_title.setImageBitmap(Util.CreateBitmap(Context.getContentResolver(),
                    Uri.parse(MusicListModel.get(position).getMusicName().get(0).getAlbumUri())));
        } else {
            holder.img_title.setImageResource(R.mipmap.pic12);
        }
        if (OnItemClickListener != null) {
            holder.img_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.img_title, position, 0);
                }
            });
            holder.img_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.img_detail, position, 1);
                }
            });
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.onItemClick(holder.title, position, 2);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return MusicListModel.size();
    }

    public class AddMusicToListViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_title;
        private ImageView img_detail;
        private TextView title;
        private TextView size;

        public AddMusicToListViewHolder(@NonNull View itemView) {
            super(itemView);
            img_title = itemView.findViewById(R.id.my_music_img);
            img_detail = itemView.findViewById(R.id.detail);
            title = itemView.findViewById(R.id.my_music_text);
            size=itemView.findViewById(R.id.my_music_number);
        }
    }

    //设置点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position, int index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }

}
