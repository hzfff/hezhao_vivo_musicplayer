package com.example.musicplayer_hezhao;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by 11120555 on 2020/7/27 10:08
 */
//所有activity需要继承此类，用于判断是否登录
public class BaseActivity extends AppCompatActivity {
    public  String  username=null;
    public String getUsername(){
        return  username;
    }
    @Override
    public void  onCreate(Bundle bundle) {

        super.onCreate(bundle);
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        username = sharedPreferences.getString("UserName", null);
        String PassWord = sharedPreferences.getString("PassWord", null);
        String MD5PWD=sharedPreferences.getString(username,null);
        if(MD5PWD!=null&&MD5PWD.equals(PassWord))
        {

        }else {
            username = null;
        }
    }
}
