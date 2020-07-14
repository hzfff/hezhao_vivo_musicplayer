package com.example.musicplayer_hezhao.model;

/**
 * Created by 11120555 on 2020/7/8 17:04
 */
public class HistoryModel {
    private String MusicName;

    public HistoryModel(String musicName) {
        MusicName = musicName;
    }

    public String getMusicName() {
        return MusicName;
    }

    public void setMusicName(String musicName) {
        MusicName = musicName;
    }
}
