package com.example.musicplayer_hezhao.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by 11120555 on 2020/7/28 9:28
 */
public class MusicPlayService extends Service {
    private final Binder mbinder = new MusicServiceIBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }
    public class MusicServiceIBinder  extends Binder{

    }
}
