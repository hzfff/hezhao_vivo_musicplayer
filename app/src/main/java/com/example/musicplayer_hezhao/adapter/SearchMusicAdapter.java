package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;

/**
 * Created by 11120555 on 2020/8/6 10:40
 */
public class SearchMusicAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SearchMusicCallback  searchMusicCallback;
    private Context mContext;
    private OnItemClickListener OnItemClickListener;
    private View view;

    public SearchMusicAdapter(SearchMusicCallback searchMusicCallback, Context mContext) {
        this.searchMusicCallback = searchMusicCallback;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(mContext, R.layout.search_music_item, null);
        return new SearchMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       SearchMusicHolder searchMusicHolder= (SearchMusicHolder) holder;
       searchMusicHolder.song.setText(searchMusicCallback.getResult().getSongs().get(position).getName());
       searchMusicHolder.artist.setText(searchMusicCallback.getResult().getSongs().get(position).getArtists().get(0).getName());
       searchMusicHolder.num.setText((position+1)+"");
       if(OnItemClickListener!=null)
       {
           searchMusicHolder.song.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int pos = holder.getLayoutPosition();
                   OnItemClickListener.onItemClick(((SearchMusicHolder) holder).song,pos);
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return searchMusicCallback.getResult().getSongs().size();
    }
    static  class SearchMusicHolder  extends RecyclerView.ViewHolder{
        private TextView song;
        private TextView artist;
        private TextView num;
        private ImageView imageView;
        public SearchMusicHolder(@NonNull View itemView) {
            super(itemView);
            song=itemView.findViewById(R.id.text1);
            artist=itemView.findViewById(R.id.text2);
            num=itemView.findViewById(R.id.num);
            imageView=itemView.findViewById(R.id.image_thumb);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }
}
