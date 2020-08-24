package com.example.musicplayer_hezhao;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.musicplayer_hezhao.Service.ListService;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.tool.LrcRow;
import com.example.musicplayer_hezhao.tool.LrcRows;
import com.example.musicplayer_hezhao.tool.LrcView;
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;

import java.util.List;

/**
 * Created by 11120555 on 2020/8/4 8:56
 */
public class PlayMusicListActivity extends AppCompatActivity implements LrcView.MedCallBack {
    private List<String> MusicUrl;
    private List<MusicInfo> MusicInfo;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private TextView textView1;
    private TextView textView2;
    private static TextView textView3;
    private static TextView textView4;
    private Intent intent;
    private Bundle bundle;
    private Toolbar toolbar;
    private static SeekBar seekBar;
    private int position;
    private ObjectAnimator animator;
    private ListService.MusicControl listservice;
    private PlayMusicConn playMusicConn;
    private boolean is_playing = false;
    private boolean index = false;
    private static List<String>Lyriclist;
    private static LrcView lrcView;
    private static LrcRows lrcRows=new LrcRows();
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.play_music_main_pager);
        initview();
        initdata();
        initListener();
    }

    public void initview() {
        lrcView=findViewById(R.id.lyric);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.played_time);
        textView4 = findViewById(R.id.duration_time);
        imageView1 = findViewById(R.id.image);
        imageView2 = findViewById(R.id.pre_btn);
        imageView3 = findViewById(R.id.play_btn);
        imageView4 = findViewById(R.id.next_btn);
        imageView5 = findViewById(R.id.img1);
        imageView6 = findViewById(R.id.img3);
        imageView7 = findViewById(R.id.share);
        imageView8 = findViewById(R.id.play_music_background);
        toolbar = findViewById(R.id.toolbar);
        seekBar = findViewById(R.id.seek_music);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        intent = getIntent();
        bundle = intent.getExtras();
        MusicUrl = (List<String>) bundle.getSerializable("musicUrl");
        Lyriclist= (List<String>) bundle.getSerializable("Lyriclist");
        MusicInfo = (List<com.example.musicplayer_hezhao.model.MusicInfo>) bundle.getSerializable("musicInfo");
        position = bundle.getInt("position");
    }

    public void initdata() {
        playMusicConn = new PlayMusicConn();
        if(listservice!=null) {
            listservice.init();
        }
        Intent intent = new Intent(getApplicationContext(), ListService.class);
        boolean test= getApplicationContext().bindService(intent, playMusicConn, BIND_AUTO_CREATE);
        textView1.setText(MusicInfo.get(position).getSongs()[0].getName());
        textView2.setText(MusicInfo.get(position).getSongs()[0].getAl().getName());
        Glide.with(getApplicationContext()).load(MusicInfo.get(position).getSongs()[0].getAl().getPicUrl()).into(imageView1);
        Glide.with(getApplicationContext()).load(MusicInfo.get(position).getSongs()[0].getAl().getPicUrl()).into(imageView8);
        animator = ObjectAnimator.ofFloat(imageView1, "rotation", 0f, 360.0f);//设置图片动画
        animator.setDuration(10000);//动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);
        lrcRows=new LrcRows();
        List<LrcRow>list=lrcRows.BuildList(getApplicationContext(),Lyriclist.get(position));
        lrcView.clearAnimation();
        lrcView.setLrc(list);
        lrcView.setCall(this);
        inittime();
        seekBar.getThumb().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//设置滑块颜色、样式
        seekBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//设置进度条颜色、样式
    }

    public void ChangePic() {
        textView1.setText(MusicInfo.get(position).getSongs()[0].getName());
        textView2.setText(MusicInfo.get(position).getSongs()[0].getAl().getName());
        Glide.with(getApplicationContext()).load(MusicInfo.get(position).getSongs()[0].getAl().getPicUrl()).into(imageView1);
        Glide.with(getApplicationContext()).load(MusicInfo.get(position).getSongs()[0].getAl().getPicUrl()).into(imageView8);
    }
    public void inittime(){
        MediaPlayer player=new MediaPlayer();
        String strs=MusicUrl.get(position) ;
        player = MediaPlayer.create(getApplicationContext(), Uri.parse(MusicUrl.get(position)));
        int minute = player.getDuration() / 1000 / 60;
        int second = player.getDuration() / 1000 % 60;
        String strMinute = null;
        String strSecond = null;
        if (minute < 10) {//如果歌曲的时间中的分钟小于10
            strMinute = "0" + minute;//在分钟的前面加一个0
        } else {
            strMinute = minute + "";
        }
        if (second < 10) {//如果歌曲中的秒钟小于10
            strSecond = "0" + second;//在秒钟前面加一个0
        } else {
            strSecond = second + "";
        }
        textView4.setText(strMinute + ":" + strSecond);
    }
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position -= 1;
                if (position < 0) {
                    position = MusicUrl.size() - 1;
                }

                if (!is_playing) {
                    imageView3.setBackgroundResource(R.mipmap.play);
                    animator.start();
                    is_playing=true;
                }
                ChangePic();
                listservice.play(MusicUrl, position);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                ChangePic();
                if (!is_playing) {
                    if (!index) {
                        listservice.play(MusicUrl, position);
                        index=true;
                    } else {
                        listservice.continuePlay();
                    }
                    animator.start();
                    imageView3.setBackgroundResource(R.mipmap.play);
                    is_playing = true;
                } else {
                    is_playing = false;
                    imageView3.setBackgroundResource(R.mipmap.stop);
                    animator.pause();
                    listservice.pausePlay();
                }
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position += 1;
                if (position == MusicUrl.size()) {
                    position = 0;
                }
                if (!is_playing) {
                    imageView3.setBackgroundResource(R.mipmap.play);
                    animator.start();
                    is_playing=true;
                }
                ChangePic();
                listservice.play(MusicUrl, position);
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
                int progress=seekBar.getProgress();//获取seekBar的进度
                listservice.seekTo(progress);
            }
        });
    }

    @Override
    public void call(long time) {

    }

    class PlayMusicConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            listservice = (ListService.MusicControl) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public static Handler handler = new Handler() {//创建消息处理器对象
        //在主线程中处理从子线程发送过来的消息
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();//获取从子线程发送过来的音乐播放进度
            int duration = bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");
            seekBar.setMax(duration);
            seekBar.setProgress(currentPosition);
            //歌曲总时长
            int minute = duration / 1000 / 60;
            int second = duration / 1000 % 60;
            String strMinute = null;
            String strSecond = null;
            if (minute < 10) {//如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute;//在分钟的前面加一个0
            } else {
                strMinute = minute + "";
            }
            if (second < 10) {//如果歌曲中的秒钟小于10
                strSecond = "0" + second;//在秒钟前面加一个0
            } else {
                strSecond = second + "";
            }
            textView4.setText(strMinute + ":" + strSecond);
            //歌曲当前播放时长
            minute = currentPosition / 1000 / 60;
            second = currentPosition / 1000 % 60;
            if (minute < 10) {//如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute;//在分钟的前面加一个0
            } else {
                strMinute = minute + " ";
            }
            if (second < 10) {//如果歌曲中的秒钟小于10
                strSecond = "0" + second;//在秒钟前面加一个0
            } else {
                strSecond = second + " ";
            }
            textView3.setText(strMinute + ":" + strSecond);
            lrcView.LrcToPlayer(currentPosition);
        }
    };
}
