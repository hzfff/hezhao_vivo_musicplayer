package com.example.musicplayer_hezhao;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.Service.MusicService;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.tool.CircleImageView;
import com.example.musicplayer_hezhao.tool.LrcView;
import com.example.musicplayer_hezhao.util.MyTextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.internal.Utils;

/**
 * Created by 11120555 on 2020/7/17 15:38
 */
public class PlayMusicActivity extends BaseActivity implements View.OnClickListener {
    private SeekBar seekBar;
    private LrcView lrcView;
    private ImageView start_button;
    private ImageView pre_button;
    private ImageView next_button;
    private ImageView background_pic;
    private CircleImageView profile_pic;
    private TextView begin_time;
    private TextView end_time;
    private TextView singer_name;
    private MyTextView music_title;
    private ImageView share_img;
    private Toolbar toolbar;
    private MusicService.MusicServiceIBinder mMusicService;
    private ObjectAnimator animator;
    private Intent intent1, intent2;
    private boolean isUnbind = false;
    private boolean isStart = true;
    private long currentPlayTime = 0;//记录动画旋转时间和状态
    MyServiceConn myServiceConn;
    private ImageView image1;
    private ImageView image3;
    //表示image的三种状态
    private int state = 1;
    private static List<Music> musicList = new ArrayList<>();
    private boolean index = true;
    private boolean indexs = true;
    private boolean indexss = true;
    private boolean temp = false;
    private Intent intent;
    private int position;
    private  int index_temp=0;
    private int index_copy = 0;
    private int num = 0;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.play_music_main_pager);
        intent1 = getIntent();
        initView();
    }

    public void initView() {
        intent = getIntent();
        final Bundle bundle = intent.getExtras();
        musicList = (List<Music>) bundle.getSerializable("MusicList");
        position = intent.getIntExtra("position", 0);
        index_temp=position;
        index_copy = intent.getIntExtra("index_copy", 2);
        Message msg = MainActivity.handler_copy.obtainMessage();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("MusicList", (Serializable) musicList);
        bundle1.putInt("position", position);
        msg.setData(bundle1);
        MainActivity.   handler_copy.sendMessage(msg);
        music_title = findViewById(R.id.text1);
        singer_name = findViewById(R.id.text2);
        image1 = findViewById(R.id.img1);
        image3 = findViewById(R.id.img3);
        share_img = findViewById(R.id.share);
        toolbar = findViewById(R.id.toolbar);
        seekBar = findViewById(R.id.seek_music);
        lrcView = findViewById(R.id.lyric);
        start_button = findViewById(R.id.play_btn);
        pre_button = findViewById(R.id.pre_btn);
        next_button = findViewById(R.id.next_btn);
        background_pic = findViewById(R.id.play_music_background);
        profile_pic = findViewById(R.id.image);
        begin_time = findViewById(R.id.played_time);
        end_time = findViewById(R.id.duration_time);
        intent2 = new Intent(this, MusicService.class);
        myServiceConn = new MyServiceConn();
        bindService(intent2, myServiceConn, BIND_AUTO_CREATE);
        pre_button.setOnClickListener(this);
        next_button.setOnClickListener(this);
        start_button.setOnClickListener(this);
        lrcView.setOnClickListener(this);
        share_img.setOnClickListener(this);
        image1.setOnClickListener(this);
        image3.setOnClickListener(this);
        //TODO 后期将从网络传入图片
        if (index_copy == 1) {
            start_button.setBackgroundResource(R.mipmap.play);
            isStart = false;
        }
        animator = ObjectAnimator.ofFloat(profile_pic, "rotation", 0f, 360.0f);//设置图片动画
        animator.setDuration(10000);//动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);
        seekBar.getThumb().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//设置滑块颜色、样式
        seekBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//设置进度条颜色、样式
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        background_pic.setImageBitmap(Util.CreateBitmap(getContentResolver(), Uri.parse(musicList.get(position).getAlbumUri())));
        profile_pic.setImageBitmap(Util.CreateBitmap(getContentResolver(), Uri.parse(musicList.get(position).getAlbumUri())));
        String times = Util.ConverSecondsToTime(musicList.get(position).Duration);
        end_time.setText(times);
        singer_name.setText(musicList.get(position).getArtist());
        music_title.setText(musicList.get(position).getName());
        System.out.println(musicList.get(position).getName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == seekBar.getMax()) {
                    animator.pause();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mMusicService.seekTO(progress);
            }
        });
    }

    public void play() {
        if (indexs && index && indexss && position != mMusicService.getcurrentmusic()) {
            mMusicService.addPlayList(musicList, position);
            indexs = false;
        }
        if (isStart) {
            mMusicService.play(super.username);
            start_button.setBackground(getDrawable(R.mipmap.play));
            animator.start();
            animator.setCurrentPlayTime(currentPlayTime);
            isStart = false;
        } else {
            mMusicService.pause();
            start_button.setBackground(getDrawable(R.mipmap.stop));
            currentPlayTime = animator.getCurrentPlayTime();
            animator.pause();
            isStart = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
                break;
            case R.id.next_btn:
                if (indexs && index && indexss) {
                    mMusicService.addPlayList(musicList, position);
                    indexs = false;
                }
                start_button.setBackground(getDrawable(R.mipmap.play));
                animator.start();
                animator.setCurrentPlayTime(currentPlayTime);
                isStart = false;
                temp = true;
                mMusicService.playNext();
                num += 1;
                break;
            case R.id.play_btn:

                if (index && indexs && indexss) {
                    mMusicService.addPlayList(musicList, position);
                    index = false;
                }
                if (isStart) {
                    mMusicService.play(super.username);
                    start_button.setBackground(getDrawable(R.mipmap.play));
                    animator.start();
                    animator.setCurrentPlayTime(currentPlayTime);
                    isStart = false;
                } else {
                    mMusicService.pause();
                    start_button.setBackground(getDrawable(R.mipmap.stop));
                    currentPlayTime = animator.getCurrentPlayTime();
                    animator.pause();
                    isStart = true;
                }

                break;
            case R.id.pre_btn:
                if (indexss && indexs && index) {
                    mMusicService.addPlayList(musicList, position);
                    indexs = false;
                }
                start_button.setBackground(getDrawable(R.mipmap.play));
                animator.start();
                animator.setCurrentPlayTime(currentPlayTime);
                isStart = false;
                temp = true;
                mMusicService.playPre();
                num -= 1;
                break;
            case R.id.img1:
                if (state == 1) {
                    state = 2;
                    image1.setImageResource(R.mipmap.playmusicpic7);
                } else if (state == 2) {
                    state = 3;
                    image1.setImageResource(R.mipmap.playmusicpic8);
                } else {
                    state = 1;
                    image1.setImageResource(R.mipmap.playmusicpic6);
                }
                break;
            default:
                break;
        }
    }

    public void addTimer() {

        Message msg = MainActivity.handler.obtainMessage();
        Bundle bundle = new Bundle();
        int nums=num+index_temp;
        bundle.putInt("MusicNum", num+index_temp);
        msg.setData(bundle);
        MainActivity.handler.sendMessage(msg);

    }


    class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMusicService = (MusicService.MusicServiceIBinder) iBinder;
            mMusicService.registerOnStateChangeListener(mStateChangeListener);
            play();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    private MusicService.OnStateChangeListener mStateChangeListener = new MusicService.OnStateChangeListener() {
        @Override
        public void onPlayProgressChange(Music music) {
            updatePlayingInfo(music);
        }

        @Override
        public void onPlay(Music music) {
            updatePlayingInfo(music);
            enableControlPanel(true);
        }

        @Override
        public void onPause(Music music) {
            enableControlPanel(true);
        }
    };

    private void updatePlayingInfo(Music music) {
        String times = Util.ConverSecondsToTime(music.Duration);
        end_time.setText(times);
        times = Util.ConverSecondsToTime(music.PlayedTime);
        begin_time.setText(times);
        seekBar.setMax((int) music.Duration);
        seekBar.setProgress((int) music.PlayedTime);
        if (temp) {
            temp = false;
            music_title.setText(music.Name);
            singer_name.setText(music.Artist);
            profile_pic.setImageBitmap(Util.CreateBitmap(getContentResolver(), Uri.parse(music.AlbumUri)));
            background_pic.setImageBitmap(Util.CreateBitmap(getContentResolver(), Uri.parse(music.AlbumUri)));
        }
    }

    private void enableControlPanel(boolean enabled) {
        start_button.setEnabled(enabled);
        next_button.setEnabled(enabled);
        pre_button.setEnabled(enabled);
        seekBar.setEnabled(enabled);
    }


    private void showPlayList() {

    }

    private AdapterView.OnItemClickListener mOnMusicItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (mMusicService != null) {
                mMusicService.addPlayList(musicList.get(position));
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //TODO
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        addTimer();
    }
}
