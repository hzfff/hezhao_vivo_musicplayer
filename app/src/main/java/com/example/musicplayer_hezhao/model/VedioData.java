package com.example.musicplayer_hezhao.model;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/31 17:55
 */
public class VedioData {
private List<VedioInformation>data;
private int code;

    public List<VedioInformation> getData() {
        return data;
    }

    public void setData(List<VedioInformation> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
