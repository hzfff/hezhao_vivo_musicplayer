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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer_hezhao.LocalMusic;
import com.example.musicplayer_hezhao.Local_Singer_Show_Activity;
import com.example.musicplayer_hezhao.PlayMusicActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.adapter.LocalMusic_Singer_Adapter;
import com.example.musicplayer_hezhao.adapter.MusicShowAdapter;
import com.example.musicplayer_hezhao.adapter.local_music_collect_adapter;
import com.example.musicplayer_hezhao.adapter.local_singer_fragment_adapter;
import com.example.musicplayer_hezhao.model.Music;
import com.example.musicplayer_hezhao.util.ShowDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/15 9:47
 */
public class local_singer_fragment extends BaseFragment {
    private RecyclerView recyclerView;
    private LocalMusic_Singer_Adapter adapter;
    private View view;
    private List<List<Music>>music_list=new ArrayList<>();
    private MusicTask  musicTask;
    private HashMap<String ,Integer>map=new HashMap<>();
    private class  MusicTask  extends AsyncTask<Object, Music,Void>{
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
            if(map.containsKey(music.getArtist())){
                int  key=map.get(music.getArtist());
                music_list.get(key).add(music);
            }else{
                map.put(music.getArtist(),music_list.size());
                List<Music>list=new ArrayList<>();
                list.add(music);
                music_list.add(new ArrayList<Music>(list));
            }
            LocalMusic_Singer_Adapter adapter = (LocalMusic_Singer_Adapter) recyclerView.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        musicTask = new MusicTask();
        musicTask.execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.local_music_fragment, container, false);
        initview();
        return view;
    }

    public void initview() {
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter=new LocalMusic_Singer_Adapter(music_list,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setOnItemClickListener(new LocalMusic_Singer_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent0 = new Intent(getActivity(), Local_Singer_Show_Activity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("MusicList", (Serializable) music_list.get(position));
                intent0.putExtras(bundle);
                startActivity(intent0);
            }
        });
    }


}
