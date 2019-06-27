package com.example.imageloader.core.downloader;

import java.io.InputStream;
import java.util.Locale;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: BaseImageDownLoader.java
 *
 * @Author: hk
 * Date: 2019/06/27 18:07
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class BaseImageDownLoader implements  ImageDownLoader {
    @Override
    public InputStream getStream(String imageUri, Object extra) {
        for(int i =0;i<SCHEMES.length;i++){
            if(belongsTo(imageUri,SCHEMES[i])){

            }
        }
        return null;
    }

    private boolean belongsTo(String uri,String prefix) {
        return uri.toLowerCase(Locale.US).startsWith(prefix);
    }
}
