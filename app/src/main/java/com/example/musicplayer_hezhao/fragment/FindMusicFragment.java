package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.MhealthAdater;
import com.example.musicplayer_hezhao.util.RoundImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/7 17:02
 */
//播放器发现界面
public class FindMusicFragment   extends Fragment {
    private View mView;
    private ViewPager viewPager;
    private List<RoundImageView>mViewList=new ArrayList<>();
    private MhealthAdater adapter;
    private int currentItem=0;
    private boolean isScroll=false;
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mhandler.sendEmptyMessageDelayed(0, 2000);
            if (isScroll) {
            } else {
                currentItem = currentItem + 1;
                viewPager.setCurrentItem(currentItem);
               // changeIcon(currentItem);
            }

        }
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       mView=inflater.inflate(R.layout.findmusic_layout,null);
       initview();
      return mView;
    }
    public void initview(){
        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        RoundImageView view1 = new RoundImageView(getContext());
        view1.setImageResource(R.mipmap.pic4);
        view1.setScaleType(RoundImageView.ScaleType.CENTER_CROP );
        mViewList.add(view1);
        RoundImageView view2 = new RoundImageView(getContext());
        view2.setImageResource(R.mipmap.pic2);
        view2.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
        mViewList.add(view2);
        RoundImageView view3 = new RoundImageView(getContext());
        view3.setImageResource(R.mipmap.pic3);
        view3.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
        mViewList.add(view3);
        RoundImageView view4 = new RoundImageView(getContext());
        view4.setImageResource(R.mipmap.pic23);
        view4.setScaleType(RoundImageView.ScaleType.CENTER_CROP);
        mViewList.add(view4);
        //设置适配器
        adapter = new MhealthAdater(mViewList);
        viewPager.setAdapter(adapter);
        //默认显示的图片
        currentItem = (int) (Integer.MAX_VALUE * 0.5);
        viewPager.setCurrentItem((int) (Integer.MAX_VALUE * 0.5));
        //定时更新UI
        mhandler.sendEmptyMessageDelayed(0, 2000);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
              //  changeIcon(arg0);
                currentItem = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            //处于滑动状态时不轮播图片
            @Override
            public void onPageScrollStateChanged(int arg0) {
                if (arg0 == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
            }
        });

    }

}
