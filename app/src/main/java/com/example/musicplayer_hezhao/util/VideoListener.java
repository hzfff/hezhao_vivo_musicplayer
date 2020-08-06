package com.example.musicplayer_hezhao.util;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by 11120555 on 2020/8/1 16:53
 */
public interface VideoListener extends IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnSeekCompleteListener{
}
