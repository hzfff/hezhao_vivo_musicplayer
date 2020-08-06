package com.example.musicplayer_hezhao;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.adapter.HistoryMusicAdapter;
import com.example.musicplayer_hezhao.adapter.HotMusicAdapter;
import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.HistoryModel;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.HotMusicModel;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;
import com.example.musicplayer_hezhao.model.SongID;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Created by 11120555 on 2020/7/8 14:15
 */
public class SearchActivity extends AppCompatActivity  implements NeteaseCloudMusicApiTool.Callback {
    private static EditText meditText;
    private Toolbar mtoolbar;
    private ImageView mimageView;
    private TextView search_btn;
    private List<HistoryModel> historyModelList=new ArrayList<>();
    private List<HotMusicModel>hotMusicModelList=new ArrayList<>();
    private RecyclerView  historyrecyclerview;
    private static RecyclerView  hotMusicrecyclerview;
    private HistoryMusicAdapter historyMusicAdapter;
    private static HotMusicAdapter hotMusicAdapter;
    private static NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;
    private static HotMusic hotMusic;
    private  NeteaseCloudMusicApiTool.Callback callback=this;
    private SearchMusicCallback SearchMusicCallback;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.search_music);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initview();
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Glide.with(this).load(R.mipmap.pic5).
                apply(RequestOptions.
                        bitmapTransform(new BlurTransformation(18, 3))).into(mimageView);
        neteaseCloudMusicApiTool=new NeteaseCloudMusicApiTool();
        neteaseCloudMusicApiTool.findhotmusic(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        historyrecyclerview.setLayoutManager(linearLayoutManager);
        historyMusicAdapter=new HistoryMusicAdapter(historyModelList);
        historyrecyclerview.setAdapter(historyMusicAdapter);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        hotMusicrecyclerview.setLayoutManager(linearLayoutManager1);
    }
    public void initview() {
        search_btn=findViewById(R.id.search_music);
        mimageView=findViewById(R.id.background_pic);
        mtoolbar = findViewById(R.id.search_toolbar);
        meditText = findViewById(R.id.input_music);
        historyrecyclerview=findViewById(R.id.history_search);
        hotMusicrecyclerview=findViewById(R.id.hot_music);
        setSupportActionBar(mtoolbar);
        //search  music
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String musicname=meditText.getText().toString().trim();
                if(musicname!=null&&musicname.length()>0)
                {
                    neteaseCloudMusicApiTool.searchmsuci(musicname,callback);
                }
            }
        });
        //setDisplayHomeAsUpEnabled设置为true以后，会显示toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        for(int i=0;i<5; i++){
            HistoryModel model=new HistoryModel("心如止水");
            HistoryModel mode2=new HistoryModel("我这一生");
            HistoryModel mode3=new HistoryModel("一路向北");
            HistoryModel mode4=new HistoryModel("后来遇见她");
            historyModelList.add(model);
            historyModelList.add(mode2);
            historyModelList.add(mode3);
            historyModelList.add(mode4);
        }

    }

   public  static Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what)
           {
               case 1:
                   hotMusicAdapter=new HotMusicAdapter(hotMusic);
                   hotMusicrecyclerview.setAdapter(hotMusicAdapter);
                   break;
               default:
                   break;
           }
           hotMusicAdapter.setOnItemClickListener(new HotMusicAdapter.OnItemClickListener() {
               @Override
               public void onItemClick(View view, int position) {
                   String searchsong=hotMusic.getData().get(position).getSearchWord();
                   meditText.setText(searchsong);
                   meditText.refreshDrawableState();
               }
           });
       }
   };
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

    }

    @Override
    public void doResult7(List<VedioInformation> obj) {

    }

    @Override
    public void doResult8(HotMusic obj) {
        hotMusic=obj;
        Message msg = handler.obtainMessage();//创建消息对象
        msg.what=1;
       handler.sendMessage(msg);
    }

    @Override
    public void doResult9(SearchMusicCallback searchMusicCallback) {
        SearchMusicCallback=searchMusicCallback;
        Intent  intent=new Intent(this,SearchResultActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("SearchMusicCallback",SearchMusicCallback);
        String SongName=meditText.getText().toString().trim();
        bundle.putString("SongName",SongName);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
