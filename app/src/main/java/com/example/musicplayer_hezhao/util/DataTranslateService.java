package com.example.musicplayer_hezhao.util;

import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.MusicUrl;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.PhoneCheck;
import com.example.musicplayer_hezhao.model.PhoneEmail;
import com.example.musicplayer_hezhao.model.ResponseCheck;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.model.findsongs;
import com.example.musicplayer_hezhao.model.huayu;

import java.util.List;

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
    @GET("top/playlist/highquality")
    Call<Data<Info>>getMusicList();
    @GET("mv/first")
    Call<Data<Num>>getVedioNum(@Query("limit") int limit);
    @GET("?type=mv")
    Call<VedioInformation>getVedio(@Query("id") int MVID);
    @GET("detail")
    Call<Data>getList(@Query("id") String ListID);
    @GET("?type=song")
    Call<MusicUrl>getSong(@Query("id") String SongID);
    @GET("detail")
    Call<MusicInfo>getSongInfo(@Query("ids") String SongID);
    @GET("check")
    Call<PhoneCheck>checkPhone(@Query("phone") String phone);
    @GET("sent?")
    Call<PhoneEmail> sendphone(@Query("phone") String phone);
    //"msg": "验证码错误",
    //"code": 503,
    //"message": "验证码错误"

    //"msg": "该昵称已被占用",
    //"code": 505,
    //"message": "该昵称已被占用"

    //code :200   success
    @GET("cellphone?")
    Call<ResponseCheck>register(@Query("phone") String phone, @Query("password") String password, @Query("captcha") String captcha, @Query("nickname") String nickname);
    @GET("detail")
    Call<HotMusic> queryhotmusic();
    @GET("search?")
    Call<SearchMusicCallback> searchmusic(@Query("keywords") String keywords);
    @GET("list?type=1&area=8&limit=30")
    Call<huayu> findhuayu();
    @GET("song?")
    Call<findsongs> findsongs(@Query("id")int id);
}
