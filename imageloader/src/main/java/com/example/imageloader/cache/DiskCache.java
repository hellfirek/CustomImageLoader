package com.example.imageloader.cache;

import android.graphics.Bitmap;

import java.io.File;
import java.io.InputStream;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: DiskCache.java
 *
 * @Author: hk
 * Date: 2019/06/19 16:41
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public interface DiskCache {

    File getSaveDictory();

    File get(String url);

    boolean save(String url, Bitmap bitmap);

    boolean remove(String url);

    void clear();
}
