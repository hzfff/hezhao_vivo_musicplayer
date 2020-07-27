package com.example.musicplayer_hezhao.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.DownLoadMusic;
import com.example.musicplayer_hezhao.HistoryMusic;
import com.example.musicplayer_hezhao.LocalMusic;
import com.example.musicplayer_hezhao.LoginMainActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.MyMusicBottomAdapter;
import com.example.musicplayer_hezhao.util.MusicListDialog;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.android.material.appbar.AppBarLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 11120555 on 2020/7/7 17:01
 */
public class MyMusicFragment extends BaseFragment implements View.OnClickListener, DialogInterface.OnDismissListener{
    private GyroscopeObserver gyroscopeObserver;
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private TextView creatmusic_text;
    private TextView selectmusic_text;
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
    private TextView My_favorite_music;
    private ImageView imageView;
    private ImageView detail_img;
    private final String TAG = "HeZhao";
    private static SwipeRefreshLayout swipeRefreshLayout;


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
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        swipeRefreshLayout = view.findViewById(R.id.swiprefreshlayout);
        imageView = view.findViewById(R.id.image2);
        Glide.with(this).load(R.mipmap.pic5).
                apply(RequestOptions.
                        bitmapTransform(new BlurTransformation(18, 3))).into(imageView);
        detail_img = view.findViewById(R.id.image_title);
        My_favorite_music = view.findViewById(R.id.text1);
        loadmusic = view.findViewById(R.id.downloadmusic_text);
        Drawable drawable = getResources().getDrawable(R.mipmap.download);
        drawable.setBounds(0, 0, 80, 80);
        loadmusic.setCompoundDrawables(null, drawable, null, null);
        local_music = view.findViewById(R.id.localmusic_text);
        Drawable drawable1 = getResources().getDrawable(R.mipmap.localmusic);
        drawable1.setBounds(0, 0, 80, 80);
        local_music.setCompoundDrawables(null, drawable1, null, null);
        recent_listen = view.findViewById(R.id.historymusic_text);
        Drawable drawable2 = getResources().getDrawable(R.mipmap.historymusic);
        drawable2.setBounds(0, 0, 80, 80);
        recent_listen.setCompoundDrawables(null, drawable2, null, null);
        login_name = view.findViewById(R.id.login_name);
        if(super.username!=null)
        {
            login_name.setText(super.username);
        }
        Intent intent = getActivity().getIntent();
        username = intent.getStringExtra("username");
        if (username != null) {
            login_name.setText(username);
        }
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new MySelectFragment());
        fragmentTransaction.commit();
//        myMusicBottomAdapter = new MyMusicBottomAdapter(list_img, list_induction, list_number, getContext());
        // recyclerView = view.findViewById(R.id.my_music_recyclerview);
        // recyclerView.setAdapter(myMusicBottomAdapter);
        creatmusic_text = view.findViewById(R.id.create_music_text);
        selectmusic_text = view.findViewById(R.id.collect_music_text);
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI / 9);
        PanoramaImageView panoramaImageView = view.findViewById(R.id.favorite_image_view1);
//        PanoramaImageView panoramaImageView1 = view.findViewById(R.id.favorite_image_view2);
//        PanoramaImageView panoramaImageView2 = view.findViewById(R.id.favorite_image_view3);
        //      panoramaImageView1.setGyroscopeObserver(gyroscopeObserver);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
//        panoramaImageView2.setGyroscopeObserver(gyroscopeObserver);
        creatmusic_text.setOnClickListener(this);
        creatmusic_text.setSelected(true);
        local_music.setOnClickListener(this);
        loadmusic.setOnClickListener(this);
        recent_listen.setOnClickListener(this);
        selectmusic_text.setOnClickListener(this);
        My_favorite_music.setOnClickListener(this);
        panoramaImageView.setOnClickListener(this);
        detail_img.setOnClickListener(this);
        // Personal_FM.setOnClickListener(this);
        login_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginMainActivity.class));
            }
        });
        detail_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicListDialog musicListDialog = new MusicListDialog();
                musicListDialog.show(getFragmentManager(), TAG);
                //处理musiclistdialog的回调
                musicListDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new MySelectFragment());
                        swipeRefreshLayout.setRefreshing(false);
                        fragmentTransaction.commit();
                    }
                });

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new MySelectFragment());
                        swipeRefreshLayout.setRefreshing(false);
                        fragmentTransaction.commit();
                    }
                }, 100);

            }
        });

    }
public static  void refresh(){
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_container, new MySelectFragment());
    swipeRefreshLayout.setRefreshing(false);
    fragmentTransaction.commit();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public void onClick(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.create_music_text:
                fragmentTransaction.replace(R.id.fragment_container, new MySelectFragment());
                creatmusic_text.setSelected(true);
                selectmusic_text.setSelected(false);
                break;
            case R.id.collect_music_text:
                fragmentTransaction.replace(R.id.fragment_container, new MySelectFragment());
                creatmusic_text.setSelected(false);
                selectmusic_text.setSelected(true);
                break;
            case R.id.downloadmusic_text:
                Intent intent1 = new Intent(getActivity(), DownLoadMusic.class);
                startActivity(intent1);
                break;
            case R.id.localmusic_text:
                Intent intent2 = new Intent(getActivity(), LocalMusic.class);
                startActivity(intent2);
                break;
            case R.id.historymusic_text:
                Intent intent3 = new Intent(getActivity(), HistoryMusic.class);
                startActivity(intent3);
                break;
            case R.id.favorite_image_view1:
                Intent intent4 = new Intent(getActivity(), My_Favorite_Music.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }
}
