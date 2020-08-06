package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/31 18:00
 */
public class Vedio  implements Serializable {
    private String name;//vedio名字
    private String artistName;//作者名字
    private String cover;//vedio 图片
    private String publishTime;//发布日期
    private int playCount;//播放次数
    private int duration;
    private Object brs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Object getBrs() {
        return brs;
    }

    public void setBrs(Object brs) {
        this.brs = brs;
    }
}
