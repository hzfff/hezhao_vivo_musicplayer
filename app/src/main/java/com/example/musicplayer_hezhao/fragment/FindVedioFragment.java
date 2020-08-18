package com.example.musicplayer_hezhao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.MV_Adapter;
import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;
import com.example.musicplayer_hezhao.model.SongID;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.model.findsongs;
import com.example.musicplayer_hezhao.model.huayu;
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 11120555 on 2020/7/7 17:02
 */
public class FindVedioFragment extends Fragment implements NeteaseCloudMusicApiTool.Callback {
    private static RecyclerView recyclerView;
    private static View view;
    private static NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;
    private static List<VedioInformation> VedioDataList = new ArrayList<>();
    private static List<Num> list = new ArrayList<>();
    private static LinearLayoutManager linearLayoutManager;
    private static MV_Adapter mv_adapter;
    private static Context mContext;
    private static SwipeRefreshLayout swipeRefreshLayout;
    private static ImageView imageView;
    private static ImageView imageView1;
    private static JCVideoPlayerStandard player1;
    private static JCVideoPlayerStandard player2;
    private static JCVideoPlayerStandard player3;
    private static JCVideoPlayerStandard player4;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.findvedio_layout, null);
        try {
            initdata();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    public static Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    mv_adapter = new MV_Adapter(VedioDataList, mContext);
                    recyclerView.setAdapter(mv_adapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                break;
                default:
                    break;
            }
        }
    };
    public void initdata() throws IOException {
        mContext=getContext();
        player1=view.findViewById(R.id.player_list_video1);
        player2=view.findViewById(R.id.player_list_video2);
        player3=view.findViewById(R.id.player_list_video3);
        player4=view.findViewById(R.id.player_list_video4);
        player1.setUp("http://vodkgeyttp8.vod.126.net/cloudmusic/415a/core/913a/e0128243a96e8f1138858a469fa430c6.mp4?wsSecret=1d6940f5f113b508eac2da90e4f67a58&wsTime=1597047104", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        Glide.with(mContext).load("http://p1.music.126.net/C8U0mq74vs0iAaPScArEbQ==/109951165139743756.jpg").into(player1.thumbImageView);
        player2.setUp("http://vodkgeyttp8.vod.126.net/cloudmusic/obj/core/2944169926/8d1d31ca1ff8ed6343d7ae47ca44a469.mp4?wsSecret=ede53a772ed6faff08c94171a83de42b&wsTime=1597047147", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        Glide.with(mContext).load("http://p1.music.126.net/dzvRnAUsIbtSEZkj4mtbfA==/109951165197625602.jpg").into(player2.thumbImageView);
        player3.setUp("http://vodkgeyttp8.vod.126.net/cloudmusic/obj/core/3317433674/4ad8353173cf6cfb26cf12dc3a665bd4.mp4?wsSecret=78d2dbc2907f6abc957c2cb8e96a96b3&wsTime=1597047175", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        Glide.with(mContext).load("http://p1.music.126.net/Og8vSyqmEXJSYTC9yJQ6Dw==/109951165168950480.jpg").into(player3.thumbImageView);
        player4.setUp( "http://vodkgeyttp8.vod.126.net/cloudmusic/obj/core/3428299479/09385d973767722c7abad41984fe0b93.mp4?wsSecret=56e170b241f92e42f7c9f3e6a81e5d53&wsTime=1597047194", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        Glide.with(mContext).load("http://p1.music.126.net/Et9fYZPyYt6oFqjwgrFNFA==/109951165059274985.jpg").into(player4.thumbImageView);
        imageView=view.findViewById(R.id.findvedio_background1);
        imageView1=view.findViewById(R.id.image);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setItemViewCacheSize(3);
        neteaseCloudMusicApiTool = new NeteaseCloudMusicApiTool();
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swipeRefreshLayout=view.findViewById(R.id.swiprefreshlayout);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //这里获取数据的逻辑
                    swipeRefreshLayout.setRefreshing(false);
                }
            });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        neteaseCloudMusicApiTool.getVedioInformation(10, this);
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

    }

    @Override
    public void doResult5(Data<Info> obj) {

    }

    @Override
    public void doResult6(List<Num> obj) {
        list = obj;
        try {
            neteaseCloudMusicApiTool.getVedio(list, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doResult7(List<VedioInformation> obj) {
        VedioDataList = obj;
        Message msg=handler.obtainMessage();
        msg.what=1;
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
}
