package com.example.musicplayer_hezhao;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.Service.MusicService;
import com.example.musicplayer_hezhao.adapter.LocalMusic_Singer_Adapter;
import com.example.musicplayer_hezhao.adapter.MainAdapter;
import com.example.musicplayer_hezhao.adapter.playmusicadapter;
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
import com.example.musicplayer_hezhao.util.VpRecyView;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
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
    private static List<Music> musicList = new ArrayList<>();
    private static VpRecyView recyclerView;
    private static playmusicadapter adapter;
    private MusicService.MusicServiceIBinder musicservice;
    private MyServiceConnect musicConnect;
    private boolean temp = true;
    private static int position_copy = 0;
    private boolean index_copy = false;
    private static LinearLayoutManager linearLayoutManager;
    private static Context context;

    class MyServiceConnect implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicservice = (MusicService.MusicServiceIBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onCreate(final Bundle bundle) {
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
                } else if (position==1){
                    mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic5));
                }else{
                    mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic3));
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
        final Intent intent = this.getIntent();
        username = intent.getStringExtra("username");
        if (username != null) {
            login_text.setText(username);
        }
        adapter = new playmusicadapter(musicList, getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setOnPagerChageListener(new VpRecyView.onPagerChageListener() {
            @Override
            public void onPagerChage(int position) {
                if (position_copy > position) {
                    if (temp) {
                        ArrayList<Music> list = new ArrayList<>();
                        for (int i = musicList.size() - 1; i >= 0; i--) {
                            list.add(musicList.get(i));
                        }
                        musicservice.addPlayList(list);
                        musicservice.pause();
                        temp = false;
                    }
                    musicservice.playPre();
                    position_copy = position;
                } else if (position_copy < position) {
                    if (temp) {
                        ArrayList<Music> list = new ArrayList<>();
                        for (int i = musicList.size() - 1; i >= 0; i--) {
                            list.add(musicList.get(i));
                        }
                        musicservice.addPlayList(list);
                        temp = false;
                    }
                    position_copy = position;
                    musicservice.playNext();
                    musicservice.pause();
                }
            }
        });
        recyclerView.setOnPagerPosition(0);
        adapter.setOnItemClickListener(new playmusicadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int index) {
                if (index == 1) {
                    Intent intent1 = new Intent(MainActivity.this, PlayMusicActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("MusicList", (Serializable) musicList);
                    bundle1.putInt("position", position);
                    if (index_copy) {
                        bundle1.putInt("index_copy", 1);
                    } else {
                        bundle1.putInt("index_copy", 2);
                    }
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                } else if (index == 2) {
                    if (temp) {
                        ArrayList<Music> list = new ArrayList<>();
                        for (int i = musicList.size() - 1; i >= 0; i--) {
                            list.add(musicList.get(i));
                        }
                        musicservice.addPlayList(list);
                        index_copy = true;
                        temp = false;
                    } else {
                        musicservice.play(username);
                        index_copy = true;
                    }
                } else if (index == 3) {
                    musicservice.pause();
                    index_copy = false;

                } else if (index == 4) {

                }
            }
        });
    }

    public static Handler handler_copy = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            ArrayList<Music>list = (ArrayList<Music>) bundle.getSerializable("MusicList");
            musicList.clear();
            for(int i=0;i<list.size();i++)
            {
                musicList.add(list.get(i));
            }
            LinearLayoutManager manager= (LinearLayoutManager) recyclerView.getLayoutManager();
            adapter= (playmusicadapter) recyclerView.getAdapter();
            adapter.notifyDataSetChanged();
           // int postion = bundle.getInt("position");
            manager.scrollToPositionWithOffset(0, 0);
            manager.setStackFromEnd(true);

        }
    };
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int num = bundle.getInt("MusicNum");
            int postion = recyclerView.getOnPagerPosition();
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            manager.scrollToPositionWithOffset(num , 0);
            manager.setStackFromEnd(true);
        }
    };

    public void initView() {
        context=getApplicationContext();
        musicConnect = new MyServiceConnect();
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, musicConnect, BIND_AUTO_CREATE);
        recyclerView = findViewById(R.id.recyclerview);
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
        MusicUpdateTask task = new MusicUpdateTask();
        task.doInBackground();
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

    class MusicUpdateTask {

        public Void doInBackground(Object... objects) {
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] strs = new String[]{
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.ALBUM_ID,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.DURATION
            };
            String where = MediaStore.Audio.Media.DATA + " like \"%" + "/raw" + "%\"";
            String[] keywords = null;
            String sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
            ContentResolver contentResolver = getContentResolver();
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            Cursor cursor = contentResolver.query(uri, strs, where, keywords, sortOrder);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    Uri musicUri = Uri.withAppendedPath(uri, id);
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    int albumId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ID));
                    Uri albumUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
                    String Artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    Music music = new Music(String.valueOf(musicUri), String.valueOf(albumUri), name, duration, Artist, 0);
                    if (uri != null) {
                        ContentResolver resolver = getContentResolver();
                        music.MusicImage = String.valueOf(albumUri);
                    }
                    musicList.add(music);
                }
                cursor.close();

            }
            return null;
        }

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
                Log.d("hezhao", "MYMUSIC");
                mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic4));
                MusicPlayerViewPager.setCurrentItem(Num_MyMusic, true);
                break;
            case R.id.find_music:
                Log.d("hezhao", "FINDMUSIC");
                MusicPlayerViewPager.setCurrentItem(Num_FindMusic, true);
                mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic5));
                break;
            case R.id.find_vedio:
                Log.d("hezhao", "FINDVEDIO");
                MusicPlayerViewPager.setCurrentItem(Num_FindVedio, true);
                mymusic_head_pic.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.pic6));
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
