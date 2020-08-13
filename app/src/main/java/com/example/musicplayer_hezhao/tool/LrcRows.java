package com.example.musicplayer_hezhao.tool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * Created by 11120555 on 2020/8/13 11:03
 */
public class LrcRows {

    private List<LrcRow> list = new ArrayList<LrcRow>();//存放每行歌词的集合
//获取list集合的方法，将每行的歌词添加到list集合中

    public List<LrcRow> BuildList(Context context,String lyric) {

        try {
            InputStream inputStream = new ByteArrayInputStream(lyric.getBytes("UTF-8"));
//将字节输入流转化为字符流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                LrcRow lrcRow = new LrcRow();//创建每行封装歌词的对象
//获取新的解析封装好的歌词 添加到集合中
                LrcRow lrcRow2 = lrcRow.getRow(line);

                if (lrcRow2 != null) {
                    list.add(lrcRow2);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
// TODO: handle exception
        }
        return list;

    }

}
