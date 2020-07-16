package com.example.musicplayer_hezhao.model;

import java.io.Serializable;

/**
 * Created by 11120555 on 2020/7/16 10:21
 */
public class SearchResult  implements Serializable {
    private String MusicName;
    private String MusicUrl;
    private String MusicArtist;
    private String Album;
    private long duration;
    private long playedtime;
    public String getMusicName() {
        return MusicName;
    }

    public void setMusicName(String musicName) {
        MusicName = musicName;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPlayedtime() {
        return playedtime;
    }

    public void setPlayedtime(long playedtime) {
        this.playedtime = playedtime;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getMusicArtist() {
        return MusicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        MusicArtist = musicArtist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }
}
