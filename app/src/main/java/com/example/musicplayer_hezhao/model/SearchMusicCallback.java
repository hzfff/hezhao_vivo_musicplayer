package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/5 17:56
 */
public class SearchMusicCallback  implements Serializable {
    private Result result;
    private int code;
    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public class Result  implements Serializable {

        private List<Songs> songs;
        private boolean hasMore;
        private int songCount;
        public void setSongs(List<Songs> songs) {
            this.songs = songs;
        }
        public List<Songs> getSongs() {
            return songs;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }
        public boolean getHasMore() {
            return hasMore;
        }

        public void setSongCount(int songCount) {
            this.songCount = songCount;
        }
        public int getSongCount() {
            return songCount;
        }

    }
    public class Songs implements Serializable{

        private long id;
        private String name;
        private List<Artists> artists;
        private Album album;
        private long duration;
        private long copyrightId;
        private int status;
        private List<String> alias;
        private int rtype;
        private int ftype;
        private int mvid;
        private int fee;
        private String rUrl;
        private int mark;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setArtists(List<Artists> artists) {
            this.artists = artists;
        }
        public List<Artists> getArtists() {
            return artists;
        }

        public void setAlbum(Album album) {
            this.album = album;
        }
        public Album getAlbum() {
            return album;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }
        public long getDuration() {
            return duration;
        }

        public void setCopyrightId(long copyrightId) {
            this.copyrightId = copyrightId;
        }
        public long getCopyrightId() {
            return copyrightId;
        }

        public void setStatus(int status) {
            this.status = status;
        }
        public int getStatus() {
            return status;
        }

        public void setAlias(List<String> alias) {
            this.alias = alias;
        }
        public List<String> getAlias() {
            return alias;
        }

        public void setRtype(int rtype) {
            this.rtype = rtype;
        }
        public int getRtype() {
            return rtype;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }
        public int getFtype() {
            return ftype;
        }

        public void setMvid(int mvid) {
            this.mvid = mvid;
        }
        public int getMvid() {
            return mvid;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }
        public int getFee() {
            return fee;
        }

        public void setRUrl(String rUrl) {
            this.rUrl = rUrl;
        }
        public String getRUrl() {
            return rUrl;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }
        public int getMark() {
            return mark;
        }

    }
    public class Album implements Serializable{

        private long id;
        private String name;
        private Artist artist;
        private long publishTime;
        private int size;
        private long copyrightId;
        private int status;
        private long picId;
        private int mark;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }
        public Artist getArtist() {
            return artist;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }
        public long getPublishTime() {
            return publishTime;
        }

        public void setSize(int size) {
            this.size = size;
        }
        public int getSize() {
            return size;
        }

        public void setCopyrightId(long copyrightId) {
            this.copyrightId = copyrightId;
        }
        public long getCopyrightId() {
            return copyrightId;
        }

        public void setStatus(int status) {
            this.status = status;
        }
        public int getStatus() {
            return status;
        }

        public void setPicId(long picId) {
            this.picId = picId;
        }
        public long getPicId() {
            return picId;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }
        public int getMark() {
            return mark;
        }

    }
    public class Artist  implements Serializable {

        private int id;
        private String name;
        private String picUrl;
        private List<String> alias;
        private int albumSize;
        private int picId;
        private String img1v1Url;
        private int img1v1;
        private String trans;
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

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
        public String getPicUrl() {
            return picUrl;
        }

        public void setAlias(List<String> alias) {
            this.alias = alias;
        }
        public List<String> getAlias() {
            return alias;
        }

        public void setAlbumSize(int albumSize) {
            this.albumSize = albumSize;
        }
        public int getAlbumSize() {
            return albumSize;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }
        public int getPicId() {
            return picId;
        }

        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }
        public String getImg1v1Url() {
            return img1v1Url;
        }

        public void setImg1v1(int img1v1) {
            this.img1v1 = img1v1;
        }
        public int getImg1v1() {
            return img1v1;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }
        public String getTrans() {
            return trans;
        }

    }
    public class Artists implements Serializable{

        private long id;
        private String name;
        private String picUrl;
        private List<String> alias;
        private int albumSize;
        private int picId;
        private String img1v1Url;
        private int img1v1;
        private String trans;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
        public String getPicUrl() {
            return picUrl;
        }

        public void setAlias(List<String> alias) {
            this.alias = alias;
        }
        public List<String> getAlias() {
            return alias;
        }

        public void setAlbumSize(int albumSize) {
            this.albumSize = albumSize;
        }
        public int getAlbumSize() {
            return albumSize;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }
        public int getPicId() {
            return picId;
        }

        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }
        public String getImg1v1Url() {
            return img1v1Url;
        }

        public void setImg1v1(int img1v1) {
            this.img1v1 = img1v1;
        }
        public int getImg1v1() {
            return img1v1;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }
        public String getTrans() {
            return trans;
        }

    }
}
