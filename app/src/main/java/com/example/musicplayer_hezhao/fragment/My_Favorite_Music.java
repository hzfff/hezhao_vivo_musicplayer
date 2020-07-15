package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;

/**
 * Created by 11120555 on 2020/7/15 15:07
 */
public class My_Favorite_Music  extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.my_favorite_music);
        initview();
        initdata();
    }
    public void initview(){
      toolbar=findViewById(R.id.toolbar);
      recyclerView=findViewById(R.id.recyclerview);
      
    }
    public void initdata(){

    }
}
