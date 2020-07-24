package com.example.musicplayer_hezhao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.adapter.MusicShowAdapter;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.model.MusicListModel;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Created by 11120555 on 2020/7/23 14:05
 */
//用于展示每个歌单的详情
public class ShowMusicActivity  extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView list_name;
    private TextView name;
    private Intent intent;
    private MusicListModel musicListModel;
    private String MusicListName;
    private List<Music> MusicList;
    private RecyclerView recyclerView;
    private MusicShowAdapter adapter;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.showmusiclayout);
        initview();
    }
    public void initview(){
        intent=getIntent();
        Bundle bundle = intent.getExtras();
        musicListModel = (MusicListModel) bundle.getSerializable("musiclistmode");
        MusicListName=musicListModel.getMusicListName();
        MusicList=musicListModel.getMusicName();
        imageView=findViewById(R.id.image1);
        list_name=findViewById(R.id.list_name);
        name=findViewById(R.id.name);
        toolbar=findViewById(R.id.title_toolbar);
        recyclerView=findViewById(R.id.musiclist_body);
        adapter=new MusicShowAdapter(MusicList,getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        list_name.setText(MusicListName);
        Random random=new Random();
        //从歌单中随机选一张照片作为封面
        if(MusicList.size()>1){imageView.setImageBitmap(Util.CreateBitmap(getContentResolver(), Uri.parse(MusicList.get(random.nextInt(MusicList.size()-1)).getAlbumUri())));;}
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new MusicShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int index,View view, int position) {
                //点击右侧图片跳出底部选项歌曲详细信息，删除或者添加到喜欢的音乐选项
                if(index==2) {
                    ShowDialog bottomDialogFr = new ShowDialog();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MusicList", (Serializable) MusicList.get(position));
                    bottomDialogFr.setArguments(bundle);
                    bottomDialogFr.show(getSupportFragmentManager(), "hezhao");
                }else if(index==1){
                    //点击歌名跳转到播放页面
                    Intent intent0 = new Intent(getApplicationContext(), PlayMusicActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MusicList", (Serializable) MusicList);
                    intent0.putExtra("position", position);
                    intent0.putExtras(bundle);
                    startActivity(intent0);
                }
            }
        });
    }
}
