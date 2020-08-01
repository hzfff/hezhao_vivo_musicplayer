package com.example.musicplayer_hezhao.util;

import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.VedioInformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 11120555 on 2020/7/30 15:16
 */
public interface DataTranslateService {

    @GET("banner?type=2")
    Call<Data<Info>> getJsonData();
    @GET("/top/playlist/highquality")
    Call<Data<Info>>getMusicList();
    @GET("mv/first")
    Call<Data<Num>>getVedioNum();
    @GET("?type=mv")
    Call<VedioInformation>getVedio(@Query("id") int MVID);
}
