package com.example.musicplayer_hezhao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer_hezhao.R;


/**
 * Created by 11120555 on 2020/7/7 14:35
 */

public class MusicPlayerViewPager extends ViewPager {
    private boolean isNoScroll = false;

    public MusicPlayerViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean isNoScroll) {
        this.isNoScroll = isNoScroll;
    }

    public MusicPlayerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = getContext().obtainStyledAttributes(attrs,R.styleable.MusicPlayerViewPager );
        this.isNoScroll = ta.getBoolean(R.styleable.MusicPlayerViewPager_noScroll, false);
        ta.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getCurrentItem() == 0 && getChildCount() == 0) {
            return false;
        }

        return !isNoScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getCurrentItem() == 0 && getChildCount() == 0) {
            return false;
        }

        return !isNoScroll && super.onTouchEvent(ev);
    }
}
