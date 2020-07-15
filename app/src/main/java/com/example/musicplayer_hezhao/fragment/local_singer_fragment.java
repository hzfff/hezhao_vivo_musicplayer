package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.local_music_collect_adapter;
import com.example.musicplayer_hezhao.adapter.local_singer_fragment_adapter;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 9:47
 */
public class local_singer_fragment extends Fragment {
    private RecyclerView recyclerView;
    private local_singer_fragment_adapter adapter;
    private View view;
    private List<String> music_name_list = new ArrayList<>();
    private List<String> music_num_list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.local_music_fragment, container, false);
        initview();
        initdata();
        return view;
    }

    public void initview() {
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new local_singer_fragment_adapter(getActivity(), music_name_list, music_num_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setOnItemClickListener(new local_singer_fragment_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO
                Toast.makeText(getContext(),"hezhao",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initdata() {
        for (int i = 0; i < 14; i++) {
            music_num_list.add("17 ");
        }
        for (int i = 0; i < 14; i++) {
            music_name_list.add("王菲");
        }
    }
}
