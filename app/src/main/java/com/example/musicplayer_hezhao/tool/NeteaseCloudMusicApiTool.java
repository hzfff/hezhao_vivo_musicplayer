package com.example.musicplayer_hezhao.tool;

import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.util.DataTranslateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 11120555 on 2020/7/31 10:20
 */
public class NeteaseCloudMusicApiTool {
    //获取网易云头部的banner的uri地址
    public List<String> getbanner() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://music.eleuu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataTranslateService api = retrofit.create(DataTranslateService.class);
        Call<Data<Info>> dataCall = api.getJsonData();
        List<String> bannerpic = new ArrayList<>();
        try {
            Response<Data<Info>> data = dataCall.execute();
            Data<Info> body = data.body();
            List<Info> infoList = body.getBanners();
            for (int i = 0; i < infoList.size(); i++) {
                bannerpic.add(infoList.get(i).getPic());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bannerpic;
    }

    //获取推荐歌单的详细信息
    public Data<Info> getListInformation() throws IOException {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://music.eleuu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataTranslateService api = retrofit.create(DataTranslateService.class);
        Call<Data<Info>> dataCall = api.getMusicList();
        Response<Data<Info>> data = dataCall.execute();
        Data<Info> body = data.body();
        return body;
    }
//获取MV的Num
    public List<VedioInformation>getVedioInformation() throws IOException {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://music.eleuu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataTranslateService api = retrofit.create(DataTranslateService.class);
        Call<Data<Num>> dataCall = api.getVedioNum();
        Response<Data<Num>> data = dataCall.execute();
        Data<Num> body = data.body();
        List<Num>list=body.getData();
        List<VedioInformation>result=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            result.add(getVedio(list.get(i).getId()));
        }
        return  result;
    }
    //获取MV的信息
    public VedioInformation getVedio(int MVID) throws IOException {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imjad.cn/cloudmusic/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataTranslateService api = retrofit.create(DataTranslateService.class);
        Call<VedioInformation> dataCall = api.getVedio(MVID);
        Response<VedioInformation> data = dataCall.execute();
        VedioInformation body = data.body();
        return body;
    }
}
