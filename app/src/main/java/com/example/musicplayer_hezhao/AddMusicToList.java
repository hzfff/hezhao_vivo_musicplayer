package com.example.musicplayer_hezhao;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.musicplayer_hezhao.Service.MusicListService;
import com.example.musicplayer_hezhao.fragment.My_Favorite_Music;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.sql.BatchUpdateException;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/23 18:42
 */
//用于实现将音乐添加到歌单
public class AddMusicToList  extends AppCompatActivity {
    private Toolbar toolbar;
    private Intent intent;
    private Music music;
    private List<MusicListModel> musicListModelList;
    private MusicListService.MusicServiceIBinder musicListService;
    private MyServiceConn myServiceConn;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.addmusictolist);
        initview();
    }
    public void initview(){
        toolbar=findViewById(R.id.title_toolbar);
        intent=getIntent();
        Bundle bundle = intent.getExtras();
        music = (Music) bundle.getSerializable("music");
        myServiceConn=new MyServiceConn();
        Intent intent=new Intent(getApplicationContext(),MusicListService.class);
        bindService(intent,myServiceConn,BIND_AUTO_CREATE);
    }
    class MyServiceConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicListService= (MusicListService.MusicServiceIBinder) iBinder;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
