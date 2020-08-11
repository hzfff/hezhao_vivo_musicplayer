package com.example.musicplayer_hezhao.fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.musicplayer_hezhao.R;

/**
 * Created by 11120555 on 2020/8/6 16:05
 */
public class HanGuoFragment  extends Fragment {
    private View mView;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.hanguolayout, null);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        return mView;
    }
}
