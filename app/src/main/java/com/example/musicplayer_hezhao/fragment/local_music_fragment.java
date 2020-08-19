package com.example.musicplayer_hezhao.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.PlayMusicActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.adapter.MusicShowAdapter;
import com.example.musicplayer_hezhao.adapter.downmusicrecycleradapter;
import com.example.musicplayer_hezhao.adapter.local_music_collect_adapter;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 9:46
 */
public class local_music_fragment extends BaseFragment {
    private RecyclerView recyclerView;
    private MusicShowAdapter adapters;
    private View view;
    private MusicUpdateTask musicUpdateTask;
    private List<Music> musicList = new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
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
            public void onItemClick(int index,View view, int position) {
                //点击右侧图片跳出底部选项歌曲详细信息，删除或者添加到喜欢的音乐选项
                if(index==2) {
                    ShowDialog bottomDialogFr = new ShowDialog();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MusicList", (Serializable) musicList.get(position));
                    bottomDialogFr.setArguments(bundle);
                    bottomDialogFr.show(getFragmentManager(), "hezhao");
                }else if(index==1){
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
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] strs = new String[]{
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.ALBUM_ID,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.DURATION
            };
            String where = MediaStore.Audio.Media.DATA + " like \"%" + "/raw" + "%\"";
            String[] keywords = null;
            String sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
            ContentResolver contentResolver = getActivity().getContentResolver();
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            Cursor cursor = contentResolver.query(uri, strs, where, keywords, sortOrder);
            if (cursor != null) {
                while (cursor.moveToNext() && !isCancelled()) {
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    Uri musicUri = Uri.withAppendedPath(uri, id);
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    int albumId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ID));
                    Uri albumUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
                    String Artist=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    Music music = new Music(String.valueOf(musicUri), String.valueOf(albumUri), name, duration, Artist,0);
                    if (uri != null) {
                        ContentResolver resolver = getActivity().getContentResolver();
                        music.MusicImage = String.valueOf(albumUri);
                    }
                    publishProgress(music);
                }
                cursor.close();

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Music... musiclists) {
            Music music = musiclists[0];
            musicList.add(music);
            MusicShowAdapter adapter = (MusicShowAdapter) recyclerView.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }
}
