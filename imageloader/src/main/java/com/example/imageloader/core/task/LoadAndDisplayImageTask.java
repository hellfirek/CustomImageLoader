package com.example.imageloader.core.task;

import com.example.imageloader.config.ImageLoadingInfo;
import com.example.imageloader.core.ImageLoadEngine;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: LoadAndDisplayImageTask.java
 *
 * @Author: hk
 * Date: 2019/06/20 17:27
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class LoadAndDisplayImageTask implements Runnable {


    ImageLoadEngine engine;
    ImageLoadingInfo info;



    @Override
    public void run() {
        if(waitIfPause()){
            return;
        }

    }


    private boolean waitIfPause(){

    }
}
