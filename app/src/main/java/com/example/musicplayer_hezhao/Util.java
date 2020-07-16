package com.example.musicplayer_hezhao;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

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
