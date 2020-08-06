package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/3 14:07
 */
public class MusicUrl implements Serializable {
    private List<urls> data;

    public List<urls> getData() {
        return data;
    }

    public void setData(List<urls> data) {
        this.data = data;
    }
}
