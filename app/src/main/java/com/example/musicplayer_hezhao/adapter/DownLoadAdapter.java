package com.example.musicplayer_hezhao.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/14 11:53
 */
public class DownLoadAdapter extends FragmentPagerAdapter {
    private List<String> list_title = new ArrayList<>();
    private List<Fragment> list_fragment = new ArrayList<>();

    public DownLoadAdapter(@NonNull FragmentManager fm, List<String> list1, List<Fragment> list2) {
        super(fm);
        list_title = list1;
        list_fragment = list2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }
    @Override
    public int getCount() {
        return list_fragment.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return list_title.get(position);
    }
}
