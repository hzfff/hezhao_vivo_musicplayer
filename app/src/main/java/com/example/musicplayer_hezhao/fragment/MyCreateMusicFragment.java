package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/10 14:30
 */
public class MyCreateMusicFragment extends Fragment {
    private List<Integer> list_img = new ArrayList<>();
    private List<String>list_induction=new ArrayList<>();
    private List<String>list_number=new ArrayList<>();
    private MyMusicBottomAdapter myMusicBottomAdapter;
    private RecyclerView mrecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_music_recyclerview, container,false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView=view.findViewById(R.id.my_music_recyclerview);
        mrecyclerView.setLayoutManager(linearLayoutManager);
        initdata();
        mrecyclerView.setLayoutManager(linearLayoutManager);
        myMusicBottomAdapter=new MyMusicBottomAdapter(list_img,list_induction,list_number,getContext());
        mrecyclerView.setAdapter(myMusicBottomAdapter);
    }
    public void initdata(){
        list_img.add(R.mipmap.favorite_music_pic1);
        list_img.add(R.mipmap.favorite_music_pic2);
        list_img.add(R.mipmap.favorite_music_pic3);
        list_img.add(R.mipmap.favorite_music_pic4);
        list_img.add(R.mipmap.favorite_music_pic5);
        list_img.add(R.mipmap.favorite_music_pic6);
        list_induction.add("苏打绿年度歌单");
        list_induction.add("陈奕迅年度歌单");
        list_induction.add("睡前必听歌单");
        list_induction.add("陈奕迅年度歌单");
        list_induction.add("薛之谦年度歌单");
        list_induction.add("王菲年度歌单");
        list_number.add("10首");
        list_number.add("62首");
        list_number.add("18首");
        list_number.add("37首");
        list_number.add("13首");
        list_number.add("133首");

    }
}
