package com.example.musicplayer_hezhao.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.DownLoadMusic;
import com.example.musicplayer_hezhao.HistoryMusic;
import com.example.musicplayer_hezhao.LocalMusic;
import com.example.musicplayer_hezhao.LoginMainActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/7 17:01
 */
public class MyMusicFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {
    private GyroscopeObserver gyroscopeObserver;
    private Toolbar my_music_toolbar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView creatmusic_text;
    private TextView selectmusic_text;
    private TextView helpmusic_text;
    private List<String> list_induction = new ArrayList<>();
    private List<String> list_number = new ArrayList<>();
    private List<Integer> list_img = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyMusicBottomAdapter myMusicBottomAdapter;
    private TextView login_name;
    private String username = null;
    private TextView loadmusic;
    private TextView local_music;
    private TextView recent_listen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mymusic_layout, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(my_music_toolbar);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        loadmusic=view.findViewById(R.id.downloadmusic_text);
        local_music=view.findViewById(R.id.localmusic_text);
        recent_listen=view.findViewById(R.id.historymusic_text);
        login_name = view.findViewById(R.id.login_name);
        Intent intent = getActivity().getIntent();
        username = intent.getStringExtra("username");
        if (username != null) {
            login_name.setText(username);
        }
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new MyCreateMusicFragment());
        fragmentTransaction.commit();
        myMusicBottomAdapter = new MyMusicBottomAdapter(list_img, list_induction, list_number, getContext());
        recyclerView = view.findViewById(R.id.my_music_recyclerview);
        recyclerView.setAdapter(myMusicBottomAdapter);
        creatmusic_text = view.findViewById(R.id.create_music_text);
        selectmusic_text = view.findViewById(R.id.collect_music_text);
        helpmusic_text = view.findViewById(R.id.help_music_text);
        my_music_toolbar = view.findViewById(R.id.my_music_toolbar);
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI / 9);
        PanoramaImageView panoramaImageView = view.findViewById(R.id.favorite_image_view1);
        PanoramaImageView panoramaImageView1 = view.findViewById(R.id.favorite_image_view2);
        PanoramaImageView panoramaImageView2 = view.findViewById(R.id.favorite_image_view3);
        panoramaImageView1.setGyroscopeObserver(gyroscopeObserver);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
        panoramaImageView2.setGyroscopeObserver(gyroscopeObserver);
        creatmusic_text.setOnClickListener(this);
        creatmusic_text.setSelected(true);
        local_music.setOnClickListener(this);
        loadmusic.setOnClickListener(this);
        recent_listen.setOnClickListener(this);
        selectmusic_text.setOnClickListener(this);
        helpmusic_text.setOnClickListener(this);
        login_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginMainActivity.class));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        gyroscopeObserver.register(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        my_music_toolbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public void onClick(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.create_music_text:
                fragmentTransaction.replace(R.id.fragment_container, new MyCreateMusicFragment());
                creatmusic_text.setSelected(true);
                selectmusic_text.setSelected(false);
                helpmusic_text.setSelected(false);
                break;
            case R.id.collect_music_text:
                fragmentTransaction.replace(R.id.fragment_container, new MySelectFragment());
                creatmusic_text.setSelected(false);
                selectmusic_text.setSelected(true);
                helpmusic_text.setSelected(false);
                break;
            case R.id.help_music_text:
                fragmentTransaction.replace(R.id.fragment_container, new MyHelpMusicFragment());
                creatmusic_text.setSelected(false);
                selectmusic_text.setSelected(false);
                helpmusic_text.setSelected(true);
                break;
            case R.id.downloadmusic_text:
                Intent intent1=new Intent(getActivity(), DownLoadMusic.class);
                startActivity(intent1);
                break;
            case R.id.localmusic_text:
                Intent intent2=new Intent(getActivity(), LocalMusic.class);
                startActivity(intent2);
                break;
            case R.id.historymusic_text:
                Intent intent3=new Intent(getActivity(), HistoryMusic.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
