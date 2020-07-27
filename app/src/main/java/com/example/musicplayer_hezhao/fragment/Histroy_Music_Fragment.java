package com.example.musicplayer_hezhao.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.ContentProvider.PlayListProvider;
import com.example.musicplayer_hezhao.DB.DBHelper;
import com.example.musicplayer_hezhao.PlayMusicActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.MusicShowAdapter;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/22 14:07
 */
public class Histroy_Music_Fragment extends BaseFragment {
    private RecyclerView recyclerView;
    private MusicShowAdapter adapters;
    private View view;
    private MusicUpdateTask musicUpdateTask;
    private List<Music> musicList = new ArrayList<>();
    private String UserName;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        UserName=super.username;
        musicUpdateTask = new MusicUpdateTask();
        musicUpdateTask.execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_vedio, container, false);
        initview();
        return view;
    }

    public void initview() {
        recyclerView = view.findViewById(R.id.recyclerview);
        adapters = new MusicShowAdapter(musicList, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapters);
        //相应recyclerview的点击事件
        adapters.setOnItemClickListener(new MusicShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int index, View view, int position) {
                //点击右侧图片跳出底部选项歌曲详细信息，删除或者添加到喜欢的音乐选项
                if (index == 2) {
                    ShowDialog bottomDialogFr = new ShowDialog();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MusicList", (Serializable) musicList.get(position));
                    bottomDialogFr.setArguments(bundle);
                    bottomDialogFr.show(getFragmentManager(), "hezhao");
                } else if (index == 1) {
                    //点击歌名跳转到播放页面
                    Intent intent0 = new Intent(getActivity(), PlayMusicActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MusicList", (Serializable) musicList);
                    intent0.putExtra("position", position);
                    intent0.putExtras(bundle);
                    startActivity(intent0);

                }
            }
        });
    }

    private class MusicUpdateTask extends AsyncTask<Object, Music, Void> {

        @Override
        protected Void doInBackground(Object... objects) {
            musicList.clear();
            String where = "username=?";
            String[] SongListUri = new String[]{UserName};
            Cursor cursor = getActivity().getContentResolver().query(
                    PlayListProvider.CONTENT_URI_SONG_THIRD,
                    null,
                    where,
                    SongListUri,
                    null);
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                String songUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SONG_URI));
                String albumUri = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ALBUM_URI));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
                long playedtime = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.LAST_PLAY_TIME));
                long duration = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.DURATION));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ARTIST));
                Music music = new Music(songUri, albumUri, name, duration, playedtime, artist);
                musicList.add(music);
            }
            Collections.reverse(musicList);
            cursor.close();
            publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(Music... musiclists) {
            MusicShowAdapter adapter = (MusicShowAdapter) recyclerView.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }
}
