package com.example.imageloader.cache;

import android.graphics.Bitmap;

import java.util.Collection;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: MemoryCache.java
 *
 * @Author: hk
 * Date: 2019/06/14 17:47
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public interface MemoryCache {

    boolean put(String key, Bitmap bitmap);

    Bitmap  get(String key);

    Bitmap remove(String key);

    Collection<String> keys();

    void clear();
}
