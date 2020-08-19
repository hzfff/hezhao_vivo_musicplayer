package com.example.musicplayer_hezhao.fragment;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;

import com.example.musicplayer_hezhao.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 11120555 on 2020/7/27 11:02
 */
public class BaseDialogFragment  extends DialogFragment {
    public String UserName=null;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        UserName = sharedPreferences.getString("UserName", null);
        String PassWord = sharedPreferences.getString("PassWord", null);
        String MD5PWD = sharedPreferences.getString(UserName, null);
        if (MD5PWD!=null&&PassWord!=null&&MD5PWD.equals(PassWord)) {

        } else {
            UserName = null;
        }
    }
}
