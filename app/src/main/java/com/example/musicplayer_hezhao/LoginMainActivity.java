package com.example.musicplayer_hezhao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer_hezhao.adapter.loginadapter;
import com.example.musicplayer_hezhao.fragment.loginfragment;
import com.example.musicplayer_hezhao.fragment.registerfragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/13 11:29
 */
public class LoginMainActivity  extends AppCompatActivity  implements View.OnClickListener {
    private List<String> TitleList=new ArrayList<>();
    private List<Fragment>fragmentList=new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private loginadapter   loginadapter;
    private Toolbar logintoolbar;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.login);
        logintoolbar=findViewById(R.id.login_toolbar);
        setSupportActionBar(logintoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        viewPager=findViewById(R.id.login_viewpager);
        tableLayout=findViewById(R.id.login_tablelayout);
        initdata();
        loginadapter=new loginadapter(getSupportFragmentManager(),TitleList,fragmentList);
        viewPager.setAdapter(loginadapter);
        tableLayout.setupWithViewPager(viewPager);
        logintoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void initdata(){
     TitleList.add("登录");
     TitleList.add("注册");
     fragmentList.add(new loginfragment());
     fragmentList.add(new registerfragment());
    }


    @Override
    public void onClick(View view) {

    }
}
