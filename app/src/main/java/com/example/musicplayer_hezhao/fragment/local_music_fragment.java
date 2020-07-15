package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.downmusicrecycleradapter;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 9:46
 */
public class local_music_fragment  extends Fragment {
   private  RecyclerView  recyclerView;
   private View view;
   private downmusicrecycleradapter  adpter;
   private List<String> music_list=new ArrayList<>();
   private List<String> name_list=new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.local_music_fragment,container,false);
        initData();
        initview();
        return view;
    }
    public void initData() {
        for(int i=0;i<14;i++){
            music_list.add("棋子");
        }
        for(int i=0;i<14;i++){
            name_list.add("王菲");
        }
    }
    public void initview(){
        recyclerView=view.findViewById(R.id.recyclerview);
        adpter=new downmusicrecycleradapter(music_list,name_list,getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adpter);
        adpter.setOnItemClickListener(new downmusicrecycleradapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ShowDialog bottomDialogFr = new ShowDialog();
                bottomDialogFr.show(getFragmentManager(), "hezhao");
            }
        });
    }
}
