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

import com.example.musicplayer_hezhao.MainActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.util.MD5Utils;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 11120555 on 2020/7/13 11:38
 */
public class loginfragment   extends Fragment {
    private Button submit_button;
    private Button forget_name_button;
    private String username, psw, spPsw;
    private EditText et_user_name, et_psw;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_item, container,false);
        return view;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        submit_button = view.findViewById(R.id.btn_login);
        forget_name_button = view.findViewById(R.id.btn_froget_password);
        et_user_name = view.findViewById(R.id.et_user_name);
        et_psw = view.findViewById(R.id.et_psw);
        initdata();
    }
    public void initdata() {
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                String md5Psw = MD5Utils.md5(psw);
                spPsw = readPsw(username);
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (md5Psw.equals(spPsw)) {
                    Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    saveLoginStatus(true, username,md5Psw);
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    intent.putExtra("isLogin", true);
                    intent.putExtra("username",username);
                    getActivity().setResult(RESULT_OK, intent);
                    startActivity(intent);
                    return;
                } else if ((spPsw != null && !TextUtils.isEmpty(spPsw) && !md5Psw.equals(spPsw))) {
                    Toast.makeText(getContext(), "账号密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(getContext(), "此用户不存在", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }


    //从sharedpreferences中读取密码
    public String readPsw(String username) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sharedPreferences.getString(username, "");
    }

    //保存登录状态到sharedpreference
    private void saveLoginStatus(boolean status, String Username,String MD5PSW) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.putString("loginUserName", Username);
        editor.putString("UserName",Username);
        editor.putString("PassWord",MD5PSW);
        editor.commit();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //是获取注册界面回传过来的用户名
            String userName = data.getStringExtra("userName");
            if (!TextUtils.isEmpty(userName)) {
                //设置用户名到 et_user_name 控件
                et_user_name.setText(userName);
                //et_user_name控件的setSelection()方法来设置光标位置
                et_user_name.setSelection(userName.length());
            }
        }
    }
}


