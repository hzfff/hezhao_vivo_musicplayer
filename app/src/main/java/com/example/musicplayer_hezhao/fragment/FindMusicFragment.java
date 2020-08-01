package com.example.musicplayer_hezhao.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.adapter.FindMusicAdapter;
import com.example.musicplayer_hezhao.adapter.MV_Adapter;
import com.example.musicplayer_hezhao.adapter.MhealthAdater;
import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.VedioData;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;
import com.example.musicplayer_hezhao.util.DataTranslateService;
import com.example.musicplayer_hezhao.util.RoundImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 11120555 on 2020/7/7 17:02
 */
//播放器发现界面
public class FindMusicFragment extends Fragment {
    private View mView;
    private ViewPager viewPager;
    private List<RoundImageView> mViewList = new ArrayList<>();
    private MhealthAdater adapter;
    private int currentItem = 0;
    private boolean isScroll = false;
    private List<String> pic_uri = new ArrayList<>();//顶部banner图片；
    private List<VedioInformation>VedioDataList=new ArrayList<>();
    private Data<Info> list_sings=new Data<>();
    private NeteaseCloudMusicApiTool CloudMusicTool = new NeteaseCloudMusicApiTool();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private FindMusicAdapter findMusicAdapter;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager MV_LinearLayoutManager;
    private RecyclerView MV_Recyclerview;
    private MV_Adapter mv_adapter;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mhandler.sendEmptyMessageDelayed(0, 2000);
            if (isScroll) {
            } else {
                currentItem = currentItem + 1;
                viewPager.setCurrentItem(currentItem);
            }

        }
    };


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.findmusic_layout, null);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            intitviewpager();//初始化banner,推荐歌单,推荐MV
        } catch (IOException e) {
            e.printStackTrace();
        }
        initview();
        return mView;
    }

    public void intitviewpager() throws IOException {
        pic_uri = CloudMusicTool.getbanner();
        list_sings=CloudMusicTool.getListInformation();
        VedioDataList=CloudMusicTool.getVedioInformation();
        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        for (int i = 0; i < 10; i++) {
            RoundImageView view1 = new RoundImageView(getContext());
            Glide.with(this).load(pic_uri.get(i)).into(view1);
            view1.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
            mViewList.add(view1);
        }
    }

    public void initview() {
        Map<String,String> url= (Map<String, String>) VedioDataList.get(0).getData().getBrs();
        String result=url.get("240");
         result=url.get("240");
        swipeRefreshLayout = mView.findViewById(R.id.swiprefreshlayout);
        //设置适配器
        adapter = new MhealthAdater(mViewList);
        viewPager.setAdapter(adapter);
        mv_adapter=new MV_Adapter(VedioDataList,getContext());
        MV_Recyclerview=mView.findViewById(R.id.recyclerview_second);
        recyclerView = mView.findViewById(R.id.recyclerview);
        findMusicAdapter=new FindMusicAdapter(list_sings.getPlaylists(),getContext());
        linearLayoutManager=new LinearLayoutManager(getContext());
        MV_LinearLayoutManager=new LinearLayoutManager(getContext());
        MV_LinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(findMusicAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        //默认显示的图片
        currentItem = (int) (Integer.MAX_VALUE * 0.5);
        viewPager.setCurrentItem((int) (Integer.MAX_VALUE * 0.5));
        //定时更新UI
        mhandler.sendEmptyMessageDelayed(0, 2000);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里获取数据的逻辑
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                //  changeIcon(arg0);
                currentItem = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            //处于滑动状态时不轮播图片
            @Override
            public void onPageScrollStateChanged(int arg0) {
                if (arg0 == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
            }
        });

    }

}
