package com.example.imageloader.core.ViewWrapper;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ViewWrapper.java
 *
 * @Author: hk
 * Date: 2019/06/13 18:37
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public abstract class ViewWrapper implements IViewWrapper {


    protected Reference<View> viewRef;

    public ViewWrapper(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View can not be null");
        }

        this.viewRef = new WeakReference<>(view);
    }

    @Override
    public int getWidth() {
        View view = viewRef.get();
        if (view == null) {
            return 0;
        }
        final ViewGroup.LayoutParams params = view.getLayoutParams();

        int width = 0;
        if (params != null && params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
            width = params.width;
        }
        return width;

    }


    @Override
    public int getHeight() {
        View view = viewRef.get();
        if (view == null) {
            return 0;
        }
        final ViewGroup.LayoutParams params = view.getLayoutParams();

        int height = 0;
        if (params != null && params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
            height = params.height;
        }
        return height;
    }

    @Override
    public boolean isCollected() {
        return viewRef.get() == null;
    }

    @Override
    public int getId() {
        View view = viewRef.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

    @Override
    public boolean setImageDrawable(Drawable drawable) {
        if(Looper.myLooper() == Looper.getMainLooper()){
            View view = viewRef.get();
            if(view != null){
                setImageDrawableInto(drawable,view);
                return  true;
            }
        }
        return false;
    }




    @Override
    public boolean setImageBitmap(Bitmap bitmap) {
        if(Looper.myLooper() == Looper.getMainLooper()){
            View view = viewRef.get();
            if(view != null){
                setImageBitMapInto(bitmap,view);
                return  true;
            }
        }
        return false;
    }

    public abstract  void setImageDrawableInto(Drawable drawable, View view);

    public abstract  void setImageBitMapInto(Bitmap bitMap,View view);
}
