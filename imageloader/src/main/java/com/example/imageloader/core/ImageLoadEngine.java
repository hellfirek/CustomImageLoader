package com.example.imageloader.core;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ImageLoadEngine.java
 *
 * @Author: hk
 * Date: 2019/06/13 19:30
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImageLoadEngine {

    private Executor taskDistributor;
    private Executor taskExecutor;
    private Executor taskExecutorForCachedImages;
    private ImageLoaderConfig config;

    private volatile AtomicBoolean pause = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    private WeakHashMap<String,ReentrantLock> locks = new WeakHashMap<>();

    public ImageLoadEngine(ImageLoaderConfig config) {
        this.config = config;
        taskDistributor = Executors.newCachedThreadPool(new DefaultThreadFactory(Thread.NORM_PRIORITY, "pool_d"));
        taskExecutor = createWorkerPool();
        taskExecutorForCachedImages = createWorkerPool();
    }

    void submit() {

    }

    public void pause(){
        pause.set(true);
    }

    public void resume(){
        pause.set(false);
        synchronized (pauseLock){
            pauseLock.notifyAll();
        }
    }

    private Executor createWorkerPool(){
        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
        return new ThreadPoolExecutor(config.threadPoolSize,config.threadPoolSize,0,TimeUnit.MILLISECONDS,taskQueue,new DefaultThreadFactory(Thread.NORM_PRIORITY,"pool_u"));
    }

    public ReentrantLock getLockForUri(String uri){
         ReentrantLock lock = locks.get(uri);
         if(lock == null){
             lock = new ReentrantLock();
             locks.put(uri,lock);
         }
         return lock;
    }

}
