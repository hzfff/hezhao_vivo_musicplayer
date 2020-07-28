package com.example.musicplayer_hezhao.adapter;

/**
 * Created by 11120555 on 2020/7/28 10:18
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.musicplayer_hezhao.PlayMusicActivity;
import com.example.musicplayer_hezhao.R;
import com.example.musicplayer_hezhao.Service.MusicService;
import com.example.musicplayer_hezhao.Util;
import com.example.musicplayer_hezhao.model.Music;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BottomViewAdapter extends PagerAdapter {
    private static boolean index = true;
    private static MusicService.MusicServiceIBinder musicServiceIBinder;
    private MusicConnection musicConnection;
    private int temp = 0;

    public interface OnViewClickListener {
        void onViewClicked(int position);
    }

    private LinkedList<View> viewCache = null;
    private static List<Music> datas;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private OnViewClickListener onViewClickListener;
    private String UserName;
    private static ViewHolder holder;
    private static int key = 0;
    private boolean indexs = true;

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }

    public BottomViewAdapter(List<Music> songList, Context mContext, String UserName) {
        this.datas = songList;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
        this.viewCache = new LinkedList<View>();
        this.UserName = UserName;
        Intent intent = new Intent(mContext.getApplicationContext(), MusicService.class);
        MusicConnection musicConnection = new MusicConnection();
        mContext.bindService(intent, musicConnection, Context.BIND_AUTO_CREATE);
    }

    public void setDatas(List<Music> datas) {
        this.datas = datas;
    }

    public static void playpre(String UserName) {
        musicServiceIBinder.playPre(UserName);
        holder.image_Btn.setImageResource(R.mipmap.play);
    }

    public static void playnext(String UserName) {
        musicServiceIBinder.playNext(UserName);
        holder.image_Btn.setImageResource(R.mipmap.play);
        index = false;
    }

    public static Handler handlers = new Handler() {//创建消息处理器对象
        //在主线程中处理从子线程发送过来的消息
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            Music music = (Music) bundle.getSerializable("MUSIC");
            key = datas.indexOf(music);

        }
    };
    public static Handler handler = new Handler() {//创建消息处理器对象

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String Message = bundle.getString("MSG");
            String Messages = bundle.getString("MSGS");
            String UserName = bundle.getString("USERNAME");
            if (Messages.equals("INIT")) {
                init(UserName);
            }
            switch (Message) {
                case "PRE":
                    playpre(UserName);
                    break;
                case "NEXT":
                    playnext(UserName);
                    break;
                default:
                    break;
            }
        }
    };

    public void setHolder(ViewHolder viewHolder) {
        this.holder = viewHolder;
        index = false;
    }

    public static void init(String UserName) {
        musicServiceIBinder.addPlayList(datas, 0, UserName);
        musicServiceIBinder.play(UserName);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        View contentView = (View) object;
//        container.removeView(contentView);
//        viewCache.add(contentView);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = null;
        ViewHolder viewHolder = null;
        if (position >= datas.size()) {
            position = position % datas.size() == 0 ? 0 : position % datas.size() + 1;
        }
        if (viewCache.size() == 0) {
            convertView = layoutInflater.inflate(R.layout.bottom_layout_item, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            convertView = viewCache.removeFirst();
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(datas.get(position).getName());
        viewHolder.imageView.setImageBitmap(Util.CreateBitmap(mContext.getContentResolver(),
                Uri.parse(datas.get(position).getAlbumUri())));
        container.addView(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final int finalPosition = position;
        final ViewHolder finalViewHolder = viewHolder;
        setHolder(viewHolder);
        final int finalPosition2 = position;
        viewHolder.image_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewClickListener != null) {
                    onViewClickListener.onViewClicked(finalPosition);
                    if (index) {
                        finalViewHolder.image_Btn.setImageResource(R.mipmap.play);
                        musicServiceIBinder.addPlayList(datas, finalPosition2, UserName);
                        musicServiceIBinder.play(UserName);
                        index = false;
                    } else {
                        index = true;
                        finalViewHolder.image_Btn.setImageResource(R.mipmap.stop);
                        musicServiceIBinder.pause(UserName);
                    }
                }
            }
        });
        final int finalPosition1 = position;
        viewHolder.name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), PlayMusicActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MusicList", (Serializable) datas);
                bundle.putSerializable("position", finalPosition1-1  + 10000);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    /**
     * 进行View预加载处理
     */
    class ViewHolder {
        TextView name;
        ImageView imageView;
        ImageView image_Btn;
        ImageView detail_Btn;

        public ViewHolder(View view) {
            name = view.findViewById(R.id.play_music_text);
            imageView = view.findViewById(R.id.play_music_img);
            image_Btn = view.findViewById(R.id.play_music_buttom);
            detail_Btn = view.findViewById(R.id.play_music);
        }
    }

    class MusicConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicServiceIBinder = (MusicService.MusicServiceIBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}

