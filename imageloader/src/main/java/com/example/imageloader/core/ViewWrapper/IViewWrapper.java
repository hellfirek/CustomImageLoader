package com.example.imageloader.core.ViewWrapper;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ViewWrapper.java
 *
 * @Author: hk
 * Date: 2019/06/13 18:07
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public interface IViewWrapper {
    int getWidth();

    int getHeight();

    View getWrapperView();

    boolean isCollected();

    int getId();

    boolean setImageDrawable(Drawable drawable);

    boolean setImageBitmap(Bitmap bitmap);
}
