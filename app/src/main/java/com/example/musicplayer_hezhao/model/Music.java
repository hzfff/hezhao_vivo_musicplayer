package com.example.musicplayer_hezhao.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by 11120555 on 2020/7/16 8:41
 */
public class Music implements Serializable {
    //音乐名
    public String Name;
    //歌手名
    public String Artist;
    //歌曲链接
    public String MusicUri;
    //歌曲专辑
    public String Album;
    //歌曲封面Uri
    public String AlbumUri;
    //歌曲播放进度
    public long PlayedTime;
    //歌曲图片
    public String MusicImage;
    //歌曲时长
    public long Duration;

    public Music() {
    }

    public Music(String name, String musicUri, String albumUri, long duration) {
        Name = name;
        MusicUri = musicUri;
        AlbumUri = albumUri;
        Duration = duration;
    }

    public Music(String musicUri, String albumUri, String name, long duration, long playedTime) {
        MusicUri = musicUri;
        AlbumUri = albumUri;
        Name = name;
        Duration = duration;
        PlayedTime = playedTime;

    }

    public Music(String musicUri, String albumUri, String name, long duration, long playedTime, String artist) {
        MusicUri = musicUri;
        AlbumUri = albumUri;
        Name = name;
        Duration = duration;
        PlayedTime = playedTime;
        Artist = artist;
    }

    public Music(String musicUri, String albumUri, String name, long duration, String artist, long playedTime) {
        MusicUri = musicUri;
        AlbumUri = albumUri;
        Name = name;
        Duration = duration;
        Artist = artist;
        PlayedTime = playedTime;

    }

    protected Music(Parcel in) {
        Name = in.readString();
        Artist = in.readString();
        MusicUri = in.readString();
        Album = in.readString();
        AlbumUri = in.readString();
        PlayedTime = in.readLong();
        MusicImage = in.readString();
        Duration = in.readLong();
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getMusicUri() {
        return MusicUri;
    }

    public void setMusicUri(String musicUri) {
        MusicUri = musicUri;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getAlbumUri() {
        return AlbumUri;
    }

    public void setAlbumUri(String albumUri) {
        this.AlbumUri = albumUri;
    }

    public long getPlayedTime() {
        return PlayedTime;
    }

    public void setPlayedTime(long playedTime) {
        PlayedTime = playedTime;
    }

    public String getMusicImage() {
        return MusicImage;
    }

    public void setMusicImage(String musicImage) {
        MusicImage = musicImage;
    }

    public long getDuration() {
        return Duration;
    }

    public void setDuration(long duration) {
        Duration = duration;
    }

}
