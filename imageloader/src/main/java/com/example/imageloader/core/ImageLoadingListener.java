package com.example.imageloader.core;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ImageLoadingListener.java
 *
 * @Author: hk
 * Date: 2019/06/19 17:05
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public interface ImageLoadingListener {
    void onLoadingStarted(String uri,View view);

    void onLoadingFailed(String uri,View view);

    void onLoadingComplete(String uri,View view ,Bitmap bitmap);

    void onLoadingCancle(String uri,View view);
}
