package com.example.musicplayer_hezhao;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.adapter.FindMusicAdapter;
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
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/20 16:26
 */
public class ShowListActivity  extends BaseActivity implements NeteaseCloudMusicApiTool.Callback {
    private static RecyclerView recyclerView;
    private Toolbar toolbar;
    private static GridLayoutManager gridLayoutManager;
    private static NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;
    private static Data<Info>list_sings=new Data<>();
    private static FindMusicAdapter adapter;
    private static AVLoadingIndicatorView avLoadingIndicatorView;
    private static Context mContext;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.showlistlayout);
        initview();
        initdata();
    }
    public void initview(){
        avLoadingIndicatorView=findViewById(R.id.avi);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        recyclerView=findViewById(R.id.recyclerview);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("歌单");
        setSupportActionBar(toolbar);
        mContext=getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        neteaseCloudMusicApiTool=new NeteaseCloudMusicApiTool();
        getSupportActionBar().setHomeButtonEnabled(true);
        gridLayoutManager=new GridLayoutManager(this,2);
        toolbar.setTitleTextAppearance(getApplicationContext(),R.style.MyEditText);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void initdata(){
        try {
            neteaseCloudMusicApiTool.getListInformation(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        list_sings=obj;
        Message msg=handler.obtainMessage();
        msg.what=1;
        handler.sendMessage(msg);
    }

    @Override
    public void doResult6(List<Num> obj) {

    }

    @Override
    public void doResult7(List<VedioInformation> obj) {

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

  public static Handler handler=new Handler(){
      @Override
      public void handleMessage(Message msg) {
          super.handleMessage(msg);
          switch (msg.what)
          {
              case 1:
                  adapter=new FindMusicAdapter(list_sings.getPlaylists(),mContext,true);
                  recyclerView.setAdapter(adapter);
                  recyclerView.setLayoutManager(gridLayoutManager);
                  avLoadingIndicatorView.setVisibility(View.GONE);
                  break;
              default:
                  break;
          }
      }
  };
}
