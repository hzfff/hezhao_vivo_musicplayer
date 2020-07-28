package com.example.musicplayer_hezhao.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by 11120555 on 2020/7/28 14:51
 */
public class customViewpagerView extends ViewPager {

    private int preX=0;
    public customViewpagerView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public customViewpagerView(Context context) {
        super(context);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent even) {

        if(even.getAction()==MotionEvent.ACTION_DOWN)
        {
            preX=(int) even.getX();
        }else
        {
            if(Math.abs((int)even.getX()-preX)>10)
            {
                return true;
            }else
            {
                preX=(int) even.getX();
            }
        }
        return super.onInterceptTouchEvent(even);
    }
}

