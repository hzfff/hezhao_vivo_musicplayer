package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/5 16:34
 */
public class HotMusic  implements Serializable {

    private int code;
    //DataMusic  music information
    private List<DataMusic> data;
    private String message;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setData(List<DataMusic> data) {
        this.data = data;
    }
    public List<DataMusic> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public class DataMusic{
        private String searchWord;
        private long score;
        private String content;
        private int source;
        private int iconType;
        private String iconUrl;
        private String url;
        private String alg;
        public void setSearchWord(String searchWord) {
            this.searchWord = searchWord;
        }
        public String getSearchWord() {
            return searchWord;
        }

        public void setScore(long score) {
            this.score = score;
        }
        public long getScore() {
            return score;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setSource(int source) {
            this.source = source;
        }
        public int getSource() {
            return source;
        }

        public void setIconType(int iconType) {
            this.iconType = iconType;
        }
        public int getIconType() {
            return iconType;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }
        public String getIconUrl() {
            return iconUrl;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }
        public String getAlg() {
            return alg;
        }

    }
}
