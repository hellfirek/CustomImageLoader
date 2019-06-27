package com.example.imageloader.config;

import com.example.imageloader.core.ImageLoadingListener;
import com.example.imageloader.core.Util.DisplayOption;
import com.example.imageloader.core.Util.ImageSize;
import com.example.imageloader.core.ViewWrapper.ImageViewWrapper;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ImageLoadingInfo.java
 *
 * @Author: hk
 * Date: 2019/06/27 10:50
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImageLoadingInfo {

    public String uri;
    public String memoryCacheKey;
    public ImageViewWrapper wrapper;
    public ImageSize size;
    public DisplayOption options;
    public ReentrantLock lock;
    public ImageLoadingListener listener;

    public ImageLoadingInfo(String uri, String memoryCacheKey, ImageViewWrapper wrapper, ImageSize size, DisplayOption options, ReentrantLock lock, ImageLoadingListener listener) {
        this.uri = uri;
        this.memoryCacheKey = memoryCacheKey;
        this.wrapper = wrapper;
        this.size = size;
        this.options = options;
        this.lock = lock;
        this.listener = listener;
    }
}
