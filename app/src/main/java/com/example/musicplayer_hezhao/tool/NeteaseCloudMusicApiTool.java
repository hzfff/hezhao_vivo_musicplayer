package com.example.musicplayer_hezhao.tool;

import android.util.Log;

import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.MusicUrl;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.PhoneCheck;
import com.example.musicplayer_hezhao.model.ResponseCheck;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;
import com.example.musicplayer_hezhao.model.SongID;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.model.findsongs;
import com.example.musicplayer_hezhao.model.huayu;
import com.example.musicplayer_hezhao.util.DataTranslateService;

import java.io.CharArrayReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 11120555 on 2020/7/31 10:20
 */
public class NeteaseCloudMusicApiTool {
    //获取网易云头部的banner的uri地址

    public void getbanner(Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
                if (callback != null) {
                    callback.doResult4(bannerpic);
                }
            }

        }).start();
    }

    //获取推荐歌单的详细信息
    public void getListInformation(Callback callback) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                Call<Data<Info>> dataCall = api.getMusicList();
                Response<Data<Info>> data = null;
                try {
                    data = dataCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Data<Info> body = data.body();
                if (callback != null) {
                    callback.doResult5(body);
                }
            }
        }).start();
    }

    //获取MV的Num
    public void getVedioInformation(int limit, Callback callback) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                Call<Data<Num>> dataCall = api.getVedioNum(limit);
                Response<Data<Num>> data = null;
                try {
                    data = dataCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Data<Num> body = data.body();
                List<Num> list = body.getData();
                if (callback != null) {
                    callback.doResult6(list);
                }
            }
        }).start();
    }

    //获取MV的信息
    public void getVedio(List<Num> MVID, Callback callback) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.imjad.cn/cloudmusic/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                List<VedioInformation> list = new ArrayList<>();
                Call<VedioInformation> dataCall = null;
                Response<VedioInformation> data = null;
                for (int i = 0; i < MVID.size(); i++) {
                    dataCall = api.getVedio(MVID.get(i).getId());
                    try {
                        data = dataCall.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    VedioInformation body = data.body();
                    list.add(body);
                }
                if (callback != null) {
                    callback.doResult7(list);
                }
            }
        }).start();
    }

    //获取List详情
    public void getSongList(String ListID, Callback callback) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/playlist/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                Call<Data> dataCall = api.getList(ListID);
                Response<Data> data = null;
                try {
                    data = dataCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Data body = data.body();
                List<SongID> list = body.getPrivileges();
                if (callback != null) {
                    callback.doResult1(list);
                }
            }
        }).start();
    }

    //获取音乐Url
    public void getSong(List<SongID> list, Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.imjad.cn/cloudmusic/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                List<String> stringList = new ArrayList<>();
                Response<MusicUrl> data = null;
                Call<MusicUrl> dataCall = null;
                for (int i = 0; i < list.size(); i++) {
                    dataCall = api.getSong(list.get(i).getId());
                    try {
                        data = dataCall.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    MusicUrl body = data.body();
                    String url = body.getData().get(0).getUrl();
                    stringList.add(url);
                }

                if (callback != null) {
                    callback.doResult2(stringList);
                }
            }
        }).start();
    }

    //获取音乐的信息
    public void getMusicInfo(List<SongID> list, Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/song/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                List<MusicInfo> musicInfo = new ArrayList<>(2);
                Response<MusicInfo> data = null;
                Call<MusicInfo> dataCall = null;
                for (int i = 0; i < list.size(); i++) {
                    dataCall = api.getSongInfo(list.get(i).getId());
                    try {
                        data = dataCall.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    MusicInfo body = data.body();
                    musicInfo.add(body);
                }

                if (callback != null) {
                    callback.doResult3(musicInfo);
                }

            }
        }).start();


    }

    public int isExistPhone(String phone) throws IOException {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://music.eleuu.com/cellphone/existence/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        DataTranslateService api = retrofit.create(DataTranslateService.class);
        Call<PhoneCheck> dataCall = api.checkPhone(phone);
        Response<PhoneCheck> data = dataCall.execute();
        PhoneCheck phoneCheck = data.body();
        return phoneCheck.getExist();
    }


    public int register(String phone, String password, String phonecheck, String nickname) throws IOException {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://music.eleuu.com/register/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        DataTranslateService api = retrofit.create(DataTranslateService.class);
        Call<ResponseCheck> dataCall = api.register(phone, password, phonecheck, nickname);
        Response<ResponseCheck> data = dataCall.execute();
        return data.raw().code();
    }

    public void sendcheckword(String phone) throws IOException {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://music.eleuu.com/captcha/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        DataTranslateService api = retrofit.create(DataTranslateService.class);
        api.sendphone(phone).execute();
    }

    public void findhotmusic(Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/search/hot/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                Call<HotMusic> dataCall = api.queryhotmusic();
                Response<HotMusic> data =null;
                try {
                     data = dataCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (callback != null) {
                    callback.doResult8(data.body());
                }

            }
        }).start();
    }

    public void searchmsuci(String musicname,Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                Call<SearchMusicCallback> dataCall = api.searchmusic(musicname);
                Response<SearchMusicCallback> data =null;
                try {
                    data = dataCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (callback != null) {
                    callback.doResult9(data.body());
                }
            }
        }).start();
    }

    public void findHuYu(Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/artist/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                Call<huayu> dataCall = api.findhuayu();
                Response<huayu> data =null;
                try {
                    data = dataCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (callback != null) {
                    callback.doResult10(data.body());
                }
            }
        }).start();
    }
    public void findsinger(Callback callback,int id)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.eleuu.com/artist/top/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataTranslateService api = retrofit.create(DataTranslateService.class);
                Call<findsongs> dataCall = api.findsongs(id);
                Response<findsongs> data =null;
                try {
                    data = dataCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (callback != null) {
                    callback.doResult11(data.body());
                }
            }
        }).start();
    }
    public interface Callback {
        void doResult1(List<SongID> obj);

        void doResult2(List<String> obj);

        void doResult3(List<MusicInfo> obj);

        void doResult4(List<String> obj);

        void doResult5(Data<Info> obj);

        void doResult6(List<Num> obj);

        void doResult7(List<VedioInformation> obj);

        void doResult8(HotMusic obj);

        void doResult9(SearchMusicCallback searchMusicCallback);

        void doResult10(huayu huayu);

        void doResult11(findsongs findsongs);
    }
}
