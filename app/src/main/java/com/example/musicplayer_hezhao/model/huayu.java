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
        private long img1v1Id;
        private int topicPerson;
        private int musicSize;
        private int albumSize;
        private String briefDesc;
        private List<String> alias;
        private long picId;
        private boolean followed;
        private String img1v1Url;
        private String picUrl;
        private String trans;
        private String name;
        private int id;
        private long accountId;
        private String picId_str;
        private String img1v1Id_str;
        public void setImg1v1Id(long img1v1Id) {
            this.img1v1Id = img1v1Id;
        }
        public long getImg1v1Id() {
            return img1v1Id;
        }

        public void setTopicPerson(int topicPerson) {
            this.topicPerson = topicPerson;
        }
        public int getTopicPerson() {
            return topicPerson;
        }

        public void setMusicSize(int musicSize) {
            this.musicSize = musicSize;
        }
        public int getMusicSize() {
            return musicSize;
        }

        public void setAlbumSize(int albumSize) {
            this.albumSize = albumSize;
        }
        public int getAlbumSize() {
            return albumSize;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }
        public String getBriefDesc() {
            return briefDesc;
        }

        public void setAlias(List<String> alias) {
            this.alias = alias;
        }
        public List<String> getAlias() {
            return alias;
        }

        public void setPicId(long picId) {
            this.picId = picId;
        }
        public long getPicId() {
            return picId;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }
        public boolean getFollowed() {
            return followed;
        }

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

        public void setTrans(String trans) {
            this.trans = trans;
        }
        public String getTrans() {
            return trans;
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

        public void setAccountId(long accountId) {
            this.accountId = accountId;
        }
        public long getAccountId() {
            return accountId;
        }

        public void setPicId_str(String picId_str) {
            this.picId_str = picId_str;
        }
        public String getPicId_str() {
            return picId_str;
        }

        public void setImg1v1Id_str(String img1v1Id_str) {
            this.img1v1Id_str = img1v1Id_str;
        }
        public String getImg1v1Id_str() {
            return img1v1Id_str;
        }

    }
}
