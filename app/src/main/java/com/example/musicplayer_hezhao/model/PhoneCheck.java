package com.example.musicplayer_hezhao.model;

import java.io.Serializable;

/**
 * Created by 11120555 on 2020/8/5 14:56
 */
public class PhoneCheck  implements Serializable {
    private int exist;
    private String nickname;
    private String hasPassword;
    private int code;

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(String hasPassword) {
        this.hasPassword = hasPassword;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
