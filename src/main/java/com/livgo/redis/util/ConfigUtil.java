package com.livgo.redis.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * config
 * livgo
 */
public class ConfigUtil {

    private static final ResourceBundle resource = ResourceBundle.getBundle("config");

    public static String get(String key) {
        String str = "";
        try {
            str = new String(resource.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}
