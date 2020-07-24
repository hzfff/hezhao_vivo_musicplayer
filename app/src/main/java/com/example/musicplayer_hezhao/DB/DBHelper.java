package com.example.musicplayer_hezhao.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteMisuseException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 11120555 on 2020/7/16 14:13
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "playlist.db";
    private final static int DB_VERSION = 1;
    public final static String PLAYLIST_TABLE_NAME = "playlist_table";//播放列表
    public final static String FAVORITE_TABLE_NAME = "favoritelist_table";//最喜欢的音乐列表
    public final static String RECENT_TABLE_NAME = "recentlist_table";//最近播放列表
    public final static String SONG_LIST_TABLE_NAME = "songlist_table";//歌单表
    public final static String ID = "id";
    public final static String MUSIC_LIST_NAME = "musiclist_name";
    public final static String NAME = "name";
    public final static String LAST_PLAY_TIME = "last_play_time";
    public final static String SONG_URI = "song_uri";
    public final static String ALBUM_URI = "album_uri";
    public final static String DURATION = "duration";
    public final static String ARTIST = "artist";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String PLAYLIST_TABLE_DB = "CREATE TABLE  " + PLAYLIST_TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " VERCHAR(256),"
                + LAST_PLAY_TIME + " LONG,"
                + SONG_URI + " VERCHAR(128),"
                + ALBUM_URI + " VERCHAR(128),"
                + DURATION + " LONG"
                + ");";
        String FAVORITE_TABLE_DB = "CREATE TABLE  " + FAVORITE_TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " VERCHAR(256),"
                + LAST_PLAY_TIME + " LONG,"
                + SONG_URI + " VERCHAR(128),"
                + ALBUM_URI + " VERCHAR(128),"
                + DURATION + " LONG,"
                + ARTIST + " VERCHAR(256)"
                + ");";
        String RECENT_TABLE = "CREATE TABLE  " + RECENT_TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " VERCHAR(256),"
                + LAST_PLAY_TIME + " LONG,"
                + SONG_URI + " VERCHAR(128),"
                + ALBUM_URI + " VERCHAR(128),"
                + DURATION + " LONG,"
                + ARTIST + " VERCHAR(256)"
                + ");";
        String SONG_LIST_TABLE = "CREATE TABLE  " + SONG_LIST_TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MUSIC_LIST_NAME + " VERCHAR(256) NOT NULL ,"
                + NAME + " VERCHAR(256),"
                + LAST_PLAY_TIME + " LONG,"
                + SONG_URI + " VERCHAR(128),"
                + ALBUM_URI + " VERCHAR(128),"
                + DURATION + " LONG,"
                + ARTIST + " VERCHAR(256)"
                + ");";
        sqLiteDatabase.execSQL(PLAYLIST_TABLE_DB);
        sqLiteDatabase.execSQL(FAVORITE_TABLE_DB);
        sqLiteDatabase.execSQL(RECENT_TABLE);
        sqLiteDatabase.execSQL(SONG_LIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLAYLIST_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVORITE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECENT_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SONG_LIST_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
