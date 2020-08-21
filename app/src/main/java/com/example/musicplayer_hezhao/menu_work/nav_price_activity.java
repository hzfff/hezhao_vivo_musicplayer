package com.example.musicplayer_hezhao.menu_work;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.nav_price_adapter;
import com.example.musicplayer_hezhao.fragment.MyMusicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/13 14:49
 */
public class nav_price_activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private nav_price_adapter adapter;
    private List<Integer>pic_list=new ArrayList<>();
    private List<Integer>pic_list_copy=new ArrayList<>();
    private Toolbar toolbar ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_price_layout);
        initview();
        initdata();
    }
    public void initview(){
        recyclerView=findViewById(R.id.recyclerview);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("换肤");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitleTextAppearance(getApplicationContext(),R.style.MyEditText);
        initlist();
        initlistcopy();
        adapter=new nav_price_adapter(pic_list,getApplicationContext());
        gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
    public void initlist(){
        pic_list.add(R.mipmap.pic1);
        pic_list.add(R.mipmap.pic2);
        pic_list.add(R.mipmap.pic4);
        pic_list.add(R.mipmap.pic5);
        pic_list.add(R.mipmap.pic6);
        pic_list.add(R.mipmap.pic8);
        pic_list.add(R.mipmap.pic12);
        pic_list.add(R.mipmap.pic23);
    }
    public void initlistcopy(){
        pic_list_copy.add(R.mipmap.pic11);
        pic_list_copy.add(R.mipmap.pic22);
        pic_list_copy.add(R.mipmap.pic44);
        pic_list_copy.add(R.mipmap.pic55);
        pic_list_copy.add(R.mipmap.pic66);
        pic_list_copy.add(R.mipmap.pic88);
        pic_list_copy.add(R.mipmap.pic122);
        pic_list_copy.add(R.mipmap.pic233);
    }
    public void initdata(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new nav_price_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pic_list.clear();
                initlist();
                Message msg= MyMusicFragment.handler.obtainMessage();
                Bundle bundle=new Bundle();
                bundle.putInt("ListInteger",pic_list.get(position));
                msg.setData(bundle);
                msg.what=1;
                MyMusicFragment.handler.sendMessage(msg);
                pic_list.remove(position);
                pic_list.add(position,pic_list_copy.get(position));
                nav_price_adapter adapter= (nav_price_adapter) recyclerView.getAdapter();
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"设置成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
