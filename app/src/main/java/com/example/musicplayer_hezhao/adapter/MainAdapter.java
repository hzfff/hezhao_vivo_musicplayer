package com.example.musicplayer_hezhao.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/7 15:24
 */
public class MainAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private FragmentManager manager;
    public static Fragment instantFragment;
    private List<Fragment> tabFragments;
    private List<String> tabTitles;

    public  MainAdapter(Context context, FragmentManager fm, List<Fragment> fragments,
                                 List<String> titles) {
        super(fm);
        manager = fm;
        mContext = context;
        tabFragments = fragments;
        tabTitles = titles;
    }

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        // 可以即时刷新Fragment
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragments.get(position);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        instantFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getInstantFragment(){
        return instantFragment;
    }

    @Override
    public int getCount() {
        return tabFragments.size();
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles.get(position);
//    }

}
