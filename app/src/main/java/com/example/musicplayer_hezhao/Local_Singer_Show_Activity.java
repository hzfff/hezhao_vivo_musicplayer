package com.example.musicplayer_hezhao;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.adapter.MusicShowAdapter;
import com.example.musicplayer_hezhao.fragment.local_music_fragment;
import com.example.musicplayer_hezhao.model.Music;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/21 10:58
 */
public class Local_Singer_Show_Activity  extends AppCompatActivity {
    private Intent intent=new Intent();
    private List<Music>Music_List=new ArrayList<>();
    private MusicShowAdapter adapters;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textView;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.local_singer);
        initview();
    }

    public void initview() {
        imageView=findViewById(R.id.image);
        toolbar=findViewById(R.id.toolbar);
        textView=findViewById(R.id.text);
        intent=getIntent();
        Music_List= (List<Music>) intent.getSerializableExtra("MusicList");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        textView.setText(Music_List.get(0).getArtist());
        imageView.setImageBitmap(Util.CreateBitmap(getContentResolver(), Uri.parse(Music_List.get(0).MusicImage)));
        recyclerView = findViewById(R.id.recyclerview);
        adapters = new MusicShowAdapter(Music_List, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapters);
        adapters.setOnItemClickListener(new MusicShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int index,View view, int position) {
                Intent intent0 = new Intent(getApplicationContext(), PlayMusicActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("MusicList", (Serializable) Music_List);
                intent0.putExtra("position",position);
                intent0.putExtras(bundle);
                startActivity(intent0);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
