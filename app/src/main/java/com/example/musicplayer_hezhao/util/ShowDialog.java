package com.example.musicplayer_hezhao.util;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.musicplayer_hezhao.AddMusicToList;
import com.example.musicplayer_hezhao.PlayMusicActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Service.MyFavoriteMusic_Service;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.fragment.My_Favorite_Music;
import com.example.musicplayer_hezhao.model.Music;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 16:52
 */

public class ShowDialog extends DialogFragment {
    private View view;
    private Window window;
    private RoundImageView imageView;
    private TextView text1;
    private TextView text2;
    private TextView collect;
    private TextView delete;
    private TextView share;
    private TextView addlist;
    private Music music;
    private Bundle bundle;
    private MyFavoriteMusic_Service.MusicServiceIBinder musicControl;
    private MyServiceConn myServiceConn;
    private int position=0;

    public ShowDialog() {
    }

    public ShowDialog(int position) {
        this.position = position;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.musicshowdialog, null);
        initview();
        return view;
    }

    public void initview() {
        Intent intent = new Intent(getActivity(), MyFavoriteMusic_Service.class);
        myServiceConn = new MyServiceConn();
        getActivity().bindService(intent, myServiceConn, Context.BIND_AUTO_CREATE);
        bundle = getArguments();
        music = (Music) bundle.getSerializable("MusicList");
        collect = view.findViewById(R.id.test2);
        delete = view.findViewById(R.id.test4);
        share = view.findViewById(R.id.test3);
        addlist=view.findViewById(R.id.test5);
        imageView = view.findViewById(R.id.test_img);
        text1 = view.findViewById(R.id.test_song);
        text2 = view.findViewById(R.id.test_name);
        imageView.setImageBitmap(Util.CreateBitmap(getActivity().getContentResolver(), Uri.parse(music.getAlbumUri())));
        text1.setText(music.getName());
        text2.setText(music.getArtist());
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (collect.getText().equals("收藏")) {
                    musicControl.add(music);
                    collect.setText("已收藏");
                    Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    musicControl.delete(music);
                    collect.setText("收藏");
                    Toast.makeText(getContext(), "已取消收藏", Toast.LENGTH_SHORT).show();
                 //   My_Favorite_Music.CanCel(position);
                }
                getDialog().dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
        //将音乐添加到歌单中
        addlist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddMusicToList.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("music", (Serializable) music);
                intent.putExtras(bundle);
                startActivity(intent);
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        window = getDialog().getWindow();
        // 消除弹框与四边的距离
        window.setBackgroundDrawableResource(android.R.color.transparent);
        // 设置动画
        window.setWindowAnimations(R.style.bottomDialog);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 设置宽度
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);
    }


    class MyServiceConn implements ServiceConnection {//用于实现连接服务

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicControl = (MyFavoriteMusic_Service.MusicServiceIBinder) service;
            int id = musicControl.query(music);
            if (id > 0) {
                collect.setText("已收藏");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
