package com.example.musicplayer_hezhao;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 11120555 on 2020/8/12 17:58
 */
public class PublicQueue <T>{
    private BlockingDeque<T>blockingDeque=new LinkedBlockingDeque(5);
    

}
