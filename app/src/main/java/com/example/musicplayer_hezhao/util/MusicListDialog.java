package com.example.musicplayer_hezhao.util;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.musicplayer_hezhao.ManagerMusicListActivity;
import com.example.musicplayer_hezhao.R;

/**
 * Created by 11120555 on 2020/7/22 17:08
 */
public class MusicListDialog  extends DialogFragment {
    private Window window;
    private  View view;
    private TextView add_musiclist;
    private TextView delete_musiclist;
    private final  String TAG="HeZhao";
    private OnButtonClick onButtonClick;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.musiclist_create, null);
        initview();
        return view;
    }
    public void  initview(){
        add_musiclist=view.findViewById(R.id.test2);
        delete_musiclist=view.findViewById(R.id.test3);
        //添加歌单
        add_musiclist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               //跳转到添加歌单界面
                EditNameDialogFragment editNameDialogFragment=new EditNameDialogFragment();
                editNameDialogFragment.show(getFragmentManager(),TAG);
                getDialog().dismiss();

            }
        });
        delete_musiclist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //跳转到管理歌单界面
                Intent intent=new Intent(getActivity(), ManagerMusicListActivity.class);
                startActivity(intent);
                getDialog().dismiss();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        window = getDialog().getWindow();
        // 消除弹框与四边的距离
        window.setBackgroundDrawableResource(android.R.color.transparent);
        // 设置动画
        window.setWindowAnimations(R.style.bottomDialog);
        //设置圆角
        window.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 设置宽度
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);
    }
    public interface  OnButtonClick{
        public void onClick(View view);
    }
    public void setOnButtomClick(OnButtonClick onButtomClick)
    {
        this.onButtonClick=onButtomClick;
    }
}
