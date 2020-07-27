package com.example.musicplayer_hezhao;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.Service.MusicListService;
import com.example.musicplayer_hezhao.adapter.AddMusicToListAdapter;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;
import com.example.musicplayer_hezhao.fragment.My_Favorite_Music;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.model.MusicListModel;
import com.example.musicplayer_hezhao.util.AddMusicToListDialog;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/23 18:42
 */
//用于实现将音乐添加到歌单
public class AddMusicToList extends BaseActivity {
    private Toolbar toolbar;
    private Intent intent;
    private Music music;
    private MusicListService.MusicServiceIBinder musicListService;
    private MyServiceConn myServiceConn;
    private static List<MusicListModel> musiclistModel = new ArrayList<>();
    private static RecyclerView recyclerView;
    private AddMusicToListAdapter adapter;
    private final String TAG = "HeZhao";
    private String UserName;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.addmusictolist);
        UserName=super.username;
        initview();
    }

    public void initview() {
        toolbar = findViewById(R.id.title_toolbar);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        music = (Music) bundle.getSerializable("music");
        myServiceConn = new MyServiceConn();
        Intent intent = new Intent(getApplicationContext(), MusicListService.class);
        bindService(intent, myServiceConn, BIND_AUTO_CREATE);
        setSupportActionBar(toolbar);
        //setDisplayHomeAsUpEnabled设置为true以后，会显示toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static void notifyChangeAdd(int position,Music music) {
        musiclistModel.get(position).getMusicName().add(music);
        AddMusicToListAdapter  addMusicToListAdapter= (AddMusicToListAdapter) recyclerView.getAdapter();
        addMusicToListAdapter.notifyDataSetChanged();


    }
    public static void notifyChangeDelete(int position,Music music) {
        musiclistModel.get(position).getMusicName().remove(music);
        AddMusicToListAdapter  addMusicToListAdapter= (AddMusicToListAdapter) recyclerView.getAdapter();
        addMusicToListAdapter.notifyDataSetChanged();
    }
    class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicListService = (MusicListService.MusicServiceIBinder) iBinder;
            if(UserName!=null) {
                initdata(UserName);
            }
            initrecyclerview();
        }

        public void initdata(String UserName) {
            musiclistModel = musicListService.QueryMusicList( UserName);
        }

        public void initrecyclerview() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapter = new AddMusicToListAdapter(music, musiclistModel, getApplicationContext());
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new AddMusicToListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, int index) {
                    if (index == 0 || index == 2) {
                        MusicListModel musiclistmode = musiclistModel.get(position);
                        Intent intent1 = new Intent(getApplicationContext(), ShowMusicActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable("musiclistmode", musiclistmode);
                        intent1.putExtras(bundle1);
                        startActivity(intent1);
                    } else {
                        AddMusicToListDialog dialog = new AddMusicToListDialog(music, musiclistModel, position);
                        dialog.show(getSupportFragmentManager(), TAG);
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
