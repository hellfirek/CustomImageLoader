package com.example.imageloader.cache.cacheimple;

import android.graphics.Bitmap;

import com.example.imageloader.cache.MemoryCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: LRUCache.java
 *
 * @Author: hk
 * Date: 2019/06/14 18:00
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class LRUCache implements MemoryCache {

    private final LinkedHashMap<String,Bitmap> map;

    private final int maxSize;
    private int size;

    public LRUCache(int maxSize){
       if(maxSize<=0){
          throw new IllegalArgumentException("size can not be 0");
       }
       this.maxSize = maxSize;
       this.map = new LinkedHashMap<String,Bitmap>(0,0.75f,true);
    }

    @Override
    public boolean put(String key, Bitmap bitmap) {
       if(key == null || bitmap == null){
           throw  new IllegalArgumentException("arg can not be null");
       }

       synchronized (this){
           size += sizeOf();
           Bitmap pre = map.put(key,bitmap);
           if(pre != null ){

           }
       }
    }

    @Override
    public Bitmap get(String key) {
        return null;
    }

    @Override
    public Bitmap remove(String key) {
        return null;
    }

    @Override
    public Collection<String> keys() {
        return null;
    }

    @Override
    public void clear() {

    }

    private int sizeOf(Bitmap value){}
}
