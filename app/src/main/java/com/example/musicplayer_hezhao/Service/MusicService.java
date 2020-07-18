package com.example.musicplayer_hezhao.Service;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.example.musicplayer_hezhao.ContentProvider.PlayListProvider;
import com.example.musicplayer_hezhao.DB.DBHelper;
import com.example.musicplayer_hezhao.MusicWeight;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.model.Music;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/16 15:23
 */
public class MusicService extends Service {
    public static final String ACTION_PLAY_MUSIC_PRE = "com.example.musicplayer_hezhao.playpre";
    public static final String ACTION_PLAY_MUSIC_NEXT = "com.example.musicplayer_hezhao.playnext";
    public static final String ACTION_PLAY_MUSIC_TOGGLE = "com.example.musicplayer_hezhao.playtoggle";
    public static final String ACTION_PLAY_MUSIC_UPDATE = "com.example.musicplayer_hezhao.playupdate";
    private final int MSG_PROGRESS_UPDATE = 0;
    private List<OnStateChangeListener> listenerList=new ArrayList<>();
    private List<Music> musicList;
    private Music mcurrentmusic;
    private MediaPlayer mediaPlayer;
    private ContentResolver contentResolver;
    private boolean mPaused;
    private final Binder mbinder = new MusicServiceIBinder();

    public interface OnStateChangeListener {
        void onPlayProgressChange(Music music);
        void onPlay(Music music);
        void onPause(Music music);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicList = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
        contentResolver = getContentResolver();
        mPaused = false;
        mediaPlayer.setOnCompletionListener(onCompletionListener);
        IntentFilter commandFilter = new IntentFilter();
        commandFilter.addAction(ACTION_PLAY_MUSIC_UPDATE);
        registerReceiver(broadcastReceiver, commandFilter);
        initplaylist();
        if (mcurrentmusic != null) {
            preparToPlay(mcurrentmusic);
        }
        updateAppWidget(mcurrentmusic);
    }

