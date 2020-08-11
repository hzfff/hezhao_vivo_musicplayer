package com.example.musicplayer_hezhao;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.musicplayer_hezhao.fragment.HanGuoFragment;
import com.example.musicplayer_hezhao.fragment.HuaYuFragment;
import com.example.musicplayer_hezhao.fragment.OuMeiFragment;
import com.example.musicplayer_hezhao.util.customViewpagerView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/6 15:22
 */
public class ShowSingerActivity extends AppCompatActivity {
    private customViewpagerView viewPager;
    private TabLayout tabLayout;
    private List<Fragment> list;
    private MyAdapter myAdapter;
    private String[] title = {"华语", "欧美", "日韩"};
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.showsingerlayout);
        initview();
        initdata();
    }
    public void initview(){
        viewPager = findViewById(R.id.viewpagers);
        tabLayout = findViewById(R.id.tablayouts);
        toolbar=findViewById(R.id.title_toolbar);
        tabLayout.setSelectedTabIndicatorHeight(0);
    }
    public void initdata(){
        list = new ArrayList<>();
        list.add(new HuaYuFragment());
        list.add(new OuMeiFragment());
        list.add(new HanGuoFragment());
        myAdapter = new MyAdapter(getSupportFragmentManager(), title, list);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
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


    public class MyAdapter extends FragmentPagerAdapter {
        private String[] tablayout_title;
        private List<Fragment>list_fragment;
        private FragmentManager fragmentManager;
        public MyAdapter(@NonNull FragmentManager fm,String[]title,List<Fragment>list) {
            super(fm);
            tablayout_title=title;
            list_fragment=list;
            fragmentManager=fm;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
