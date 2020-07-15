package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.downmusicrecycleradapter;
import com.example.musicplayer_hezhao.adapter.history_vedio_adapter;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 11:17
 */
public class history_vedio_fragment extends Fragment {
    private RecyclerView recyclerView;
    private history_vedio_adapter adapter;
    private View view;
    private List<String> vedio_name_list=new ArrayList<>();
    private List<String> create_name_list=new ArrayList<>();
    private List<Integer> img_list=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.history_vedio, container, false);
        initData();
        initview();
        return view;
    }
    public void initData(){
        for(int i=0;i<14;i++){
            vedio_name_list.add("棋子");
        }
        for(int i=0;i<14;i++){
            create_name_list.add("王菲");
        }
        for(int i=0;i<14;i++){
            img_list.add(R.mipmap.pic3);
        }
    }
    public void initview(){
      recyclerView=view.findViewById(R.id.recyclerview);
      adapter=new history_vedio_adapter(vedio_name_list,create_name_list,img_list,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new history_vedio_adapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                ShowDialog bottomDialogFr = new ShowDialog();
                bottomDialogFr.show(getFragmentManager(), "hezhao");
            }
        });
    }

}
