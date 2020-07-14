package com.example.musicplayer_hezhao.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 11120555 on 2020/7/13 11:29
 */
public class loginadapter extends FragmentPagerAdapter {
private List<String> titlelist;
private List<Fragment>fragmentList;
    public loginadapter(@NonNull FragmentManager fm,List<String>list1,List<Fragment>list2) {
        super(fm);
        titlelist=list1;
        fragmentList=list2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return titlelist.get(position);
    }
}
