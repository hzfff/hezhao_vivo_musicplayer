package com.example.musicplayer_hezhao.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Service.MusicListService;
import com.example.musicplayer_hezhao.Service.MyFavoriteMusic_Service;
import com.example.musicplayer_hezhao.Song_Show;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;
import com.example.musicplayer_hezhao.model.MusicListModel;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by 11120555 on 2020/7/10 14:31
 */
public class MySelectFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyMusicBottomAdapter myMusicBottomAdapter;
    private List<MusicListModel> musiclistModel=new ArrayList<>() ;
    private MyServiceConn myServiceConn;
    private MusicListService.MusicServiceIBinder musicControl;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.my_music_recyclerview, null);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        Intent intent=new Intent(getActivity().getApplicationContext(),MusicListService.class);
        myServiceConn=new MyServiceConn();
        boolean flag= getActivity().getApplicationContext().bindService(intent,myServiceConn,BIND_AUTO_CREATE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.my_music_recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        myMusicBottomAdapter = new MyMusicBottomAdapter(musiclistModel, getContext());
        recyclerView.setAdapter(myMusicBottomAdapter);
        myMusicBottomAdapter.setOnItemClickListener(new MyMusicBottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
    }

    //初始化歌单列表
    public void initdata() {
        musiclistModel=musicControl.QueryMusicList();
    }

    class MyServiceConn implements ServiceConnection {//用于实现连接服务

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicControl = (MusicListService.MusicServiceIBinder) service;
            initdata();
            myMusicBottomAdapter = new MyMusicBottomAdapter(musiclistModel, getContext());
            recyclerView.setAdapter(myMusicBottomAdapter);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
