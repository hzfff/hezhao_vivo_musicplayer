package com.example.musicplayer_hezhao.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.ShowListActivity;
import com.example.musicplayer_hezhao.ShowSingerActivity;
import com.example.musicplayer_hezhao.ShowVedioActivity;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.adapter.FindMusicAdapter;
//import com.example.musicplayer_hezhao.adapter.MV_Adapter;
import com.example.musicplayer_hezhao.adapter.MV_Adapter;
import com.example.musicplayer_hezhao.adapter.MhealthAdater;
import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;
import com.example.musicplayer_hezhao.model.SongID;
import com.example.musicplayer_hezhao.model.Vedio;
import com.example.musicplayer_hezhao.model.VedioData;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.model.findsongs;
import com.example.musicplayer_hezhao.model.huayu;
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;
import com.example.musicplayer_hezhao.util.DataTranslateService;
import com.example.musicplayer_hezhao.util.RoundImageView;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import jp.wasabeef.glide.transformations.BlurTransformation;
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
public class FindMusicFragment extends BaseFragment implements NeteaseCloudMusicApiTool.Callback {
    private View mView;
    private ViewPager viewPager;
    private List<RoundImageView> mViewList = new ArrayList<>();
    private MhealthAdater adapter;
    private int currentItem = 0;
    private boolean isScroll = false;
    private List<String> pic_uri = new ArrayList<>();//顶部banner图片；
    private List<VedioInformation> VedioDataList = new ArrayList<>();
    private Data<Info> list_sings = new Data<>();
    private NeteaseCloudMusicApiTool CloudMusicTool = new NeteaseCloudMusicApiTool();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private FindMusicAdapter findMusicAdapter;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager MV_LinearLayoutManager;
    private RecyclerView MV_Recyclerview;
    private MV_Adapter mv_adapter;
    private ImageView image;
    private ImageView singer;
    private ImageView songlist;
    private ImageView mvlist;
    private int num = 1;
    private NeteaseCloudMusicApiTool.Callback callback;
    private boolean index = true;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.findmusic_layout, null);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        init();
        onclick();
        return mView;
    }

    public void init() {
        avLoadingIndicatorView = mView.findViewById(R.id.avi);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        CloudMusicTool.getbanner(this);
        singer = mView.findViewById(R.id.image1);
        mvlist = mView.findViewById(R.id.image2);
        songlist = mView.findViewById(R.id.image3);
        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        image = mView.findViewById(R.id.image);
        swipeRefreshLayout = mView.findViewById(R.id.swiprefreshlayout);
        MV_Recyclerview = mView.findViewById(R.id.recyclerview_second);
        recyclerView = mView.findViewById(R.id.recyclerview);
        initpre();
        Glide.with(this).load(R.mipmap.pic5).
                apply(RequestOptions.
                        bitmapTransform(new BlurTransformation(18, 3))).into(image);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    sendMoreRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
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

    public void onclick() {
        singer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowSingerActivity.class);
                startActivity(intent);
            }
        });
        mvlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowVedioActivity.class);
                startActivity(intent);
            }
        });
        songlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initpre() {
        pic_uri.add("http://p1.music.126.net/6lM5D0NG6GR3_3FcbMI_Bg==/109951165201392548.jpg");
        pic_uri.add("http://p1.music.126.net/6Ux98l5OB-P66XPuUavO2g==/109951165198121518.jpg");
        pic_uri.add("http://p1.music.126.net/3H6aalFW2CZPJlpMrcR15g==/109951165202750835.jpg");
        RoundImageView view1 = new RoundImageView(getContext());
        RoundImageView view2 = new RoundImageView(getContext());
        RoundImageView view3 = new RoundImageView(getContext());
        Glide.with(getActivity()).load(pic_uri.get(0)).into(view1);
        Glide.with(getActivity()).load(pic_uri.get(1)).into(view2);
        Glide.with(getActivity()).load(pic_uri.get(2)).into(view3);
        view1.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
        view2.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
        view3.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        adapter = new MhealthAdater(mViewList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem((int) (Integer.MAX_VALUE * 0.5));
        linearLayoutManager = new LinearLayoutManager(getContext());
        MV_LinearLayoutManager = new LinearLayoutManager(getContext());
        MV_LinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // MV_Recyclerview.addOnScrollListener(monScrollListener)
        //默认显示的图片
        currentItem = (int) (Integer.MAX_VALUE * 0.5);
        //定时更新UI
        mhandler.sendEmptyMessageDelayed(0, 2000);
        callback = this;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    CloudMusicTool.getVedioInformation(50, callback);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        task.run();
    }


    public void sendMoreRequest() throws IOException {
        if (VedioDataList.size() < 10) {

        } else {
            if (num == 10) {
                num = 0;
            }
            List<VedioInformation> list = new ArrayList<>();
            for (int i = num * 5; i < num * 5 + 5; i++) {
                list.add(VedioDataList.get(i));
            }
            MV_Adapter adapter2 = new MV_Adapter(list, true, getContext());
            MV_Recyclerview.setAdapter(adapter2);
            num++;
        }
    }

    Handler handlers = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Bundle bundle = msg.getData();
                List<VedioInformation> list = (List<VedioInformation>) bundle.getSerializable("list");
                mv_adapter.addList(list);
                if (list == null) {
                    mv_adapter.setIsLoadMore();
                }
            }

        }
    };

    public void initview() {
        //设置适配器
        MhealthAdater adapter1 = (MhealthAdater) viewPager.getAdapter();
        MV_Adapter adapter2 = new MV_Adapter(VedioDataList, true, getContext());
        MV_Recyclerview.setAdapter(adapter2);
        MV_Recyclerview.setLayoutManager(MV_LinearLayoutManager);
        findMusicAdapter = new FindMusicAdapter(list_sings.getPlaylists(), getContext(),false);
        recyclerView.setAdapter(findMusicAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
        avLoadingIndicatorView.setVisibility(View.GONE);
        findMusicAdapter.setOnItemClickListener(new FindMusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void doResult1(List<SongID> obj) {

    }

    @Override
    public void doResult2(List<String> obj) {

    }

    @Override
    public void doResult3(List<MusicInfo> obj) {

    }

    @Override
    public void doResult4(List<String> obj) {
        pic_uri.clear();
        pic_uri.addAll(obj);
        try {
            //intitviewpager();
            CloudMusicTool.getListInformation(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doResult5(Data<Info> obj) {
        Data<Info> li = obj;
        list_sings.setPlaylists(li.getPlaylists());
        try {
            CloudMusicTool.getVedioInformation(5, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doResult6(List<Num> obj) {
        List<Num> list = obj;
        List<Num> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i));
        }
        try {
            CloudMusicTool.getVedio(list1, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doResult7(List<VedioInformation> obj) {
        VedioDataList.clear();
        VedioDataList.addAll(obj);
        if (index) {
            index = false;
            Message msg = new Message();
            msg.what = 2;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void doResult8(HotMusic obj) {

    }

    @Override
    public void doResult9(SearchMusicCallback searchMusicCallback) {

    }

    @Override
    public void doResult10(huayu huayu) {

    }

    @Override
    public void doResult11(findsongs findsongs) {

    }

    @Override
    public void doResult12(List<String> lyrclist) {

    }

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
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    break;
                case 2:
                    for (int i = 0; i < pic_uri.size(); i++) {
                        RoundImageView view1 = new RoundImageView(getContext());
                        Glide.with(getActivity()).load(pic_uri.get(i)).into(view1);
                        view1.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
                        mViewList.add(view1);
                    }
                    initview();
                    break;
                default:
                    break;
            }
        }
    };
}
