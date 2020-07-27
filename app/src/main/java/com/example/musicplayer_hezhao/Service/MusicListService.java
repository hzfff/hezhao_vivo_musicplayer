package com.example.musicplayer_hezhao.Service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import com.example.musicplayer_hezhao.ContentProvider.PlayListProvider;
import com.example.musicplayer_hezhao.DB.DBHelper;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.example.musicplayer_hezhao.DB.DBHelper.SONG_URI;

/**
 * Created by 11120555 on 2020/7/23 8:28
 */
//处理歌单的逻辑
public class MusicListService extends Service {
    private ContentResolver contentResolver;
    private List<Music> musicList;//新建一个音乐表单数组
    private List<MusicListModel> musicListModels;//新建一个歌单列表
    private final Binder binder = new MusicServiceIBinder();

    @Override
    public void onCreate() {
        contentResolver = getContentResolver();
        musicList = new ArrayList<>();
        musicListModels = new ArrayList<>();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MusicServiceIBinder extends Binder {
        public int InsertMusicList(String MusicListName, Music music,String UserName) {
            return insertMusicListInner(MusicListName, music,UserName);
        }//向指定歌单插入音乐

        public int DeleteMusicFromList(String MusicListName, Music music,String UserName) {
            return deleteMusicFromListInner(MusicListName, music,UserName);
        }//从指定歌单删除某首音乐

        public void DeleteMusicList(String MusicListName,String UserName) {
            deleteMusicListInner(MusicListName,UserName);
        }

        public int CreateMusicList(String MusicListName,String UserName) {
            return CreateMusicListInner(MusicListName,UserName);
        }//创建一个喜欢的音乐表单

        public List<Music> QueryMusicFromList(String MusicListName,String UserName) {
            return QueryMusicFromListInner(MusicListName,UserName);
        }//从指定歌单中查询所有音乐

        public List<MusicListModel> QueryMusicList(String UserName) {
            return QueryMusicListInner(UserName);
        }//查询所有的歌单
    }

    public void deleteMusicListInner(String musiclistname, String UserName) {
        ContentResolver resolver = getContentResolver();
        String where = " musiclist_name=? and username=?";
        String[] Args = new String[]{ musiclistname,UserName};
        resolver.delete(PlayListProvider.CONTENT_URI_SONG_FOURTH, where, Args);
    }

    public int insertMusicListInner(String MusicListName, Music music, String UserName) {
        ContentValues contentView = new ContentValues();
        contentView.put(DBHelper.MUSIC_LIST_NAME, MusicListName);
        contentView.put(DBHelper.NAME, music.Name);
        contentView.put(DBHelper.LAST_PLAY_TIME, music.PlayedTime);
        contentView.put(DBHelper.SONG_URI, music.MusicUri);
        contentView.put(DBHelper.ALBUM_URI, music.AlbumUri);
        contentView.put(DBHelper.DURATION, music.Duration);
        contentView.put(DBHelper.ARTIST, music.Artist);
        contentView.put(DBHelper.UserName, UserName);
        Uri index = contentResolver.insert(PlayListProvider.CONTENT_URI_SONG_FOURTH, contentView);
        return 0;
    }

    public int deleteMusicFromListInner(String MusicListName, Music music, String UserName) {
        ContentResolver resolver = getContentResolver();
        String where = "song_uri=? and musiclist_name=? and username=?";
        String[] Args = new String[]{music.getMusicUri(), MusicListName,UserName};
        int index = resolver.delete(PlayListProvider.CONTENT_URI_SONG_FOURTH, where, Args);
        return index;
    }

    public int CreateMusicListInner(String MusicListName, String UserName) {
        ContentValues contentView = new ContentValues();
        contentView.put(DBHelper.MUSIC_LIST_NAME, MusicListName);
        contentView.put(DBHelper.UserName, UserName);
        contentResolver.insert(PlayListProvider.CONTENT_URI_SONG_FOURTH, contentView);
        return 0;
    }

    public List<Music> QueryMusicFromListInner(String MusicListName, String UserName) {
        musicList.clear();
        String where = "musiclist_name=? and  username=?";
        String[] SongListUri = new String[]{MusicListName,UserName};
        Cursor cursor = contentResolver.query(
                PlayListProvider.CONTENT_URI_SONG_FOURTH,
                null,
                where,
                SongListUri,
                null);
        int index = -1;
        while (cursor.moveToNext()) {
            String songUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SONG_URI));
            String albumUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ALBUM_URI));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
            long playedtime = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.LAST_PLAY_TIME));
            long duration = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.DURATION));
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ARTIST));
            Music music = new Music(songUri, albumUri, name, duration, playedtime, artist);
            musicList.add(music);
        }
        return musicList;
    }

    public List<MusicListModel> QueryMusicListInner(String UserName) {
        musicListModels.clear();
        HashMap<String, Integer> map = new HashMap<>();
        String where = "username=?";
        String[] SongListUri = new String[]{UserName};
        Cursor cursor = contentResolver.query(
                PlayListProvider.CONTENT_URI_SONG_FOURTH,
                null,
                where,
                SongListUri,
                null);
        while (cursor.moveToNext()) {
            String musiclistname = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.MUSIC_LIST_NAME));
            String songUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SONG_URI));
            String albumUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ALBUM_URI));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
            long playedtime = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.LAST_PLAY_TIME));
            long duration = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.DURATION));
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ARTIST));
            Music music = null;
            if (null != songUri) {
                music = new Music(songUri, albumUri, name, duration, playedtime, artist);
            }
            if (map.containsKey(musiclistname)) {
                int key = map.get(musiclistname);
                musicListModels.get(key).getMusicName().add(music);
            } else {
                map.put(musiclistname, musicListModels.size());
                List<Music> list = new ArrayList<>();
                MusicListModel musicListModel = new MusicListModel(musiclistname, list);
                musicListModels.add(musicListModel);
            }
        }
        return musicListModels;
    }
}
