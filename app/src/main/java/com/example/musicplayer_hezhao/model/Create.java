package com.example.musicplayer_hezhao.model;

import java.io.Serializable;

/**
 * Created by 11120555 on 2020/7/31 16:02
 */
public class Create  implements Serializable {
    private String nickname;
    private String avatarUrl;
    private String signature;
    private String backgroundUrl;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }
}
