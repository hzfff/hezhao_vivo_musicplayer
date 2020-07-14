package com.example.musicplayer_hezhao.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.util.MD5Utils;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 11120555 on 2020/7/13 11:38
 */
public class registerfragment extends Fragment {
    private Button btn_register;
    private EditText et_user_name, et_psw, et_psw_again;
    private String userName, psw, pswAgain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regist_item, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        btn_register = view.findViewById(R.id.btn_login);
        et_user_name = view.findViewById(R.id.et_user_name);
        et_psw = view.findViewById(R.id.et_psw);
        et_psw_again = view.findViewById(R.id.et_psw_again);
        initdata();
    }
    public void initdata(){
        btn_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getEditString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(getActivity(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(getActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(getActivity(), "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(getActivity(), "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                    //从SharedPreferences获取判断用户是否存在
                }else if(isExistUserName(userName)){
                    Toast.makeText(getActivity(), "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    //把账号、密码和账号标识保存
                    saveRegisterInfo(userName, psw);
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    getActivity().setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面
                    getActivity().finish();
                }
            }
        });
    }
    public boolean isExistUserName(String userName)
    {
        boolean has_userName=false;
        SharedPreferences sp=getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    public void saveRegisterInfo(String userName,String psw)
    {
        String MD5PSW= MD5Utils.md5(psw);
        SharedPreferences sp=getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName,MD5PSW);
        editor.commit();
    }

    public void getEditString(){
        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_psw_again.getText().toString().trim();
    }
}
