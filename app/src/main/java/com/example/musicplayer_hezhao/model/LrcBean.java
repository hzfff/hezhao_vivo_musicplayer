package com.example.musicplayer_hezhao.model;

/**
 * Created by 11120555 on 2020/7/17 15:09
 */
public class LrcBean  {
    private String lrc;
    private long start;
    private long end;

    public LrcBean() {
    }

    public LrcBean(String lrc, long start, long end) {
        this.lrc = lrc;
        this.start = start;
        this.end = end;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
