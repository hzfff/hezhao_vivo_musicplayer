package com.example.musicplayer_hezhao.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.musicplayer_hezhao.util.RoundImageView;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/30 11:43
 */
public class MhealthAdater extends PagerAdapter {
    private List<RoundImageView> mViewList;
    private List<String> titlelist;
    public MhealthAdater(List<RoundImageView> mViewList){
        this.mViewList=mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
//      container.removeView(mViewList.get(position));
    }
    //初始化
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            position %= mViewList.size();
            RoundImageView view = mViewList.get(position);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }
            container.addView(view);
            //add listeners here if necessary
            return mViewList.get(position);
    }


    @Override
    public int getCount() {
        // 设置view数量为最大值,实现左右无限轮播
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        //推荐使用,View是否来自对象
        return arg0==arg1;
    }
}