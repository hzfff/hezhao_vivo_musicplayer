package com.example.musicplayer_hezhao;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer_hezhao.fragment.FindVedioFragment;
import com.example.musicplayer_hezhao.fragment.OuMeiFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/20 15:36
 */
public class ShowVedioActivity extends BaseActivity {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.showvediolayout);
        initview();
        initdata();
    }

    public void initview() {
        tableLayout = findViewById(R.id.tl_tabs);
        tableLayout.setSelectedTabIndicatorHeight(0);
        viewPager = findViewById(R.id.vp_content);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("在线视频");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitleTextAppearance(getApplicationContext(), R.style.MyEditText);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initdata() {
        titles.add("华语");
        titles.add("欧美");
        fragments.add(new FindVedioFragment());
        fragments.add(new OuMeiFragment());

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return titles.get(position);
            }
        });

        tableLayout.setupWithViewPager(viewPager);
    }
}
