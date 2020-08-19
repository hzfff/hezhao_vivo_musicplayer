package com.example.musicplayer_hezhao.fragment;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.example.musicplayer_hezhao.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 11120555 on 2020/7/27 10:35
 */
public class BaseFragment extends Fragment {
    String username = null;

    @Override
    public void onCreate(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            // 有些情况下需要先清除透明flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.Red));
        }
        super.onCreate(bundle);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        username = sharedPreferences.getString("UserName", null);
        String PassWord = sharedPreferences.getString("PassWord", null);
        String MD5PWD = sharedPreferences.getString(username, null);
        if (MD5PWD!=null&&PassWord!=null&&MD5PWD.equals(PassWord)) {

        } else {
            username = null;
        }
    }
}
