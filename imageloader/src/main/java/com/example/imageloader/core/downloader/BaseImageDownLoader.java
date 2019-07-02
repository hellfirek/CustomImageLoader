package com.example.imageloader.core.downloader;

import android.net.Uri;

import com.example.imageloader.core.Util.ContentLengthInputStream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 5 * 1000; // milliseconds
    /** {@value} */
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 20 * 1000; // milliseconds
    protected static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    private  static final int MAX_REDIRECT_COUNT = 5;
    /** {@value} */
    protected static final int BUFFER_SIZE = 32 * 1024; // 32 Kb

    @Override
    public InputStream getStream(String imageUri, Object extra) {
     switch (Scheme.ofUri(imageUri)){
         case HTTP:
         case HTTPS:
             return getStreamFromNetwork(imageUri,extra);

         case FILE:

     }
    }

    protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
        HttpURLConnection connection = createConnection(imageUri,extra);

        int redirectCount = 0;
        while (connection.getResponseCode() / 100 == 3 && redirectCount < MAX_REDIRECT_COUNT) {
            connection = createConnection(connection.getHeaderField("Location"), extra);
            redirectCount++;
        }

        InputStream inputStream = null;
       try{
           inputStream = connection.getInputStream();
       }
        catch (Exception e){
           e.printStackTrace();
        }
        return  new ContentLengthInputStream(new BufferedInputStream(inputStream,BUFFER_SIZE),connection.getContentLength());

    }

    protected InputStream getStreamFromFile(String imageUri, Object extra) throws IOException {

    }

    protected HttpURLConnection createConnection(String url, Object extra) throws IOException {
        String encodedUrl = Uri.encode(url, ALLOWED_URI_CHARS);
        HttpURLConnection conn = (HttpURLConnection) new URL(encodedUrl).openConnection();
        conn.setConnectTimeout(DEFAULT_HTTP_CONNECT_TIMEOUT);
        conn.setReadTimeout(DEFAULT_HTTP_READ_TIMEOUT);
        return conn;
    }
}
