package com.example.musicplayer_hezhao.util;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Service.MusicListService;
import com.example.musicplayer_hezhao.fragment.MyMusicFragment;
import com.example.musicplayer_hezhao.fragment.MySelectFragment;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by 11120555 on 2020/7/22 17:47
 */
//创建歌单，歌单管理
public class EditNameDialogFragment extends DialogFragment {
    private EditText editText;
    private Button submit;
    private Button cancel;
    private Window window;
    private String MusicListName;
    private MyServiceConn myServiceConn;
    private MusicListService.MusicServiceIBinder musicServiceIBinder;
    public EditNameDialogFragment() {
    }

    public static EditNameDialogFragment newInstance(String tittle) {
        EditNameDialogFragment fragment = new EditNameDialogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Intent intent=new Intent(getActivity(),MusicListService.class);
        myServiceConn=new MyServiceConn();
        boolean flag=getActivity().bindService(intent,myServiceConn,BIND_AUTO_CREATE);
        editText = view.findViewById(R.id.music_list_name);
        cancel = view.findViewById(R.id.button1);
        submit = view.findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initdata();
                if(TextUtils.isEmpty(MusicListName)){
                    Toast.makeText(getActivity(), "歌单名不能为空", Toast.LENGTH_SHORT).show();

                }else{
                    musicServiceIBinder.CreateMusicList(MusicListName);
                    Toast.makeText(getActivity(), "创建歌单成功", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                    return;
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        window = getDialog().getWindow();
        // 消除弹框与四边的距离
        // 设置动画
        window.setWindowAnimations(R.style.bottomDialog);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        // 设置宽度
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);
    }

    public void initdata() {
        MusicListName = editText.getText().toString().trim();
    }

    class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicServiceIBinder= (MusicListService.MusicServiceIBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}