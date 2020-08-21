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

    }

    public class Artists implements Serializable{

        private long id;
        private String name;
        private String img1v1Url;
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

        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }
        public String getImg1v1Url() {
            return img1v1Url;
        }


    }
}
