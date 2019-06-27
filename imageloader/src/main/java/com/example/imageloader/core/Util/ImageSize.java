package com.example.imageloader.core.Util;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: ImageSize.java
 *
 * @Author: hk
 * Date: 2019/06/27 11:02
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImageSize {

    private  int width;
    private  int height;

    public ImageSize(int width,int height){
        this.width = width;
        this.height = height;
    }

    public ImageSize scaleDown(int sampleSize){
        return  new ImageSize(width/sampleSize,height/sampleSize);
    }

    public ImageSize scale(float scale){
       return  new ImageSize((int)(width*scale),(int)(width*scale));
    }
}