    private void preparToPlay(Music mcurrentmusic) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(MusicService.this, mcurrentmusic.MusicUri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case MSG_PROGRESS_UPDATE:
                    mcurrentmusic.PlayedTime = mediaPlayer.getCurrentPosition();
                    mcurrentmusic.Duration = mediaPlayer.getDuration();
                    for (OnStateChangeListener onStateChangeListener : listenerList) {
                        onStateChangeListener.onPlayProgressChange(mcurrentmusic);
                    }
                    updateMusicItem(mcurrentmusic);
                    sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 1000);
                    break;
            }

        }

    };
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_PLAY_MUSIC_UPDATE.equals(action)) {
                updateAppWidget(mcurrentmusic);
            }
        }
    };

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                if (ACTION_PLAY_MUSIC_PRE.equals(action)) {
                    playPreInnner();
                } else if (ACTION_PLAY_MUSIC_NEXT.equals(action)) {
                    playNextInner();
                } else if (ACTION_PLAY_MUSIC_TOGGLE.equals(action)) {
                    if (isPlayingInner()) {
                        pauseInner();
                    } else {
                        playInner();
                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //播放上一首歌曲,获取当前位置，取前一首歌曲
    public void playPreInnner() {
        int currentindex = musicList.indexOf(mcurrentmusic);
        if (currentindex - 1 >= 0) {
            mcurrentmusic = musicList.get(currentindex - 1);
            playMusicItem(mcurrentmusic, true);
        }
    }

    //播放下一首歌曲
    public void playNextInner() {
        //获取当前播放歌曲位置，然后取后一首歌曲
        int currentindex = musicList.indexOf(mcurrentmusic);
        if (currentindex + 1 < musicList.size()) {
            mcurrentmusic = musicList.get(currentindex + 1);
            playMusicItem(mcurrentmusic, true);
        }
    }

    //暂停
    public void pauseInner() {
        mPaused = true;
        mediaPlayer.pause();
        for (OnStateChangeListener onStateChangeListener : listenerList) {
            onStateChangeListener.onPause(mcurrentmusic);
        }
        handler.removeMessages(MSG_PROGRESS_UPDATE);
        updateAppWidget(mcurrentmusic);
    }

    //播放
    public void playInner() {
        if (musicList.size() > 0 && mcurrentmusic == null) {
            mcurrentmusic = musicList.get(0);
        }
        if (mPaused) {
            playMusicItem(mcurrentmusic, false);
        } else {
            playMusicItem(mcurrentmusic, true);
        }
    }

    public void seekToInner(int position) {
        mediaPlayer.seekTo(position);
    }

    public void registerOnStateChangeListenerInner(OnStateChangeListener onStateChangeListener) {
        listenerList.add(onStateChangeListener);
    }

    public void unregisterOnStateChangeListenerInner(OnStateChangeListener onStateChangeListener) {
        listenerList.remove(onStateChangeListener);
    }

    public Music getCurrentMusicInner() {
        return mcurrentmusic;
    }

    public boolean isPlayingInner() {
        return mediaPlayer.isPlaying();
    }

    private void initplaylist() {
        musicList.clear();
        Cursor cursor = contentResolver.query(
                PlayListProvider.CONTENT_URI_SONG_FIRST,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String songUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SONG_URI));
            String albumUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ALBUM_URI));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
            long playedtime = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.LAST_PLAY_TIME));
            long duration = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.DURATION));
            Music music = new Music(Uri.parse(songUri), Uri.parse(albumUri), name, duration, playedtime);
            musicList.add(music);
        }
        cursor.close();
        if (musicList.size() > 0) {
            mcurrentmusic = musicList.get(0);
        }
    }

    public void playMusicItem(Music music, boolean isload) {
        if (music == null) {
            return;
        }
        if (isload) {
            preparToPlay(music);
        }
        mediaPlayer.start();
        seekToInner((int) music.PlayedTime);
        for (OnStateChangeListener onStateChangeListener : listenerList) {
            onStateChangeListener.onPlay(music);
        }
        mPaused = false;
        handler.removeMessages(MSG_PROGRESS_UPDATE);
        handler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
        updateAppWidget(mcurrentmusic);
    }

    public void updateMusicItem(Music music) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DURATION, music.Duration);
        contentValues.put(DBHelper.LAST_PLAY_TIME, music.PlayedTime);
        String SongUri = music.MusicUri.toString();
        String uri=DBHelper.SONG_URI;
        contentResolver.update(PlayListProvider.CONTENT_URI_SONG_FIRST, contentValues, DBHelper.SONG_URI + "=\"" + SongUri + "\"", null);
    }

    private void insertMusicItemToContentProvider(Music music) {
        ContentValues contentView = new ContentValues();
        contentView.put(DBHelper.NAME, music.Name);
        contentView.put(DBHelper.DURATION, music.Duration);
        contentView.put(DBHelper.LAST_PLAY_TIME, music.PlayedTime);
        contentView.put(DBHelper.SONG_URI, music.MusicUri.toString());
        contentView.put(DBHelper.ALBUM_URI, music.AlbumUri.toString());
        Uri uri = contentResolver.insert(PlayListProvider.CONTENT_URI_SONG_FIRST, contentView);
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mcurrentmusic.PlayedTime = 0;
            updateMusicItem(mcurrentmusic);
            playNextInner();
        }
    };

    private void updateAppWidget(Music music) {
        if (music != null) {
            if (music.MusicImage == null) {
                ContentResolver contentResolver = getContentResolver();
                music.MusicImage = Util.CreateBitmap(contentResolver, music.AlbumUri);
            }
            MusicWeight.performUpdates(MusicService.this, music.Name, isPlayingInner(), music.MusicImage);
        }
    }
    private void addPlayListInner(List<Music> items) {

        contentResolver.delete(PlayListProvider.CONTENT_URI_SONG_FIRST, null, null);
        musicList.clear();

        for (Music item : items) {
            addPlayListInner(item, false);
        }

        mcurrentmusic = musicList.get(0);
        playInner();
    }

    private void addPlayListInner(Music item, boolean needPlay) {

        if(musicList.contains(item)) {
            return;
        }

        musicList.add(0, item);

        insertMusicItemToContentProvider(item);

        if(needPlay) {
            mcurrentmusic = musicList.get(0);
            playInner();
        }
    }
    public class MusicServiceIBinder extends Binder {
        public void addPlayList(Music item) {
            addPlayListInner(item, true);
        }
        public void addPlayList(List<Music> items) {
            addPlayListInner(items);
        }
        public void play() {
            playInner();
        }

        public void playNextInner() {
            playNextInner();
        }

        public void playPre() {
            playPre();
        }

        public void pause() {
            pauseInner();
        }

        public void seekTO(int pos) {
            seekToInner(pos);
        }

        public void registerOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
            registerOnStateChangeListenerInner(onStateChangeListener);
        }

        public void unregisterOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
            unregisterOnStateChangeListenerInner(onStateChangeListener);
        }

        public Music getCurrentMusic() {
            return getCurrentMusicInner();
        }

        public boolean isPlaying() {
            return isPlayingInner();
        }

        public List<Music> getPlayList() {
            return musicList;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        unregisterReceiver(broadcastReceiver);
        handler.removeMessages(MSG_PROGRESS_UPDATE);
        listenerList.clear();
        for (Music music : musicList) {
            if (music.MusicImage != null) {
                music.MusicImage.recycle();
            }
        }
        musicList.clear();
    }
}
