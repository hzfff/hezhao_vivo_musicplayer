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
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;
import com.example.musicplayer_hezhao.util.MD5Utils;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 11120555 on 2020/7/13 11:38
 */
public class registerfragment extends BaseFragment {
    private Button btn_register,send_phone_check;
    private EditText et_user_name, et_psw, et_psw_again, et_phone, et_phone_check;
    private String userName, psw, pswAgain, phone, emailcheck;
    private NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regist_item, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        send_phone_check=view.findViewById(R.id.send_check_email);
        btn_register = view.findViewById(R.id.btn_login);
        et_user_name = view.findViewById(R.id.et_user_name);
        et_psw = view.findViewById(R.id.et_psw);
        et_psw_again = view.findViewById(R.id.et_psw_again);
        et_phone = view.findViewById(R.id.phone);
        et_phone_check = view.findViewById(R.id.phone_check);
        initdata();
    }

    public void initdata() {
        neteaseCloudMusicApiTool=new NeteaseCloudMusicApiTool();
        send_phone_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = et_phone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(getActivity(),"Phone is empty",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    try {
                        neteaseCloudMusicApiTool.sendcheckword(phone);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getEditString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(getActivity(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(getActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(getActivity(), "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(emailcheck)) {
                    Toast.makeText(getActivity(), "phone check is empty ", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getActivity(), "phone  is empty ", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(getActivity(), "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    try {
                        if (neteaseCloudMusicApiTool.isExistPhone(phone) == -1) {
                            Toast.makeText(getActivity(), "phone had been registered", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            int code = neteaseCloudMusicApiTool.register(phone, psw, emailcheck, userName);
                            if (code == 503) {
                                Toast.makeText(getActivity(), "验证码错误", Toast.LENGTH_SHORT).show();
                            } else if (code == 505) {
                                Toast.makeText(getActivity(), "该昵称已被占用", Toast.LENGTH_SHORT).show();
                            } else if (code == 200) {
                                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                                saveRegisterInfo(phone, psw);
                                getActivity().finish();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    public void saveRegisterInfo(String phone, String psw) {
        SharedPreferences sp = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("phone", phone);
        editor.putString("PassWord", psw);
        editor.commit();
    }

    public void getEditString() {
        userName = et_user_name.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        pswAgain = et_psw_again.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        emailcheck = et_phone_check.getText().toString().trim();
    }
}
