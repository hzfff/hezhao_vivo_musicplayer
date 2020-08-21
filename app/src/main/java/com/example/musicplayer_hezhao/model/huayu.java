package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/7 8:50
 */
public class huayu  implements Serializable {
    private List<Artists> artists;
    private boolean more;
    private int code;
    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }
    public List<Artists> getArtists() {
        return artists;
    }

    public void setMore(boolean more) {
        this.more = more;
    }
    public boolean getMore() {
        return more;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public class Artists implements Serializable{

        private String img1v1Url;
        private String picUrl;
        private String name;
        private int id;
        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }
        public String getImg1v1Url() {
            return img1v1Url;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
        public String getPicUrl() {
            return picUrl;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

    }
}
