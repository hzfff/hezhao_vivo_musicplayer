package com.example.musicplayer_hezhao.model;

import java.io.Serializable;

/**
 * Created by 11120555 on 2020/8/5 15:11
 */
public class ResponseCheck implements Serializable {
    private String msg;
    private int code;
    private String message;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
