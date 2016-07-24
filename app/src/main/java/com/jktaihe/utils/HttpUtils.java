package com.jktaihe.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */
public class HttpUtils {

    public static InputStream getStreamFromURL(String imageURL) {
        InputStream in = null;
        try {
            URL url = new URL(imageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            in = connection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }


}