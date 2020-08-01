package com.example.musicplayer_hezhao.model;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/31 8:35
 */
public class Info {
   private String pic;
   private String titleColor;
   private String typeTitle;
   private String  name;//歌单名
   private Long id;//歌单的id
   private String coverImgUrl;//歌单的封面
   private Create creator;//歌单创建者信息
   private int playCount;//歌单播放数

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Create getCreator() {
        return creator;
    }

    public void setCreator(Create creator) {
        this.creator = creator;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }
}
