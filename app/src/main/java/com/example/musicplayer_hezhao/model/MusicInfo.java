package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/3 15:48
 */
public class MusicInfo implements Serializable {
    private Song[] songs;
    private int code;

    public Song[] getSongs() {
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public class Song implements  Serializable{
        private String name;
        private Artist[] ar;
        private songpic al;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Artist[] getAr() {
            return ar;
        }

        public void setAr(Artist[] ar) {
            this.ar = ar;
        }

        public songpic getAl() {
            return al;
        }

        public void setAl(songpic al) {
            this.al = al;
        }
    }


    public class Artist implements  Serializable{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class songpic implements Serializable{
        private String id;
        private String name;
        private String picUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }

}
