package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 11:31
 */
public class history_vedio_adapter extends RecyclerView.Adapter<history_vedio_adapter.history_vedio_viewholder> {
    private OnItemClickListener onItemClickListener;
    private List<String> vedio_name = new ArrayList<>();
    private List<String> create_name = new ArrayList<>();
    private List<Integer> vedio_img = new ArrayList<>();
    private Context context;

    public history_vedio_adapter(List<String> vedio_name, List<String> create_name, List<Integer> vedio_img, Context context) {
        this.vedio_name = vedio_name;
        this.create_name = create_name;
        this.vedio_img = vedio_img;
        this.context = context;
    }

    @NonNull
    @Override
    public history_vedio_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.history_vedio_item, null);
        return new history_vedio_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final history_vedio_viewholder holder, int position) {
    holder.vedio_name.setText(vedio_name.get(position));
    holder.vedio_create.setText(create_name.get(position));
    holder.vedio_img.setImageDrawable(ContextCompat.getDrawable(context,vedio_img.get(position)));
        if(onItemClickListener!=null)
        {
            holder.vedio_img.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.vedio_img,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return vedio_name.size();
    }

    class history_vedio_viewholder extends RecyclerView.ViewHolder {
        private TextView  vedio_name;
        private TextView  vedio_create;
        private ImageView vedio_img;
        public history_vedio_viewholder(@NonNull View itemView) {
            super(itemView);
            vedio_name=itemView.findViewById(R.id.text1);
            vedio_create=itemView.findViewById(R.id.text2);
            vedio_img=itemView.findViewById(R.id.image);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
