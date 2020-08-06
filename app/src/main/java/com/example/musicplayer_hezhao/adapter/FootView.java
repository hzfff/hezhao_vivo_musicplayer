package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.musicplayer_hezhao.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 11120555 on 2020/8/1 14:45
 */
public class FootView extends RelativeLayout {

    @BindView(R.id.foot_view_progressbar)
    ProgressBar footViewProgressbar;

    public FootView(Context context) {
        this(context, null);
    }

    public FootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.foot_view, this);
        ButterKnife.bind(this, this);
    }

    public void setData() {
        footViewProgressbar.setVisibility(GONE);
    }
}