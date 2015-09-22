package com.livgo.redis.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * redis server config
 * livgo
 */
public class RedisConfigUtil {

    private static final ResourceBundle resource = ResourceBundle.getBundle("redis");

    public static String get(String key) {
        resource.getKeys();
        String str = "";
        try {
            str = new String(resource.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}
