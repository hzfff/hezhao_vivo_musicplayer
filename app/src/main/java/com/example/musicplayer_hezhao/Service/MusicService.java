package com.example.musicplayer_hezhao.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.musicplayer_hezhao.model.Music;

/**
 * Created by 11120555 on 2020/7/16 15:23
 */
public class MusicService  extends Service {
    public interface OnStateChangeListener{
        void onPlayProgressChange(Music music);
        void onPlay(Music music);
        void onPause(Music music);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
