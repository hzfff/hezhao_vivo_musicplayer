package com.example.musicplayer_hezhao.model;

import java.io.Serializable;

/**
 * Created by 11120555 on 2020/7/31 17:56
 */
public class VedioInformation implements Serializable {
   private Vedio  data;
   private int code;
    public Vedio getData() {
        return data;
    }

    public void setData(Vedio data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
