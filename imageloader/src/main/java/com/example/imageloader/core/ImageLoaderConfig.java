package com.example.imageloader.core;

import com.example.imageloader.cache.DiskCache;
import com.example.imageloader.cache.MemoryCache;
import com.example.imageloader.core.decode.ImageDecoder;
import com.example.imageloader.core.downloader.ImageDownLoader;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ImageLoaderConfig.java
 *
 * @Author: hk
 * Date: 2019/06/13 19:33
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImageLoaderConfig {

     private MemoryCache memoryCache;
     private DiskCache diskCache;
     private int threadPoolSize;
     private ImageDownLoader downLoader;
     private ImageDecoder decoder;



}
