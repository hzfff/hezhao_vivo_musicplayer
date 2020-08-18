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
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.PlayMusicActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Service.MusicListService;
import com.example.musicplayer_hezhao.Service.MyFavoriteMusic_Service;
import com.example.musicplayer_hezhao.ShowMusicActivity;
import com.example.musicplayer_hezhao.Song_Show;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;
import com.example.musicplayer_hezhao.model.MusicListModel;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by 11120555 on 2020/7/10 14:31
 */
public class MySelectFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private MyMusicBottomAdapter myMusicBottomAdapter;
    private List<MusicListModel> musiclistModel = new ArrayList<>();
    private MyServiceConn myServiceConn;
    private MusicListService.MusicServiceIBinder musicControl;
    private View view;
    private String UserName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_music_recyclerview, null);
        UserName=super.username;
        initview();
        return view;
    }

    public void initview() {
        final Intent intent = new Intent(getActivity().getApplicationContext(), MusicListService.class);
        myServiceConn = new MyServiceConn();
        boolean flag = getActivity().getApplicationContext().bindService(intent, myServiceConn, BIND_AUTO_CREATE);
        recyclerView = view.findViewById(R.id.my_music_recyclerview);
    }


    //初始化歌单列表
    public void initdata() {
        if(UserName!=null) {
            musiclistModel = musicControl.QueryMusicList(UserName);
            GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(linearLayoutManager);
            myMusicBottomAdapter = new MyMusicBottomAdapter(musiclistModel, getContext());
            recyclerView.setAdapter(myMusicBottomAdapter);
            myMusicBottomAdapter.setOnItemClickListener(new MyMusicBottomAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    MusicListModel musiclistmode = musiclistModel.get(position);
                    Intent intent1 = new Intent(getActivity().getApplicationContext(), ShowMusicActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("musiclistmode", musiclistmode);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                }
            });
        }
    }

    class MyServiceConn implements ServiceConnection {//用于实现连接服务
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicControl = (MusicListService.MusicServiceIBinder) service;
            initdata();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
