package com.example.imageloader.core.ViewWrapper;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ImageViewWrapper.java
 *
 * @Author: hk
 * Date: 2019/06/13 18:27
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImageViewWrapper extends ViewWrapper{

    public ImageViewWrapper(ImageView imageView){
         super(imageView);
    }

    @Override
    public int getWidth() {
        int width =  super.getWidth();
        if(width<=0){
             ImageView imageView  = (ImageView) viewRef.get();
             if(imageView !=null){
                width = getImageViewFiledValue(imageView,"mMaxWidth");
             }
        }
        return  width;
    }


    @Override
    public int getHeight() {
        int height = super.getHeight();
        if(height<=0){
            ImageView imageView = (ImageView) viewRef.get();
            if(imageView !=null){
               height = getImageViewFiledValue(imageView,"mMaxHeight");
            }
        }
        return height;
    }

    @Override
    public void setImageDrawableInto(Drawable drawable, View view) {
        ((ImageView)view).setImageDrawable(drawable);
    }

    @Override
    public void setImageBitMapInto(Bitmap bitMap, View view) {
        ((ImageView)view).setImageBitmap(bitMap);
    }

    @Override
    public ImageView getWrapperView() {
        return (ImageView) viewRef.get();
    }

    private int getImageViewFiledValue(Object object,String fieldName){
       int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = (Integer)field.get(object);
            if(fieldValue>0 && fieldValue<Integer.MAX_VALUE){
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
