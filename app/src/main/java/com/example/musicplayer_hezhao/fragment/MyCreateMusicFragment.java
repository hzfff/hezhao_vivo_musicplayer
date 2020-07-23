package com.example.musicplayer_hezhao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Song_Show;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;
import com.example.musicplayer_hezhao.util.ShowDialog;

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
      //  myMusicBottomAdapter=new MyMusicBottomAdapter(list_img,list_induction,list_number,getContext());
        mrecyclerView.setAdapter(myMusicBottomAdapter);
//        myMusicBottomAdapter.setOnItemClickListener(new MyMusicBottomAdapter.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle=new Bundle();
//                System.out.println(position);
//                bundle.putInt("list_image",list_img.get(position));
//                bundle.putStringArray("list_song",new String[]{"下雨天","微风细雨","Man At Arms","夏天的风","你在烦恼什么","一直很安静","月黑风高","幸福额度","绿色","透明世界","起风了"});
//                bundle.putStringArray("list_name",new String[]{"王菲","周杰伦","陈奕迅","蔡琴","温岚","阿桑","陈奕迅","苏打绿","陈雪凝","火影忍者","吴青峰"});
//                Intent intent=new Intent(getContext(), Song_Show.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
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
