package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 10:38
 */
public class local_singer_fragment_adapter extends RecyclerView.Adapter<local_singer_fragment_adapter.myViewHolder> {
    private Context mcontext;
    private List<String> name_list;
    private List<String> num_list;
    private OnItemClickListener onItemClickListener;
    public local_singer_fragment_adapter(Context mcontext, List<String> name_list, List<String> num_list) {
        this.mcontext = mcontext;
        this.name_list = name_list;
        this.num_list = num_list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext, R.layout.local_singer_item, null);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {
        holder.music_num.setText(num_list.get(position));
        holder.singer_name.setText(name_list.get(position));
        if(onItemClickListener!=null)
        {
            holder.singer_name.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.singer_name,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return name_list.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView music_num;
        private TextView singer_name;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            music_num = itemView.findViewById(R.id.music_num);
            singer_name = itemView.findViewById(R.id.singer_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener  onItemClickListener)
    {
        this.onItemClickListener=onItemClickListener;
    }
}
