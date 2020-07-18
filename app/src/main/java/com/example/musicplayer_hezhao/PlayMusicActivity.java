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
import android.os.IBinder;
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

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 11120555 on 2020/7/17 15:38
 */
public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener {
    private SeekBar seekBar;
    private LrcView lrcView;
    private ImageView start_button;
    private ImageView pre_button;
    private ImageView next_button;
    private ImageView background_pic;
    private CircleImageView profile_pic;
    private TextView begin_time;
    private TextView end_time;
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
    private List<Music> musicList = new ArrayList<>();
    private MusicUpdateTask musicUpdateTask;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.play_music_main_pager);
        intent1 = getIntent();
        initView();
    }

    public void initView() {
        music_title = findViewById(R.id.text1);
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
        animator = ObjectAnimator.ofFloat(profile_pic, "rotation", 0f, 360.0f);//设置图片动画
        animator.setDuration(10000);//动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);
        musicUpdateTask = new MusicUpdateTask();
        musicUpdateTask.execute();
        seekBar.getThumb().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//设置滑块颜色、样式
        seekBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//设置进度条颜色、样式
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Glide.with(this).load(R.mipmap.pic17).
                apply(RequestOptions.
                        bitmapTransform(new BlurTransformation(18, 6))).into(background_pic);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
                break;
            case R.id.next_btn:
                break;
            case R.id.play_btn:
                if (isStart) {
                    mMusicService.play();
                    start_button.setBackground(getDrawable(R.mipmap.play));
                    animator.start();
                    animator.setCurrentPlayTime(currentPlayTime);
                    isStart = false;
                } else {
                    start_button.setBackground(getDrawable(R.mipmap.stop));
                    currentPlayTime = animator.getCurrentPlayTime();
                    animator.pause();
                    isStart = true;
                }
                System.out.println(musicList.size());
                mMusicService.addPlayList(musicList.get(0));
                break;
            case R.id.pre_btn:
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

    class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMusicService = (MusicService.MusicServiceIBinder) iBinder;
            mMusicService.registerOnStateChangeListener(mStateChangeListener);
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
        music_title.setText(music.Name);
    }

    private void enableControlPanel(boolean enabled) {
        start_button.setEnabled(enabled);
        next_button.setEnabled(enabled);
        pre_button.setEnabled(enabled);
        seekBar.setEnabled(enabled);
    }

    private class MusicUpdateTask extends AsyncTask<Object, Music, Void> {

        List<Music> list = new ArrayList<>();

        @Override
        protected Void doInBackground(Object... objects) {
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] strs = new String[]{
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.ALBUM_ID,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.DURATION
            };
            String where = MediaStore.Audio.Media.DATA + " like \"%" + "/raw" + "%\"";
            String[] keywords = null;
            String sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
            ContentResolver contentResolver = getContentResolver();
            ActivityCompat.requestPermissions(PlayMusicActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            Cursor cursor = contentResolver.query(uri, strs, where, keywords, sortOrder);
            if (cursor != null) {
                while (cursor.moveToNext() && !isCancelled()) {
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    Uri musicUri = Uri.withAppendedPath(uri, id);
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    int albumId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ID));
                    Uri albumUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
                    Music music = new Music(musicUri, albumUri, name, duration, 0);
                    if (uri != null) {
                        ContentResolver resolver = getContentResolver();
                        music.MusicImage = Util.CreateBitmap(resolver, albumUri);
                    }
                    publishProgress(music);
                }
                cursor.close();

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Music... musiclists) {
            Music music = musiclists[0];
            musicList.add(music);
            //TODO
        }
    }

    private void showPlayList() {

    }
    private AdapterView.OnItemClickListener mOnMusicItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(mMusicService != null) {
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
}
