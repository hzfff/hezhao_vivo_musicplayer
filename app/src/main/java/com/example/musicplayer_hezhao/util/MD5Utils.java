package com.example.musicplayer_hezhao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 11120555 on 2020/7/13 15:06
 */
//对密码进行MD5加密
public class MD5Utils   {
    public static String md5(String text) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : result){
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1){
                    sb.append("0"+hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
