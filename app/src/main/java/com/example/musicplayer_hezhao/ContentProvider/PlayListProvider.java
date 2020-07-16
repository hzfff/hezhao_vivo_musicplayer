package com.example.musicplayer_hezhao.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.musicplayer_hezhao.DB.DBHelper;

/**
 * Created by 11120555 on 2020/7/16 11:23
 */
//PlayListProvider主要用于操作数据库
public class PlayListProvider extends ContentProvider {
    //用于后期选择去匹配那张数据表
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBHelper dbHelper;
    private static final int MATCH_FIRST = 1;
    private static final int MATCH_SECOND = 2;
    public static final String AUTHORITY = "com.example.musicplayer_hezhao.ContentProvider";
    //后期随着数据库表的增加继续增加
    public static final Uri CONTENT_URI_SONG_FIRST = Uri.parse("content://" + AUTHORITY + "/first");

    //建立一个代码块用于初始化
    static {
        matcher.addURI(AUTHORITY, "first", MATCH_FIRST);
        //后续继续初始化
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return false;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (matcher.match(uri)) {
            case MATCH_FIRST:
                cursor = db.query(dbHelper.PLAYLIST_TABLE_NAME, strings, s, strings1, null, null, s1);
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri cursor = null;
        long id = 0;
        switch (matcher.match(uri)) {
            case MATCH_FIRST:
                id = db.insert(dbHelper.PLAYLIST_TABLE_NAME, null, contentValues);
                if (id > 0) {
                    cursor = ContentUris.withAppendedId(CONTENT_URI_SONG_FIRST, id);
                }
                break;
        }
        return cursor;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (matcher.match(uri)) {
            case MATCH_FIRST:
                count = db.delete(dbHelper.PLAYLIST_TABLE_NAME, s, strings);
                break;

        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (matcher.match(uri)) {
            case MATCH_FIRST:
                count = db.update(dbHelper.PLAYLIST_TABLE_NAME, contentValues, s, strings);
                break;
        }
        return count;
    }
}
