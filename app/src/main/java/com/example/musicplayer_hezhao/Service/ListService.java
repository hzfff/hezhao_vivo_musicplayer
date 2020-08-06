package com.example.musicplayer_hezhao.Service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import com.example.musicplayer_hezhao.PlayMusicListActivity;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 11120555 on 2020/8/4 10:58
 */
public class ListService extends Service {
    private ContentResolver contentResolver;
    private MusicControl musicControl = new MusicControl();
    private List<String> MusicList = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private int position;

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicControl();
    }

    public void initplayer() throws IOException {
        mediaPlayer.reset();
        mediaPlayer.prepare();
    }


    public ListService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //创建音乐播放器对象
    }

    public void addTimer() { //添加计时器用于设置音乐播放器中的播放进度条
        if (timer == null) {
            timer = new Timer();//创建计时器对象
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (mediaPlayer == null) return;
                    int duration = mediaPlayer.getDuration();//获取歌曲总时长
                    int currentPosition = mediaPlayer.getCurrentPosition();//获取播放进度
                    Message msg = PlayMusicListActivity.handler.obtainMessage();//创建消息对象
                    //将音乐的总时长和播放进度封装至消息对象中
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration", duration);
                    bundle.putInt("currentPosition", currentPosition);
                    msg.setData(bundle);
                    //将消息发送到主线程的消息队列
                    PlayMusicListActivity.handler.sendMessage(msg);
                }
            };
            //开始计时任务后的5毫秒，第一次执行task任务，以后每500毫秒执行一次
            timer.schedule(task, 5, 500);
        }
    }

    public class MusicControl extends Binder {//Binder是一种跨进程的通信方式

        public void play(List<String>MusicUrl,int position ) {//String path
            Uri uri = Uri.parse(MusicUrl.get(position));
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.reset();//重置音乐播放器
                //加载多媒体文件
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayer.start();//播放音乐
                addTimer();//添加计时器
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void pausePlay() {
            mediaPlayer.pause();//暂停播放音乐
        }

        public void continuePlay() {
            mediaPlayer.start();//继续播放音乐
        }

        public void seekTo(int progress) {
            mediaPlayer.seekTo(progress);//设置音乐的播放位置
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();//停止播放音乐
        mediaPlayer.release();//释放占用的资源
        mediaPlayer = null;//将player置为空
    }
}


