package com.example.musicplayer_hezhao;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer_hezhao.adapter.DownLoadAdapter;
import com.example.musicplayer_hezhao.fragment.local_music_collect;
import com.example.musicplayer_hezhao.fragment.local_music_fragment;
import com.example.musicplayer_hezhao.fragment.local_singer_fragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 11:20
 */
public class LocalMusic extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar   toolbar;
    private List<String> list_title=new ArrayList<>();
    private List<Fragment>fragmentList=new ArrayList<>();
    private DownLoadAdapter downLoadAdapter;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.localmusic);
        initview();
        initdata();
    }
    public void initview(){
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tablayout);
        toolbar=findViewById(R.id.title_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        tabLayout.setSelectedTabIndicatorHeight(0);
    }
    public void initdata(){
        list_title.add("单曲");
        list_title.add("歌手");
        fragmentList.add(new local_music_fragment());
        fragmentList.add(new local_singer_fragment());
        downLoadAdapter=new DownLoadAdapter(getSupportFragmentManager(),list_title,fragmentList);
        viewPager.setAdapter(downLoadAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_layout_text);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(LocalMusic.this, R.style.TabLayoutTextSize);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_layout_text);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(LocalMusic.this, R.style.TabLayoutTextSize_two);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
