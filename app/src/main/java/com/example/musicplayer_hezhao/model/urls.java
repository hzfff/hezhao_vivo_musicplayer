package com.example.musicplayer_hezhao.model;

import java.io.Serializable;

/**
 * Created by 11120555 on 2020/8/3 14:13
 */
public class urls  implements Serializable {
    private String url;
    private int id;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
