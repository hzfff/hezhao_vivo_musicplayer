package com.example.musicplayer_hezhao;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.musicplayer_hezhao.model.Music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 11120555 on 2020/7/16 11:34
 */
public class Util {
    public static String ConverSecondsToTime(long time)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("mm:ss");
        Date data=new Date(time);
        String datetime=dateFormat.format(data);
        return datetime;
    }
    public static Bitmap CreateBitmap(ContentResolver resolver, Uri albumuri)
    {
        InputStream in=null;
        Bitmap bmp=null;
        try {
            in=resolver.openInputStream(albumuri);
            BitmapFactory.Options options=new BitmapFactory.Options();
            bmp=BitmapFactory.decodeStream(in,null,options);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bmp;
    }


}
