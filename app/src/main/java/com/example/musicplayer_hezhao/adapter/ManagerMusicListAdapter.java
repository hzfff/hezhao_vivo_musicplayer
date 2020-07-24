package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 11120555 on 2020/7/24 14:23
 */
public class ManagerMusicListAdapter extends RecyclerView.Adapter<ManagerMusicListAdapter.ManagerMusicListViewHolder> {
    private OnItemClickListener OnItemClickListener;
    private List<MusicListModel> musicListModelList;
    private Context mcontext;
    private HashMap<Integer, Boolean> isCheckedHasMap;
    public List<MusicListModel>getMusicListModelList(){
        return musicListModelList;
    }
    public ManagerMusicListAdapter(List<MusicListModel> list, Context context) {
        musicListModelList = list;
        mcontext = context;
        isCheckedHasMap = new HashMap<>();
        for (int i = 0; i < musicListModelList.size(); i++) {
            isCheckedHasMap.put(i, false);
        }
    }

    @NonNull
    @Override
    public ManagerMusicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext, R.layout.managermusiclayout, null);
        return new ManagerMusicListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ManagerMusicListViewHolder holder, int position) {
        if (musicListModelList.get(position).getMusicName().size() > 0) {
            holder.imageView.setImageBitmap(Util.CreateBitmap(mcontext.getContentResolver(),
                    Uri.parse(musicListModelList.get(position).getMusicName().get(0).getAlbumUri())));
        } else {
            holder.imageView.setImageResource(R.mipmap.pic12);
        }
        holder.text1.setText(musicListModelList.get(position).getMusicListName());
        holder.text2.setText("" + musicListModelList.get(position).getMusicName().size() + "首");
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                isCheckedHasMap.put(position, !isCheckedHasMap.get(position));
                notifyDataSetChanged();
            }
        });
        holder.checkBox.setChecked(isCheckedHasMap.get(position));

    }

    public HashMap<Integer, Boolean> getHashMap() {
        return  isCheckedHasMap ;
    }

public void addall(){
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHasMap.entrySet();
        boolean shouldSelectedAll = false;
        for (Map.Entry<Integer, Boolean> entryset : entries) {
            Boolean aBoolean = entryset.getValue();
            if (!aBoolean) {
                shouldSelectedAll = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entryset : entries) {
            entryset.setValue(shouldSelectedAll);
        }
        notifyDataSetChanged();
}
public void cancelall(){
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHasMap.entrySet();
        for (Map.Entry<Integer, Boolean> entryset : entries) {
            entryset.setValue(!entryset.getValue());
        }
        notifyDataSetChanged();
}

    @Override
    public int getItemCount() {
        return musicListModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }

    public class ManagerMusicListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView text1;
        private TextView text2;
        private CheckBox checkBox;

        public ManagerMusicListViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            imageView = itemView.findViewById(R.id.image1);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }
}
