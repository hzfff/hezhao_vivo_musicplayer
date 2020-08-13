package com.example.musicplayer_hezhao.model;

/**
 * Created by 11120555 on 2020/8/13 10:35
 */
public class klyic {
    private boolean sgc;
    private boolean sfy;
    private boolean qfy;
    private Lrc lrc;
    private int code;
    public class Lrc {

        private int version;
        private String lyric;
        public void setVersion(int version) {
            this.version = version;
        }
        public int getVersion() {
            return version;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }
        public String getLyric() {
            return lyric;
        }

    }
    public void setSgc(boolean sgc) {
        this.sgc = sgc;
    }
    public boolean getSgc() {
        return sgc;
    }

    public void setSfy(boolean sfy) {
        this.sfy = sfy;
    }
    public boolean getSfy() {
        return sfy;
    }

    public void setQfy(boolean qfy) {
        this.qfy = qfy;
    }
    public boolean getQfy() {
        return qfy;
    }

    public void setLrc(Lrc lrc) {
        this.lrc = lrc;
    }
    public Lrc getLrc() {
        return lrc;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

}
