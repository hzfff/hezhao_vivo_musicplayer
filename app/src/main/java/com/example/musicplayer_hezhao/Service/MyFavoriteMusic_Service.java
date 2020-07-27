package com.example.musicplayer_hezhao.Service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import com.example.musicplayer_hezhao.ContentProvider.PlayListProvider;
import com.example.musicplayer_hezhao.DB.DBHelper;
import com.example.musicplayer_hezhao.fragment.My_Favorite_Music;
import com.example.musicplayer_hezhao.model.Music;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.musicplayer_hezhao.DB.DBHelper.SONG_URI;

/**
 * Created by 11120555 on 2020/7/21 15:28
 */
public class MyFavoriteMusic_Service extends Service {
    private ContentResolver contentResolver;
    private final Binder binder = new MusicServiceIBinder();
    private List<Music> musicList = new ArrayList<>();

    public MyFavoriteMusic_Service() {
    }

    @Override
    public void onCreate() {
        contentResolver = getContentResolver();
//        initdata();
        super.onCreate();
    }

    public void initdata() {
        musicList.clear();
        Cursor cursor = getContentResolver().query(
                PlayListProvider.CONTENT_URI_SONG_SECOND,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String songUri = cursor.getString(cursor.getColumnIndexOrThrow(SONG_URI));
            String albumUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ALBUM_URI));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
            long playedtime = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.LAST_PLAY_TIME));
            long duration = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.DURATION));
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ARTIST));
            Music music = new Music(songUri, albumUri, name, duration, playedtime,artist);
            musicList.add(music);
        }
        cursor.close();
    }

    public class MusicServiceIBinder extends Binder {
        public void delete(Music music,String UserName) {
            ContentResolver resolver = getContentResolver();
            String where = "song_uri=? and username=?";
            String[] Args = new String[]{music.getMusicUri(),UserName};
            resolver.delete(PlayListProvider.CONTENT_URI_SONG_SECOND, where, Args);
        }

        public void add(Music music,String UserName) {
            ContentValues contentView = new ContentValues();
            contentView.put(DBHelper.NAME, music.Name);
            contentView.put(DBHelper.DURATION, music.Duration);
            contentView.put(DBHelper.LAST_PLAY_TIME, music.PlayedTime);
            contentView.put(SONG_URI, music.MusicUri);
            contentView.put(DBHelper.ALBUM_URI, music.AlbumUri);
            contentView.put(DBHelper.ARTIST, music.Artist);
            contentView.put(DBHelper.UserName, UserName);
            contentResolver.insert(PlayListProvider.CONTENT_URI_SONG_SECOND, contentView);
        }

        public int query(Music music,String UserName) {
            String where = "song_uri=? and username=?";
            String[] SongUri =new String[]{ music.getMusicUri(),UserName};
            Cursor cursor = contentResolver.query(
                    PlayListProvider.CONTENT_URI_SONG_SECOND,
                    null,
                    where ,
                    SongUri,
                    null);
            int index = -1;
            if (cursor.moveToNext()) {
                index = 1;
            }
            return index;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
