package com.example.musicplayer_hezhao;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.adapter.HistoryMusicAdapter;
import com.example.musicplayer_hezhao.adapter.HotMusicAdapter;
import com.example.musicplayer_hezhao.model.HistoryModel;
import com.example.musicplayer_hezhao.model.HotMusicModel;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Created by 11120555 on 2020/7/8 14:15
 */
public class SearchActivity extends AppCompatActivity {
    private EditText meditText;
    private Toolbar mtoolbar;
    private ImageView mimageView;
    private List<HistoryModel> historyModelList=new ArrayList<>();
    private List<HotMusicModel>hotMusicModelList=new ArrayList<>();
    private RecyclerView  historyrecyclerview;
    private RecyclerView  hotMusicrecyclerview;
    private HistoryMusicAdapter historyMusicAdapter;
    private HotMusicAdapter hotMusicAdapter;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.search_music);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initview();
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Glide.with(this).load(R.mipmap.pic5).
                apply(RequestOptions.
                        bitmapTransform(new BlurTransformation(18, 3))).into(mimageView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        historyrecyclerview.setLayoutManager(linearLayoutManager);
        historyMusicAdapter=new HistoryMusicAdapter(historyModelList);
        historyrecyclerview.setAdapter(historyMusicAdapter);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        hotMusicrecyclerview.setLayoutManager(linearLayoutManager1);
        hotMusicAdapter=new HotMusicAdapter(hotMusicModelList);
        hotMusicrecyclerview.setAdapter(hotMusicAdapter);
    }
    public void initview() {
        mimageView=findViewById(R.id.background_pic);
        mtoolbar = findViewById(R.id.search_toolbar);
        meditText = findViewById(R.id.input_music);
        historyrecyclerview=findViewById(R.id.history_search);
        hotMusicrecyclerview=findViewById(R.id.hot_music);
        setSupportActionBar(mtoolbar);
        //setDisplayHomeAsUpEnabled设置为true以后，会显示toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        for(int i=0;i<5; i++){
            HistoryModel model=new HistoryModel("心如止水");
            HistoryModel mode2=new HistoryModel("我这一生");
            HistoryModel mode3=new HistoryModel("一路向北");
            HistoryModel mode4=new HistoryModel("后来遇见她");
            historyModelList.add(model);
            historyModelList.add(mode2);
            historyModelList.add(mode3);
            historyModelList.add(mode4);
        }

        for(int i=0;i<5;i++)
        {
            HotMusicModel model=new HotMusicModel("1","心如止水","233456","这可能是你单曲循环最多的歌曲");
            HotMusicModel mode2=new HotMusicModel("2","高考加油","343356","唱遍影视剧插曲的天籁歌手");
            HotMusicModel mode3=new HotMusicModel("3","海底","903456","还是希望你好，虽然与我无关");
            HotMusicModel mode4=new HotMusicModel("4","与我无关","734456","加油你们是最棒的");
            HotMusicModel mode5=new HotMusicModel("5","下落不明","912356","张杰带你穿越人海");
            hotMusicModelList.add(model);
            hotMusicModelList.add(mode2);
            hotMusicModelList.add(mode3);
            hotMusicModelList.add(mode4);
            hotMusicModelList.add(mode5);

        }
    }
}
