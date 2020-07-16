package com.example.musicplayer_hezhao.model;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by 11120555 on 2020/7/16 8:41
 */
public class Music {
    //音乐名
    private String Name;
    //歌手名
    private String Artist;
    //歌曲链接
    private Uri MusicUri;
    //歌曲专辑
    private String Album;
    //歌曲封面Uri
    private Uri albumUri;
    //歌曲播放进度
    private long PlayedTime;
    //歌曲图片
    private Bitmap MusicImage;
    //歌曲时长
    private long Duration;

    public Music(String name, String artist, Uri musicUri, String album, Uri albumUri, long playedTime, Bitmap musicImage, long duration) {
        Name = name;
        Artist = artist;
        MusicUri = musicUri;
        Album = album;
        this.albumUri = albumUri;
        PlayedTime = playedTime;
        MusicImage = musicImage;
        Duration = duration;
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
        return albumUri;
    }

    public void setAlbumUri(Uri albumUri) {
        this.albumUri = albumUri;
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
