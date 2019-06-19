package com.example.imageloader.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: DefaultThreadFactory.java
 *
 * @Author: hk
 * Date: 2019/06/19 18:37
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class DefaultThreadFactory implements ThreadFactory {

    AtomicInteger poolNumber = new AtomicInteger(1);
    AtomicInteger threadNumber = new AtomicInteger(1);
    ThreadGroup group;
    String  namePrefix;
    int threadPri;

    public DefaultThreadFactory(int threadPriority,String threadNamePrefix){
       this.threadPri = threadPriority;
       group = Thread.currentThread().getThreadGroup();
       namePrefix = threadNamePrefix +poolNumber.getAndIncrement()+ "-thread-";

    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread t = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) t.setDaemon(false);
        t.setPriority(threadPri);
        return t;
    }
}
