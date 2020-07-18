package com.example.musicplayer_hezhao.util;

import com.example.musicplayer_hezhao.model.LrcBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/17 15:11
 */
//处理滚动歌词
public class LrcUtil {
    //lrcStr为传入的歌词文件
    public static List<LrcBean> parseStr2List(String lrcStr) {
        List<LrcBean> list = new ArrayList<>();
        //对歌词文件进行替换处理
        String lrcText = lrcStr.replaceAll("&#58;", ":")
                .replaceAll("&#10;", "\n")
                .replaceAll("&#46;", ".")
                .replaceAll("&#32;", " ")
                .replaceAll("&#45;", "-")
                .replaceAll("&#13;", "\r")
                .replaceAll("&#39;", "'");
        String[] split = lrcText.split("\n");
        for (int i = 0; i < split.length; i++) {
            String lrc = split[i];
            if (lrc.contains(".")) {
                //提取出歌词文件中每一行歌词的播放分钟
                String min = lrc.substring(lrc.indexOf("[") + 1, lrc.indexOf("[") + 3);
                //提取出歌词文件中每一行歌词的播放秒
                String seconds = lrc.substring(lrc.indexOf(":") + 1, lrc.indexOf(":") + 3);
                //提取出歌词文件中每一行歌词的毫秒数
                String mills = lrc.substring(lrc.indexOf(".") + 1, lrc.indexOf(".") + 3);
                //计算时间
                long startTime = Long.valueOf(min) * 60 * 1000 + Long.valueOf(seconds) * 1000 + Long.valueOf(mills) * 10;
                String text = lrc.substring(lrc.indexOf("]") + 1);
                //如果当前行没有歌词进行初始化
                if (text == null || "".equals(text)) {
                    text = "raw";
                }
                LrcBean lrcBean = new LrcBean();
                lrcBean.setStart(startTime);
                lrcBean.setLrc(text);
                list.add(lrcBean);
                if (list.size() > 1) {
                    list.get(list.size() - 2).setEnd(startTime);
                }
                if (i == split.length - 1) {
                    list.get(list.size() - 1).setEnd(startTime + 100000);
                }
            }
        }
        return list;
    }
}
