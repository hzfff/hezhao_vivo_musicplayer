package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/7 11:33
 */
public class findsongs implements Serializable{
    private int code;
    private boolean more;
    private List<Songs> songs;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public void setMore(boolean more) {
        this.more = more;
    }
    public boolean getMore() {
        return more;
    }
    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }
    public List<Songs> getSongs() {
        return songs;
    }


    public class Ar implements Serializable{

        private int id;
        private String name;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


    }
    public class Al implements Serializable{
        private String picUrl;
        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
        public String getPicUrl() {
            return picUrl;
        }
    }

    public class Songs  implements Serializable {
        private String name;
        private long id;
        private List<Ar> ar;
        private Al al;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }
        public void setAl(Al al) {
            this.al = al;
        }
        public Al getAl() {
            return al;
        }

        public List<Ar> getAr() {
            return ar;
        }

        public void setAr(List<Ar> ar) {
            this.ar = ar;
        }
    }
}
