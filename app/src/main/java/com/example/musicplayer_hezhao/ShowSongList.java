package com.example.musicplayer_hezhao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer_hezhao.adapter.ShowSongListAdapter;
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
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/1 17:34
 */
public class ShowSongList extends AppCompatActivity implements NeteaseCloudMusicApiTool.Callback {
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private RecyclerView recyclerView;
    private Info info;
    private List<SongID>song_ID;
    private List<String>song_List;
    private List<MusicInfo>music_Info;
    private Intent intent;
    private ShowSongListAdapter adapter;
    private String ListID;
    private Toolbar toolbar;
    private NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;
    private LinearLayoutManager linearLayoutManager;
    private List<String>Lyriclist=new ArrayList<>();
    private AVLoadingIndicatorView avLoadingIndicatorView;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.showsonglist);
        initView();
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initView() {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        info = (Info) bundle.getSerializable("info");
        ListID=info.getId()+"";
        toolbar=findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        avLoadingIndicatorView=findViewById(R.id.avi);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        recyclerView = findViewById(R.id.recyclerview);
        neteaseCloudMusicApiTool=new NeteaseCloudMusicApiTool();
    }

    public void initData() throws IOException {
        text1.setText(info.getName());
        text2.setText(info.getCreator().getNickname());
        text3.setText(info.getDescription());
        Glide.with(getApplicationContext()).load(info.getCoverImgUrl()).into(image1);
        Glide.with(getApplicationContext()).load(info.getCreator().getAvatarUrl()).into(image2);
        neteaseCloudMusicApiTool.getSongList(ListID,this);

    }

    @Override
    public void doResult1(List<SongID> obj) {

        song_ID =  obj;
        neteaseCloudMusicApiTool.getSong(song_ID, this);


    }

    @Override
    public void doResult2(List<String> obj) {
        song_List = obj;
        neteaseCloudMusicApiTool.getMusicInfo(song_ID,this);
    }

    @Override
    public void doResult3(List<MusicInfo> obj)  {

        music_Info = obj;
        try {
            neteaseCloudMusicApiTool.getmusiclyric(this,song_ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    }

    @Override
    public void doResult12(List<String> lyrclist) {
        Lyriclist=lyrclist;
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter=new ShowSongListAdapter(Lyriclist,song_ID,song_List,music_Info,getApplicationContext());
                linearLayoutManager=new LinearLayoutManager(ShowSongList.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL) ;
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
                avLoadingIndicatorView.setVisibility(View.GONE);
                adapter.setOnItemClickListener(new ShowSongListAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
            }
        });
    }

}
