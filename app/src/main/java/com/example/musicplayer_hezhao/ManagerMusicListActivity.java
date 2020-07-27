package com.example.musicplayer_hezhao;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.Service.MusicListService;
import com.example.musicplayer_hezhao.adapter.ManagerMusicListAdapter;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/22 17:28
 */
//管理歌单
//批量进行删除
public class ManagerMusicListActivity extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView textView;
    private List<MusicListModel> musicListModels;
    private MyServiceConn myServiceConn;
    private MusicListService.MusicServiceIBinder musicServiceIBinder;
    private ManagerMusicListAdapter adapter;
    private ImageView imageView;
    private boolean index = true;
    private String UserName;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.deletelistlayout);
        UserName = super.username;
        initview();
    }

    public void initview() {
        imageView = findViewById(R.id.delete);
        recyclerView = findViewById(R.id.recyclerview);
        textView = findViewById(R.id.text_select);
        toolbar = findViewById(R.id.title_toolbar);
        myServiceConn = new MyServiceConn();
        final Intent intent = new Intent(getApplicationContext(), MusicListService.class);
        bindService(intent, myServiceConn, BIND_AUTO_CREATE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                HashMap<Integer, Boolean> array = adapter.getHashMap();
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i)) {
                        musicServiceIBinder.DeleteMusicList(musicListModels.get(i).getMusicListName(),username);
                    }
                }
                int index = 0;
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i)) {
                        musicListModels.remove(index);
                    } else {
                        index++;
                    }
                }
                initrecyclerviw();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //全选
                if (index) {
                    adapter.addall();
                    index = !index;
                } else {
                    adapter.cancelall();
                    index = !index;
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initrecyclerviw() {
        adapter = new ManagerMusicListAdapter(musicListModels, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicServiceIBinder = (MusicListService.MusicServiceIBinder) iBinder;
            musicListModels = musicServiceIBinder.QueryMusicList(username);
            initrecyclerviw();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
