package com.example.musicplayer_hezhao.model;

import com.example.musicplayer_hezhao.util.MusicListDialog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/23 8:37
 */
public class MusicListModel implements Serializable {
    //歌单名字，唯一
    private String MusicListName;
    //歌单中包含的音乐的MusicUri
    private List<Music> MusicName;
    //标志位,用于后期判断是自己创建的歌单还是收藏别人的歌单
    private  boolean index;
    public MusicListModel(String MusicListName)
    {
        this.MusicListName=MusicListName;
    }

    public MusicListModel(String musicListName, List<Music> musicName) {
        MusicListName = musicListName;
        MusicName = musicName;
    }

    public String getMusicListName() {
        return MusicListName;
    }

    public void setMusicListName(String musicListName) {
        MusicListName = musicListName;
    }

    public List<Music> getMusicName() {
        return MusicName;
    }

    public void setMusicName(List<Music> musicName) {
        MusicName = musicName;
    }
}
