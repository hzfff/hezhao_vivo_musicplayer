package com.example.musicplayer_hezhao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer_hezhao.adapter.HistoryMusicAdapter;
import com.example.musicplayer_hezhao.adapter.ShowArtistAdapter;
import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;
import com.example.musicplayer_hezhao.model.SongID;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.model.findsongs;
import com.example.musicplayer_hezhao.model.huayu;
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/7 10:11
 */
public class ShowArtistActivity extends AppCompatActivity implements NeteaseCloudMusicApiTool.Callback {
    private static Toolbar toolbar;
    private static RecyclerView recyclerView;
    private static Intent intent;
    private static huayu HuaYu;
    private static int position;
    private static NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;
    private static findsongs Songs;
    private static LinearLayoutManager linearLayoutManager;
    private static ShowArtistAdapter adapter;
    private static ImageView image1;
    private static ImageView image2;
    private static TextView textView;
    private static Context context;
    private static String id;
    private static List<MusicInfo>music_Info=new ArrayList<>();
    private static List<String> listurl = new ArrayList<>();
    private static NeteaseCloudMusicApiTool.Callback callback;
    private static List<SongID>song_ID=new ArrayList<>();
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.showartistitem);
        initview();
        initdata();
    }

    public void initview() {
        image1 = findViewById(R.id.image);
        image2 = findViewById(R.id.image2);
        textView = findViewById(R.id.text);
        toolbar = findViewById(R.id.title_toolbar);
        recyclerView = findViewById(R.id.recyclerview);
    }

    public void initdata() {
        callback=this;
        context = getApplicationContext();
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        HuaYu = (huayu) bundle.getSerializable("HuaYu");
        textView.setText(HuaYu.getArtists().get(position).getName());
        neteaseCloudMusicApiTool = new NeteaseCloudMusicApiTool();
        neteaseCloudMusicApiTool.findsinger(this, HuaYu.getArtists().get(position).getId());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static Handler handler = new Handler() {//创建消息处理器对象
        //在主线程中处理从子线程发送过来的消息
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    Glide.with(context).load(Songs.getSongs().get(position).getAl().getPicUrl()).into(image1);
                    Glide.with(context).load(Songs.getSongs().get(position).getAl().getPicUrl()).into(image2);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void doResult1(List<SongID> obj) {

    }

    @Override
    public void doResult2(List<String> obj) {
        listurl=obj;
        neteaseCloudMusicApiTool.getMusicInfo(song_ID, this);
    }

    @Override
    public void doResult3(List<MusicInfo> obj) {
        music_Info=obj;
        Intent intent =new Intent(getApplicationContext(),PlayMusicListActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("musicUrl", (Serializable) listurl);
        bundle.putSerializable("musicInfo", (Serializable) music_Info);
        bundle.putInt("position",position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void doResult4(List<String> obj) {

    }

    @Override
    public void doResult5(Data<Info> obj) {

    }

    @Override
    public void doResult6(List<Num> obj) {

    }

    @Override
    public void doResult7(List<VedioInformation> obj) {

    }

    @Override
    public void doResult8(HotMusic obj) {

    }

    @Override
    public void doResult9(SearchMusicCallback searchMusicCallback) {

    }

    @Override
    public void doResult10(huayu huayu) {

    }

    @Override
    public void doResult11(findsongs findsongs) {
        Songs = findsongs;
        adapter = new ShowArtistAdapter(Songs, getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Message message = handler.obtainMessage();
        message.what = 1;
        adapter.setOnItemClickListener(new ShowArtistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                for(int i=0;i<Songs.getSongs().size();i++)
                {
                    id = String.valueOf(Songs.getSongs().get(i).getId());
                    SongID songID=new SongID();
                    songID.setId(id);
                    song_ID.add(songID);
                }
                neteaseCloudMusicApiTool.getSong(song_ID, callback);
            }
        });
        handler.sendMessage(message);
    }
}
