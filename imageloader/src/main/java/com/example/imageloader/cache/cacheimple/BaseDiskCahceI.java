package com.example.imageloader.cache.cacheimple;

import android.graphics.Bitmap;

import com.example.imageloader.cache.DiskCache;
import com.example.imageloader.core.nameGenerator.FileNameGenerator;
import com.example.imageloader.core.nameGenerator.Md5FileNameGenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Copyright (C), 2019, 广州雷猴软件有限公司
 * FileName: BaseDiskCahceI.java
 *
 * @Author: hk
 * Date: 2019/07/01 18:03
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class BaseDiskCahceI implements DiskCache {

    //32KB
    public static final int DEFAULT_BUFFER_SIZE = 32 * 1024;
    FileNameGenerator generator;

    private File cacheDir;
    public static final String TEMP_IMAGE_POSFFIX = ".temp";

    public BaseDiskCahceI(File cacheDir) {
        this.cacheDir = cacheDir;
        generator = new Md5FileNameGenerator();

    }

    @Override
    public File getSaveDictory() {
        return cacheDir;
    }

    @Override
    public File get(String url) {
        return getFile(url);
    }

    @Override
    public boolean save(String url, Bitmap bitmap) {
        return false;
    }

    @Override
    public boolean save(String url, InputStream inputStream) {
        File imageFile = getFile(url);
        File tmpFile = new File(imageFile.getAbsolutePath() + TEMP_IMAGE_POSFFIX);
        OutputStream os = null;

        try {
            os = new BufferedOutputStream(new FileOutputStream(tmpFile), DEFAULT_BUFFER_SIZE);
            int current = 0;
            int count = 0;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

            while ((count = inputStream.read(bytes, 0, DEFAULT_BUFFER_SIZE)) != -1) {
                os.write(bytes, 0, count);
                current += count;
            }
            os.flush();
            tmpFile.renameTo(imageFile);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(tmpFile!=null){
                tmpFile.delete();
            }

        }



    }

    @Override
    public boolean remove(String url) {
        return false;
    }

    @Override
    public void clear() {

    }

    private File getFile(String imageUrl) {
        String fileName = generator.generate(imageUrl);
        File dir = cacheDir;
        return new File(dir, fileName);
    }
}
