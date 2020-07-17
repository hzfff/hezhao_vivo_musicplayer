package com.example.musicplayer_hezhao.model;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by 11120555 on 2020/7/16 8:41
 */
public class Music {
    //音乐名
    public  String Name;
    //歌手名
    public  String Artist;
    //歌曲链接
    public  Uri MusicUri;
    //歌曲专辑
    public  String Album;
    //歌曲封面Uri
    public Uri AlbumUri;
    //歌曲播放进度
    public  long PlayedTime;
    //歌曲图片
    public Bitmap MusicImage;
    //歌曲时长
    public long Duration;

    public Music(String name,  Uri musicUri,  Uri albumUri,  long duration) {
        Name = name;
        MusicUri = musicUri;
        this.AlbumUri = albumUri;
        Duration = duration;
    }
    public Music(Uri musicUri,Uri albumUri,String name,long duration,long playedTime) {
        MusicUri=musicUri;
        AlbumUri=albumUri;
        Name=name;
        Duration=duration;
        PlayedTime=playedTime;

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

    public Uri getMusicUri() {
        return MusicUri;
    }

    public void setMusicUri(Uri musicUri) {
        MusicUri = musicUri;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public Uri getAlbumUri() {
        return AlbumUri;
    }

    public void setAlbumUri(Uri albumUri) {
        this.AlbumUri = albumUri;
    }

    public long getPlayedTime() {
        return PlayedTime;
    }

    public void setPlayedTime(long playedTime) {
        PlayedTime = playedTime;
    }

    public Bitmap getMusicImage() {
        return MusicImage;
    }

    public void setMusicImage(Bitmap musicImage) {
        MusicImage = musicImage;
    }

    public long getDuration() {
        return Duration;
    }

    public void setDuration(long duration) {
        Duration = duration;
    }
    @Override
    public boolean equals(Object Item)
    {
        Music o= (Music) Item;
        return o.MusicUri==this.MusicUri;
    }
}
