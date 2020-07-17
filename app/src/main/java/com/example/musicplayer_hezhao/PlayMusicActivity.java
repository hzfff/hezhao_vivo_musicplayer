package com.example.musicplayer_hezhao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer_hezhao.tool.CircleImageView;
import com.example.musicplayer_hezhao.tool.LrcView;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 11120555 on 2020/7/17 15:38
 */
public class PlayMusicActivity  extends AppCompatActivity implements View.OnClickListener {
    private SeekBar seekBar;
    private LrcView lrcView;
    private Button start_button;
    private Button pre_button;
    private Button next_button;
    private ImageView background_pic;
    private CircleImageView profile_pic;
    private TextView begin_time;
    private TextView end_time;
    private ImageView share_img;
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.play_music_main_pager);
        initView();
    }
    public void initView(){
        share_img=findViewById(R.id.share);
        toolbar=findViewById(R.id.toolbar);
        seekBar=findViewById(R.id.seek_music);
        lrcView=findViewById(R.id.lyric);
        start_button=findViewById(R.id.play_btn);
        pre_button=findViewById(R.id.pre_btn);
        next_button=findViewById(R.id.next_btn);
        background_pic=findViewById(R.id.play_music_background);
        profile_pic=findViewById(R.id.image);
        begin_time=findViewById(R.id.played_time);
        end_time=findViewById(R.id.duration_time);
        pre_button.setOnClickListener(this);
        next_button.setOnClickListener(this);
        start_button.setOnClickListener(this);
        lrcView.setOnClickListener(this);
        share_img.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Glide.with(this).load(R.mipmap.pic17).
                apply(RequestOptions.
                        bitmapTransform(new BlurTransformation(18, 6))).into(background_pic);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
