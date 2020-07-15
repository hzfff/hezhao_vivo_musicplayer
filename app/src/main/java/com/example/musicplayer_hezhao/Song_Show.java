package com.example.musicplayer_hezhao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.adapter.downmusicrecycleradapter;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.nio.BufferUnderflowException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 16:17
 */
public class Song_Show  extends AppCompatActivity {
    private List<String> song_list;
    private List<String> name_list;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private downmusicrecycleradapter  adapter;
    private Toolbar toolbar;
    private int list_image;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.song_show);
        Intent intent=getIntent();
        Bundle bundle1=intent.getExtras();
        list_image=bundle1.getInt("list_image");
        song_list= Arrays.asList(bundle1.getStringArray("list_song"));
        name_list=Arrays.asList(bundle1.getStringArray("list_name"));
        initView();
    }
    public void initView(){
        toolbar=findViewById(R.id.toolbar);
        imageView=findViewById(R.id.image);
        imageView.setImageDrawable(getApplication().getResources().getDrawable(list_image));
        recyclerView=findViewById(R.id.recyclerview);
        adapter=new downmusicrecycleradapter(song_list,name_list,getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new downmusicrecycleradapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
              //TODO
                ShowDialog bottomDialogFr = new ShowDialog();
                bottomDialogFr.show(getSupportFragmentManager(), "hezhao");
            }
        });
    }
}
