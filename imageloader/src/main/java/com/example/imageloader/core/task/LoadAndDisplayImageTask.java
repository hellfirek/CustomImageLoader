package com.example.imageloader.core.task;

import android.graphics.Bitmap;

import com.example.imageloader.config.ImageLoadingInfo;
import com.example.imageloader.core.ImageLoadEngine;
import com.example.imageloader.core.ImageLoaderConfig;
import com.example.imageloader.core.ViewWrapper.ImageViewWrapper;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Handler;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: LoadAndDisplayImageTask.java
 *
 * @Author: hk
 * Date: 2019/06/20 17:27
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class LoadAndDisplayImageTask implements Runnable {


    ImageLoadEngine engine;
    ImageLoadingInfo info;
    ImageLoaderConfig config;
    Handler handler;

    ImageViewWrapper wrapper;
    String memoryCacheKey;
    String uri;

    public LoadAndDisplayImageTask(ImageLoadEngine engine, ImageLoadingInfo info, ImageLoaderConfig config,Handler handler) {

        this.engine = engine;
        this.info = info;
        this.handler = handler;
        this.config = config;
        this.wrapper = info.wrapper;
        this.memoryCacheKey = info.memoryCacheKey;
        this.uri = info.uri;

    }

    @Override
    public void run() {
        if(waitIfPause()){
            return;
        }
        ReentrantLock uriLock = info.lock;
        if(uriLock.isLocked()){
           //等待图片加载
        }

        uriLock.lock();
        Bitmap bmp;
        try {
            checkTaskNotActual();

            bmp = config.memoryCache.get(memoryCacheKey);
            if(bmp == null || bmp.isRecycled()){
                File file = config.diskCache.get(uri);
                if(file !=null && file.exists() && file.length()>0){

                }
            }
        } catch (TaskCancelledException e) {
            e.printStackTrace();
        }finally {
            uriLock.unlock();
        }

    }


    private boolean waitIfPause(){
        AtomicBoolean pause = engine.getPause();
        if(pause.get()){
           synchronized (engine.getPauseLock()){
               try {
                   engine.getPauseLock().wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   return true;
               }
           }
        }
        return false;
    }
    private void checkTaskNotActual() throws TaskCancelledException {
        checkViewCollected();
        checkViewReused();
    }

    /** @throws TaskCancelledException if target ImageAware is collected by GC */
    private void checkViewReused() throws TaskCancelledException {
        if (isViewReused()) {

            throw new TaskCancelledException();

        }
    }

    private void checkViewCollected() throws TaskCancelledException {
        if (isViewCollected()) {
            throw new TaskCancelledException();
        }
    }

    private boolean isViewCollected(){
        if(wrapper.isCollected()){
            return true;
        }
        return false;
    }

    private boolean isViewReused(){
      return false;
    }

    class TaskCancelledException extends Exception {
    }
}
