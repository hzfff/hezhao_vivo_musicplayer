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
    public final static String PLAYLIST_TABLE_NAME = "playlist_table";
    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String LAST_PLAY_TIME = "last_play_time";
    public final static String SONG_URI = "song_uri";
    public final static String ALBUM_URI = "album_uri";
    public final static String DURATION = "duration";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String PLAYLIST_TABLE_DB = "CREATE TABLE  " + PLAYLIST_TABLE_NAME
                + "("
                + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + "VERCHAR(256),"
                + LAST_PLAY_TIME + "LONG,"
                + SONG_URI + "VERCHAR(128),"
                + ALBUM_URI + "VERCHAR(128),"
                + DURATION + "LONG"
                + ");";
        sqLiteDatabase.execSQL(PLAYLIST_TABLE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLAYLIST_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
