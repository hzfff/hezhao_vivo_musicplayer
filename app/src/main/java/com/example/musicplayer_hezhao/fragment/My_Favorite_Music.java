package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.downmusicrecycleradapter;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 15:07
 */
public class My_Favorite_Music  extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private downmusicrecycleradapter  adapter;
    private List<String> list_name=new ArrayList<>();
    private List<String>list_singer=new ArrayList<>();
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.my_favorite_music);
        //setHasOptionsMenu(true);
        initview();
        initData();

    }
    public void initview(){
      toolbar=findViewById(R.id.toolbar);
      recyclerView=findViewById(R.id.recyclerview);
      adapter=new downmusicrecycleradapter(list_name,list_singer,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new downmusicrecycleradapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                ShowDialog bottomDialogFr = new ShowDialog();
                bottomDialogFr.show(getSupportFragmentManager(), "DF");
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void initData() {
        for(int i=0;i<14;i++){
            list_name.add("棋子");
        }
        for(int i=0;i<14;i++){
            list_singer.add("王菲");
        }

    }

}
