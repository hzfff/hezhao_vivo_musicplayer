package com.example.musicplayer_hezhao.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.downmusicrecycleradapter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 11:46
 */
public class downmusic_downloading extends Fragment  implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener{
    private RecyclerView recyclerView;
    private downmusicrecycleradapter adapter;
    private View view;
    private List<String> list_name=new ArrayList<>();
    private List<String>list_singer=new ArrayList<>();
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.downmusic_downloading, container, false);
        initData();
        initview();
        return view;
    }

    public void initview() {
        recyclerView = view.findViewById(R.id.downloadmusic_recyclerview);
        adapter = new downmusicrecycleradapter(list_name,list_singer,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new downmusicrecycleradapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                // TODO
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("title").setMessage("message").create();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
                window.setWindowAnimations(R.style.mystyle);  //添加动画
                dialog.show();
            }
        });
    }

    public void initData() {
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_name.add("棋子");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");
        list_singer.add("王菲");

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
