package com.example.musicplayer_hezhao;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer_hezhao.adapter.DownLoadAdapter;
import com.example.musicplayer_hezhao.fragment.downmusic_downloaded;
import com.example.musicplayer_hezhao.fragment.downmusic_downloading;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 11:20
 */
public class DownLoadMusic extends AppCompatActivity {
    private List<String> download_title=new ArrayList<>();
    private List<Fragment>download_fragment=new ArrayList<>();
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager  viewPager;
    private DownLoadAdapter  downLoadAdapter;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.downloadmusic);
        initview();
        initdata();

    }
    public void initdata(){
        download_title.add("已下载");
        download_title.add("下载中");
        download_fragment.add(new downmusic_downloaded());
        download_fragment.add(new downmusic_downloading());
        downLoadAdapter=new DownLoadAdapter(getSupportFragmentManager(),download_title,download_fragment);
        viewPager.setAdapter(downLoadAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void initview(){
        toolbar=findViewById(R.id.downloadmusic_toolbar);
        tabLayout=findViewById(R.id.downloadmusic_tablelayout);
        viewPager=findViewById(R.id.downloadmusic_viewpager);
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
}
