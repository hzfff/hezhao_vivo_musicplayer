package com.example.musicplayer_hezhao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.adapter.MainAdapter;
import com.example.musicplayer_hezhao.fragment.FindMusicFragment;
import com.example.musicplayer_hezhao.fragment.FindVedioFragment;
import com.example.musicplayer_hezhao.fragment.MyMusicFragment;
import com.example.musicplayer_hezhao.menu_work.change_background_activity;
import com.example.musicplayer_hezhao.menu_work.nav_developer_msg_activity;
import com.example.musicplayer_hezhao.menu_work.nav_listening_activity;
import com.example.musicplayer_hezhao.menu_work.nav_msg_activity;
import com.example.musicplayer_hezhao.menu_work.nav_order_activity;
import com.example.musicplayer_hezhao.menu_work.nav_price_activity;
import com.example.musicplayer_hezhao.menu_work.nav_show_activity;
import com.example.musicplayer_hezhao.menu_work.nav_stop_activity;
import com.example.musicplayer_hezhao.menu_work.nav_store_activity;
import com.example.musicplayer_hezhao.model.Music;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 11120555 on 2020/7/7 14:53
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //将我的音乐、发现音乐、发现视频三个fragment传入到list中；
    private final String TAG = "HeZhao";
    private List<Fragment> FragmentList;
    private List<String> titleList;
    //三个所对应的Relativelayout；
    private RelativeLayout MyMusic;
    private RelativeLayout FindMusic;
    private RelativeLayout FindVedio;
    //判断是否选中的RelativeLayout
    private RelativeLayout SelectUrl;
    //图片，分别对应右上角的图标；
    private ImageView SerchMusic;
    private ViewPager MusicPlayerViewPager;
    private MainAdapter MainAdapter;
    private final int Num_MyMusic = 0;
    private final int Num_FindMusic = 1;
    private final int Num_FindVedio = 2;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar toolbar;
    private ImageView backgroundpic;
    private ImageView mymusic_head_pic;
    private TextView play_music_text;
    private TextView login_textview;
    private TextView login_text;
    private TextView mymusic_text;
    private TextView find_music;
    private TextView find_vedio;
    private NavigationView navigationView;
    private String username = null;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        initData();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        SerchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        MusicPlayerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic4));
                } else {
                    mymusic_head_pic.setImageDrawable(null);
                }
                mymusic_text.setTextSize(16);
                find_music.setTextSize(16);
                find_vedio.setTextSize(16);
                switch (position) {
                    case 0:
                        mymusic_text.setTextSize(20);
                        break;
                    case 1:
                        find_music.setTextSize(20);
                        break;
                    case 2:
                        find_vedio.setTextSize(20);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");
        if (username != null) {
            login_text.setText(username);
        }
    }

    public void initView() {
        mymusic_text = findViewById(R.id.mymusic_text);
        mymusic_text.setTextSize(20);
        find_music = findViewById(R.id.find_music);
        find_vedio = findViewById(R.id.find_vedio);
        navigationView = findViewById(R.id.mNavigationView);
        View view = navigationView.getHeaderView(0);
        login_textview = view.findViewById(R.id.login_information);
        play_music_text = findViewById(R.id.play_music_text);
        login_text = view.findViewById(R.id.login_information);
        mymusic_head_pic = findViewById(R.id.mymusic_head_pic);
        backgroundpic = findViewById(R.id.background_picture);
        mDrawerLayout = findViewById(R.id.mdrawerLayout);

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
        mNavigationView = findViewById(R.id.mNavigationView);
        toolbar = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);
        //setDisplayHomeAsUpEnabled设置为true以后，会显示toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //这里将toolbar的默认显示箭更改为三横线
        getSupportActionBar().setHomeAsUpIndicator(new DrawerArrowDrawable(getApplicationContext()));
        SerchMusic = findViewById(R.id.SearchMusic);
        MyMusic = findViewById(R.id.MyMusic);
        FindMusic = findViewById(R.id.FindMusic);
        FindVedio = findViewById(R.id.FindVedio);
        MusicPlayerViewPager = findViewById(R.id.music_main_viewpager);
        MyMusic.setOnClickListener(this);
        FindMusic.setOnClickListener(this);
        FindVedio.setOnClickListener(this);
        mymusic_text.setOnClickListener(this);
        find_music.setOnClickListener(this);
        find_vedio.setOnClickListener(this);
        login_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginMainActivity.class);
                startActivity(intent);
            }
        });
        //给navigationView设置监听事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_msg:
                        Intent intent1 = new Intent(MainActivity.this, nav_msg_activity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_listening:
                        Intent intent2 = new Intent(MainActivity.this, nav_listening_activity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_stop:
                        Intent intent3 = new Intent(MainActivity.this, nav_stop_activity.class);
                        startActivity(intent3);
                        break;
                    case R.id.change_background:
                        Intent intent4 = new Intent(MainActivity.this, change_background_activity.class);
                        startActivity(intent4);
                        break;
                    case R.id.nav_developer_msg:
                        Intent intent5 = new Intent(MainActivity.this, nav_developer_msg_activity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_store:
                        Intent intent6 = new Intent(MainActivity.this, nav_store_activity.class);
                        startActivity(intent6);
                        break;
                    case R.id.nav_order:
                        Intent intent7 = new Intent(MainActivity.this, nav_order_activity.class);
                        startActivity(intent7);
                        break;
                    case R.id.nav_show:
                        Intent intent8 = new Intent(MainActivity.this, nav_show_activity.class);
                        startActivity(intent8);
                        break;
                    case R.id.nav_price:
                        Intent intent9 = new Intent(MainActivity.this, nav_price_activity.class);
                        startActivity(intent9);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void initData() {
        mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic4));
        if (titleList == null || titleList.size() == 0) {
            inittitleList();
        }
        if (FragmentList == null || FragmentList.size() == 0) {
            initFragment();
        }
        Glide.with(this).load(R.mipmap.pic5).
                apply(RequestOptions.
                        bitmapTransform(new BlurTransformation(18, 3))).into(backgroundpic);
        MainAdapter = new MainAdapter(getApplicationContext(), getSupportFragmentManager(), FragmentList, titleList);
        MusicPlayerViewPager.setAdapter(MainAdapter);
        MusicPlayerViewPager.setOffscreenPageLimit(0);
    }

    public void inittitleList() {
        titleList = new ArrayList<>(3);
        titleList.add("我的");
        titleList.add("发现");
        titleList.add("视频");
    }

    public void initFragment() {
        FragmentList = new ArrayList<>(3);
        FragmentList.add(new MyMusicFragment());
        FragmentList.add(new FindMusicFragment());
        FragmentList.add(new FindVedioFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mymusic_text:
                Log.d("hezhao", "MYMuSIC");
                mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic4));
                MusicPlayerViewPager.setCurrentItem(Num_MyMusic, true);
                break;
            case R.id.find_music:
                Log.d("hezhao", "FINDMUSIC");
                MusicPlayerViewPager.setCurrentItem(Num_FindMusic, true);
                mymusic_head_pic.setImageDrawable(null);
                break;
            case R.id.find_vedio:
                Log.d("hezhao", "FINDVEDIO");
                MusicPlayerViewPager.setCurrentItem(Num_FindVedio, true);
                mymusic_head_pic.setImageDrawable(null);
                break;
            default:
                break;
        }
    }

    public void findTable(int viewId) {
        if (SelectUrl != null) {
            SelectUrl.setSelected(false);
        }
        switch (viewId) {
            case R.id.MyMusic:
                if (SelectUrl != null && SelectUrl == MyMusic) {
                    SelectUrl.setSelected(true);
                    //TODO 刷新当前界面操作  performRefresh()
                } else {
                    MyMusic.setSelected(true);
                    SelectUrl = MyMusic;
                    MusicPlayerViewPager.setCurrentItem(Num_MyMusic, false);
                }
                break;
            case R.id.FindMusic:
                if (SelectUrl != null && SelectUrl == FindMusic) {
                    SelectUrl.setSelected(true);
                    //TODO 刷新操作  performRefresh()
                } else {
                    FindMusic.setSelected(true);
                    SelectUrl = FindMusic;
                    MusicPlayerViewPager.setCurrentItem(Num_FindMusic, false);
                }
                break;
            case R.id.FindVedio:
                if (SelectUrl != null && SelectUrl == FindVedio) {
                    SelectUrl.setSelected(true);
                    //TODO  刷新操作  performRefresh()
                } else {
                    FindVedio.setSelected(true);
                    SelectUrl = FindVedio;
                    MusicPlayerViewPager.setCurrentItem(Num_FindVedio, false);
                }
                break;
            default:
                break;
        }
    }

    //开始处理后台任务
    //新建一个AsyncTask
    //TODO


}
