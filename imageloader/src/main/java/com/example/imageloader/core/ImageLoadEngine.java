package com.example.imageloader.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    public ImageLoadEngine(ImageLoaderConfig config){
       this.config = config;

        taskDistributor = Executors.newSingleThreadExecutor()
    }

    void submit(){

    }
}
