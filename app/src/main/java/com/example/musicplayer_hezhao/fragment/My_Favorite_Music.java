package com.example.musicplayer_hezhao.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.ContentProvider.PlayListProvider;
import com.example.musicplayer_hezhao.DB.DBHelper;
import com.example.musicplayer_hezhao.PlayMusicActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Service.MusicService;
import com.example.musicplayer_hezhao.Service.MyFavoriteMusic_Service;
import com.example.musicplayer_hezhao.adapter.MusicShowAdapter;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by 11120555 on 2020/7/15 15:07
 */
public class My_Favorite_Music extends AppCompatActivity {
    private Toolbar toolbar;
    private static RecyclerView recyclerView;
    private static MusicShowAdapter adapter;
    private static List<Music> musicList = new ArrayList<>();
    private MyFavoriteMusic_Service.MusicServiceIBinder musicControl;
    private MyServiceConn myServiceConn;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_favorite_music);
        initview();
    }

    public void initview() {
        MusicUpdateTask task = new MusicUpdateTask();
        task.execute();
        final Intent intent = new Intent(this, MyFavoriteMusic_Service.class);
        myServiceConn = new MyServiceConn();
        bindService(intent, myServiceConn, BIND_AUTO_CREATE);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MusicShowAdapter(musicList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MusicShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int index, View view, int position) {
                ShowDialog bottomDialogFr = new ShowDialog();
                bottomDialogFr.show(getSupportFragmentManager(), "DF");
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new MusicShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int index, View view, int position) {
                //点击右侧图片跳出底部选项歌曲详细信息，删除或者添加到喜欢的音乐选项
                if (index == 2) {
                    ShowDialog bottomDialogFr = new ShowDialog(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MusicList", (Serializable) musicList.get(position));
                    bottomDialogFr.setArguments(bundle);
                    bottomDialogFr.show(getSupportFragmentManager(), "hezhao");

                } else if (index == 1) {
                    //点击歌名跳转到播放页面
                    Intent intent = new Intent(getApplicationContext(), PlayMusicActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MusicList", (Serializable) musicList);
                    intent.putExtra("position", position);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    MusicShowAdapter adapter = (MusicShowAdapter) recyclerView.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public static void CanCel(int position) {
        musicList.remove(position);
        MusicShowAdapter adapter = (MusicShowAdapter) recyclerView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private class MusicUpdateTask extends AsyncTask<Object, Music, Void> {

        @Override
        protected Void doInBackground(Object... objects) {
            musicList.clear();
            Cursor cursor = getContentResolver().query(
                    PlayListProvider.CONTENT_URI_SONG_SECOND,
                    null,
                    null,
                    null,
                    null);
            while (cursor.moveToNext()) {
                String songUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SONG_URI));
                String albumUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ALBUM_URI));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
                long playedtime = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.LAST_PLAY_TIME));
                long duration = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.DURATION));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ARTIST));
                Music music = new Music(songUri, albumUri, name, duration, playedtime,artist);
                musicList.add(music);
            }
            cursor.close();
            publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(Music... musiclists) {
            MusicShowAdapter adapter = (MusicShowAdapter) recyclerView.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    class MyServiceConn implements ServiceConnection {//用于实现连接服务

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicControl = (MyFavoriteMusic_Service.MusicServiceIBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void Insert(Music music) {
        musicControl.add(music);
    }

}
