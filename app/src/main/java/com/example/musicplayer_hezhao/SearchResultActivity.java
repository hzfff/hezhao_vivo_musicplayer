package com.example.musicplayer_hezhao;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.adapter.MusicShowAdapter;
import com.example.musicplayer_hezhao.adapter.SearchMusicAdapter;
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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/6 9:20
 */
public class SearchResultActivity extends AppCompatActivity implements NeteaseCloudMusicApiTool.Callback {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private EditText editText;
    private TextView textView;
    private Intent intent;
    private SearchMusicCallback searchMusicCallback;
    private SearchMusicAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;
    private NeteaseCloudMusicApiTool.Callback callback;
    private List<String> listurl = new ArrayList<>();
    private List<MusicInfo> musicInfo = new ArrayList<>();
    private List<SongID> listnum = new ArrayList<>();
    private int Position=0;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.searchresultlayout);
        initview();
        initdata();
    }

    public void initview() {
        toolbar = findViewById(R.id.search_toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        editText = findViewById(R.id.input_music);
        textView = findViewById(R.id.search_music);
    }

    public void initdata() {
        callback = this;
        neteaseCloudMusicApiTool=new NeteaseCloudMusicApiTool();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        searchMusicCallback = (SearchMusicCallback) bundle.getSerializable("SearchMusicCallback");
        String Song = bundle.getString("SongName");
        editText.setText(Song);
        adapter = new SearchMusicAdapter(searchMusicCallback, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new SearchMusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SongID id = new SongID();
                id.setId(String.valueOf(searchMusicCallback.getResult().getSongs().get(position).getId()));
                listnum.clear();
                listnum.add(id);
                Position=position;
                neteaseCloudMusicApiTool.getSong(listnum, callback);
            }
        });

    }

    @Override
    public void doResult1(List<SongID> obj) {
    }

    @Override
    public void doResult2(List<String> obj) {
        listurl = obj;
        neteaseCloudMusicApiTool.getMusicInfo(listnum, this);
    }

    @Override
    public void doResult3(List<MusicInfo> obj) {
        musicInfo = obj;
        try {
            neteaseCloudMusicApiTool.getmusiclyric(this,listnum);
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

        Intent intent = new Intent(getApplicationContext(), PlayMusicListActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("musicUrl", (Serializable) listurl);
        bundle.putSerializable("musicInfo", (Serializable) musicInfo);
        bundle.putSerializable("Lyriclist", (Serializable) lyrclist);
        bundle.putInt("position",0);
        bundle.putInt("positions",Position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
