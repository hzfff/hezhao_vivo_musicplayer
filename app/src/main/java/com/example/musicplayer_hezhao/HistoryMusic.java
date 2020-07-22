package com.example.musicplayer_hezhao;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer_hezhao.adapter.DownLoadAdapter;
import com.example.musicplayer_hezhao.fragment.Histroy_Music_Fragment;
import com.example.musicplayer_hezhao.fragment.history_vedio_fragment;
import com.example.musicplayer_hezhao.fragment.local_music_fragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 11:21
 */
public class HistoryMusic extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DownLoadAdapter downLoadAdapter;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.historymusic);
        initview();
        initdata();
    }

    public void initview() {
        toolbar = findViewById(R.id.title_toolbar);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setSelectedTabIndicatorHeight(0);
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

    public void initdata() {
        titleList.add("歌曲");
        titleList.add("视频");
        fragmentList.add(new Histroy_Music_Fragment());
        fragmentList.add(new history_vedio_fragment());
        downLoadAdapter=new DownLoadAdapter(getSupportFragmentManager(),titleList,fragmentList);
        viewPager.setAdapter(downLoadAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_layout_text);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(HistoryMusic.this, R.style.TabLayoutTextSize);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_layout_text);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(HistoryMusic.this, R.style.TabLayoutTextSize_two);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}
