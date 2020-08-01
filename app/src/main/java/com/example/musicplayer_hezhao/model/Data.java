package com.example.musicplayer_hezhao.model;

import com.example.musicplayer_hezhao.model.Info;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/30 18:53
 */
public class Data<T> {
    private List<Num>data;
    private int code;
    private List<Info> banners;
    private List<Info> playlists;

    public List<Info> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Info> playlists) {
        this.playlists = playlists;
    }

    public int getCode() {
        return code;
    }

    public List<Num> getData() {
        return data;
    }

    public void setData(List<Num> data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Info> getBanners() {
        return banners;
    }

    public void setBanners(List<Info> banners) {
        this.banners = banners;
    }
}

