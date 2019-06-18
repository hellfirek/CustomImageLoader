package com.example.imageloader;

import android.util.Log;
import android.widget.ImageView;

import com.example.imageloader.core.ImageLoadEngine;
import com.example.imageloader.core.ImageLoaderConfig;
import com.example.imageloader.util.Contance;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ImageLoader.java
 *
 * @Author: hk
 * Date: 2019/06/13 17:57
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImageLoader {
    public static volatile ImageLoader instance;

    ImageLoaderConfig config;
    ImageLoadEngine engie;

    private ImageLoader(){

    }

    public static ImageLoader getInstance(){
        if(instance == null){
             synchronized (ImageLoader.class){
                 if(instance == null){
                     instance = new ImageLoader();
                 }
             }
        }

        return instance;
    }

    public synchronized  void init(ImageLoaderConfig config){
        if(config == null){
            throw new IllegalArgumentException("config can not be null");
        }
        if(config == null ){
            engie = new ImageLoadEngine();
            this.config = config;
        }else{
            Log.i(Contance.TAG,"already init");
        }

    }

    public void displayImage(String url, ImageView imageView){

    }


    public void pause(){

    }

    public void resume(){

    }

    public void destory(){

    }
}
