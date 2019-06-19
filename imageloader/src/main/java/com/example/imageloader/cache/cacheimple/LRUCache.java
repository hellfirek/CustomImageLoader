package com.example.imageloader.cache.cacheimple;

import android.graphics.Bitmap;

import com.example.imageloader.cache.MemoryCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
           size += sizeOf(bitmap);
           Bitmap pre = map.put(key,bitmap);
           if(pre != null ){
               size -= sizeOf(pre);
           }
           resize(maxSize);
           return true;
       }
    }


    @Override
    public Bitmap get(String key) {
        if(key == null){
            throw new IllegalArgumentException(" key can not be null");
        }

        synchronized (this){
           return map.get(key);
        }

    }

    @Override
    public Bitmap remove(String key) {
        if(key == null){
            throw new IllegalArgumentException(" key can not be null");
        }
        synchronized (this){
           Bitmap old =  map.remove(key);
           size -=sizeOf(old);
           return old;
        }

    }

    @Override
    public Collection<String> keys() {
        return map.keySet();
    }

    @Override
    public void clear() {
        resize(0);
    }

    private int sizeOf(Bitmap value){
        return  value.getByteCount();
    }

    /**
     * 保存图片到内存中，要把最少使用的图片删除，删除到需要的空间为止
     * @param needSize
     */
    private void resize(int needSize){
      while(true){
          String key;
          Bitmap old;
          synchronized (this){
              //如果内存空间还是够用的
              if(size <= needSize || map.isEmpty()){
                 break;
              }

              Map.Entry<String,Bitmap> oldEntry = map.entrySet().iterator().next();
              if(oldEntry == null){
                break;
              }

              key = oldEntry.getKey();
              old = oldEntry.getValue();
              map.remove(key);
              size -= sizeOf(old);
          }
      }
    }
}
