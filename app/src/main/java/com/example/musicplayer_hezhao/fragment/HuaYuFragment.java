package com.example.musicplayer_hezhao.fragment;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.HuaYuAdapter;
import com.example.musicplayer_hezhao.model.Data;
import com.example.musicplayer_hezhao.model.HotMusic;
import com.example.musicplayer_hezhao.model.Info;
import com.example.musicplayer_hezhao.model.MusicInfo;
import com.example.musicplayer_hezhao.model.Num;
import com.example.musicplayer_hezhao.model.SearchMusicCallback;
import com.example.musicplayer_hezhao.model.SongID;
import com.example.musicplayer_hezhao.model.VedioInformation;
import com.example.musicplayer_hezhao.model.findsongs;
import com.example.musicplayer_hezhao.model.huayu;
import com.example.musicplayer_hezhao.tool.NeteaseCloudMusicApiTool;

import java.util.List;

/**
 * Created by 11120555 on 2020/8/6 16:05
 */
public class HuaYuFragment extends Fragment implements NeteaseCloudMusicApiTool.Callback {
    private static View mView;
    private static RecyclerView recyclerView;
    private static HuaYuAdapter adapter;
    private static huayu HuaYu;
    private static NeteaseCloudMusicApiTool neteaseCloudMusicApiTool;
    private static LinearLayoutManager linearLayoutManager;
    private static Context mContext;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.huayulayout, null);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        initview();
        return mView;
    }

    public void initview() {
        mContext=getContext();
        neteaseCloudMusicApiTool = new NeteaseCloudMusicApiTool();
        recyclerView = mView.findViewById(R.id.recyclerview);
        neteaseCloudMusicApiTool.findHuYu(this);
    }

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter = new HuaYuAdapter(HuaYu, mContext);
                    linearLayoutManager = new LinearLayoutManager(mContext);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new HuaYuAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void doResult1(List<SongID> obj) {

    }

    @Override
    public void doResult2(List<String> obj) {

    }

    @Override
    public void doResult3(List<MusicInfo> obj) {

    }

    @Override
    public void doResult4(List<String> obj) {

    }

    @Override
    public void doResult5(Data<Info> obj) {

    }

    @Override
    public void doResult6(List<Num> obj) {

    }

    @Override
    public void doResult7(List<VedioInformation> obj) {

    }

    @Override
    public void doResult8(HotMusic obj) {

    }

    @Override
    public void doResult9(SearchMusicCallback searchMusicCallback) {

    }

    @Override
    public void doResult10(huayu huayu) {
        HuaYu = huayu;
        Message msg = handler.obtainMessage();
        msg.what = 1;
        handler.sendMessage(msg);
    }

    @Override
    public void doResult11(findsongs findsongs) {

    }

    @Override
    public void doResult12(List<String> lyrclist) {

    }
}
