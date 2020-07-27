package com.example.musicplayer_hezhao.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.musicplayer_hezhao.AddMusicToList;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Service.MusicListService;
import com.example.musicplayer_hezhao.Service.MyFavoriteMusic_Service;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.fragment.BaseDialogFragment;
import com.example.musicplayer_hezhao.fragment.My_Favorite_Music;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.model.MusicListModel;

import java.io.Serializable;
import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by 11120555 on 2020/7/24 9:17
 */
public class AddMusicToListDialog  extends BaseDialogFragment {
    private View view;
    private Window window;
    private TextView collect;
    private MyServiceConn myServiceConn;
    private TextView list_name;
    private TextView list_num;
    private TextView add_music;
    private RoundImageView list_img;
    private Music Music;
    private List<MusicListModel>MusicListModels;
    private MusicListService.MusicServiceIBinder musicServiceIBinder;
    private int Position;
    private String UserName;
    public AddMusicToListDialog(Music music, List<MusicListModel> musicListModelList, int position)
    {
        this.Music=music;
        this.MusicListModels=musicListModelList;
        this.Position=position;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        UserName=super.UserName;
        view = inflater.inflate(R.layout.musicadddialog, null);
        initview();
        return view;
    }

    public void initview() {
        Intent intent=new Intent(getActivity().getApplicationContext(),MusicListService.class);
        myServiceConn=new MyServiceConn();
        getActivity().getApplicationContext().bindService(intent,myServiceConn,BIND_AUTO_CREATE);
        add_music=view.findViewById(R.id.test5);
        list_img=view.findViewById(R.id.test_img);
        list_name=view.findViewById(R.id.test_song);
        list_num=view.findViewById(R.id.test_name);
        if (MusicListModels.get(Position).getMusicName().size() > 0) {
           list_img.setImageBitmap(Util.CreateBitmap(getActivity().getContentResolver(),
                    Uri.parse(MusicListModels.get(Position).getMusicName().get(0).getAlbumUri())));
        } else {
            list_img.setImageResource(R.mipmap.pic12);
        }
        list_name.setText(MusicListModels.get(Position).getMusicListName());
        list_num.setText(""+MusicListModels.get(Position).getMusicName().size()+"首");
        add_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Music> result = musicServiceIBinder.QueryMusicFromList(MusicListModels.get(Position).getMusicListName(),UserName);
                if(result.contains(Music)){
                    add_music.setText("添加");
                    musicServiceIBinder.DeleteMusicFromList(MusicListModels.get(Position).getMusicListName(),Music,UserName);
                    AddMusicToList.notifyChangeDelete(Position,Music);
                    Toast.makeText(getContext(), "已取消收藏", Toast.LENGTH_SHORT).show();
                } else {
                    add_music.setText("已添加");
                    musicServiceIBinder.InsertMusicList(MusicListModels.get(Position).getMusicListName(),Music,UserName);
                    Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    AddMusicToList.notifyChangeAdd(Position,Music);
                }
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
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 设置宽度
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);
    }
    class MyServiceConn implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicServiceIBinder= (MusicListService.MusicServiceIBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
