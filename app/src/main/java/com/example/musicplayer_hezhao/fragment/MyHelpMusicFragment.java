package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/10 15:06
 */
public class MyHelpMusicFragment  extends Fragment {
    private List<String> list_induction=new ArrayList<>();
    private List<String> list_number=new ArrayList<>();
    private List<Integer> list_img=new ArrayList<>();
    private RecyclerView recyclerView;
    private MyMusicBottomAdapter myMusicBottomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_music_recyclerview,null);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView=view.findViewById(R.id.my_music_recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        initdata();
        myMusicBottomAdapter=new MyMusicBottomAdapter(list_img,list_induction,list_number,getContext());
        recyclerView.setAdapter(myMusicBottomAdapter);
    }

    public void initdata() {
        list_img.add(R.mipmap.pic17);
        list_img.add(R.mipmap.pic14);
        list_img.add(R.mipmap.pic15);
        list_img.add(R.mipmap.pic16);
        list_img.add(R.mipmap.pic18);
        list_img.add(R.mipmap.pic20);
        list_img.add(R.mipmap.pic19);
        list_induction.add("陈奕迅年度歌单");
        list_induction.add("新垣结衣年度歌单");
        list_induction.add("睡前必听歌单");
        list_induction.add("睡前歌单");
        list_induction.add("薛之谦年度歌单");
        list_induction.add("王菲年度歌单");
        list_induction.add("床边故事");
        list_number.add("10首");
        list_number.add("62首");
        list_number.add("18首");
        list_number.add("37首");
        list_number.add("13首");
        list_number.add("133首");
        list_number.add("133首");
    }
}
