package com.example.musicplayer_hezhao.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.musicplayer_hezhao.ShowSingerActivity;
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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class FindMusicFragment extends Fragment implements NeteaseCloudMusicApiTool.Callback {
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
        CloudMusicTool.getbanner(this);
        singer=mView.findViewById(R.id.image1);
        mvlist=mView.findViewById(R.id.image2);
        songlist=mView.findViewById(R.id.image3);
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
public void onclick(){
    singer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         Intent  intent =new Intent(getActivity(), ShowSingerActivity.class);
         startActivity(intent);
        }
    });
    mvlist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    songlist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

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
        VedioInformation vedioInformation = new VedioInformation();
        Vedio vedio = new Vedio();
        vedio.setArtistName("言承旭");
        vedio.setCover("http://p1.music.126.net/6XBdGum29eF_7Nux-V2r4A==/109951165188095856.jpg");
        vedio.setName("我好喜欢你");
        vedio.setPublishTime("2020-08-03");
        vedio.setPlayCount(74444);
        vedio.setDuration(218000);
        HashMap<String,String>map=new HashMap<>();
        map.put("240","http://vodkgeyttp8.vod.126.net/cloudmusic/MjQ3NDQ3MjUw/89a6a279dc2acfcd068b45ce72b1f560/bf2750483ed02d4c6263dffefa5959d7.mp4?wsSecret=d63da6681eb0e3a1a18d8be7221e3f28&wsTime=1596598128");
        map.put("480", "http://vodkgeyttp8.vod.126.net/cloudmusic/MjQ3NDQ3MjUw/89a6a279dc2acfcd068b45ce72b1f560/533e4183a709699d566180ed0cd9abe9.mp4?wsSecret=173f611f6d408720f70e5dafeb56fd1e&wsTime=1596598128");
        vedio.setBrs(map);
        vedioInformation.setCode(200);
        vedioInformation.setData(vedio);
        VedioDataList.add(vedioInformation);
        List<Info> list = new ArrayList<>();
        Info info = new Info();
        info.setCoverImgUrl("http://p3.music.126.net/IZaKdskHvigujL5GZW-QtQ==/1377688069888740.jpg");
        info.setName("电影中的钢琴曲｜最会讲故事²");
        info.setPlayCount(6210047);
        Info info1 = new Info();
        info1.setCoverImgUrl("http://p3.music.126.net/D-7Dhh8trQwSwlqG7HuXYg==/109951163017662871.jpg");
        info1.setName("欧美｜我们听过的那首歌，原来采样了它");
        info1.setPlayCount(4326047);
        Info info2 = new Info();
        info2.setCoverImgUrl("http://p4.music.126.net/EuVP2n4KLtz4vXX5FBKslw==/109951165141335283.jpg");
        info2.setName("琉球暖风伴清酒，日本岛呗艺术家大赏");
        info2.setPlayCount(263147);
        Info info3 = new Info();
        info3.setCoverImgUrl("http://p4.music.126.net/NDdtSac66rpsF_jMBh1JMQ==/109951164929306650.jpg");
        info3.setName("美国乡村乐图鉴与简史");
        info3.setPlayCount(4350047);
        list.add(info);
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list_sings.setPlaylists(list);
        adapter = new MhealthAdater(mViewList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem((int) (Integer.MAX_VALUE * 0.5));
        mv_adapter = new MV_Adapter(VedioDataList, getContext());
        findMusicAdapter = new FindMusicAdapter(list_sings.getPlaylists(), getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        MV_LinearLayoutManager = new LinearLayoutManager(getContext());
        MV_LinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        MV_Recyclerview.setAdapter(mv_adapter);
        MV_Recyclerview.setLayoutManager(MV_LinearLayoutManager);
        // MV_Recyclerview.addOnScrollListener(monScrollListener);
        recyclerView.setAdapter(findMusicAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        //默认显示的图片
        currentItem = (int) (Integer.MAX_VALUE * 0.5);
        //定时更新UI
        mhandler.sendEmptyMessageDelayed(0, 2000);
    }


    public void sendMoreRequest() throws IOException {
        num++;
        if(num==5)
        {
            num=1;
        }
        CloudMusicTool.getVedioInformation(10* num, this);
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
        MV_Adapter adapter2 = (MV_Adapter) MV_Recyclerview.getAdapter();
        FindMusicAdapter adapter = new FindMusicAdapter(list_sings.getPlaylists(), getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
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
            CloudMusicTool.getVedioInformation(10 * num, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doResult6(List<Num> obj) {
        List<Num> list = obj;
        List<Num> list1 = new ArrayList<>();
        for (int i = 10*(num-1); i <10*(num-1)+10 ; i++) {
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
        Message msg = new Message();
        msg.what = 2;
        handler.sendMessage(msg);
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
                    for (int i = 0; i < pic_uri.size(); i++) {
                        RoundImageView view1 = new RoundImageView(getContext());
                        Glide.with(getActivity()).load(pic_uri.get(i)).into(view1);
                        view1.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
                        mViewList.add(view1);
                    }
                    break;
                case 2:
                    initview();
                    break;
                default:
                    break;
            }
        }
    };
}
