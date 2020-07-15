package com.example.musicplayer_hezhao;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 11:21
 */
public class HistoryMusic  extends AppCompatActivity {
    private List<Fragment>  fragmentList;
    private List<String>    titleList;
    private Toolbar    toolbar;
    private ViewPager viewPager;
    private TabLayout  tabLayout;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.historymusic);
        initview();
        initdata();
    }
    public void initview(){
        
    }
    public void initdata(){

    }
}
