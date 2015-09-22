package com.livgo.redis.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * key value
 * livgo
 */
public class KeyUtil {

    private static final ResourceBundle resource = ResourceBundle.getBundle("kv");

    public static String get(String key) {
        String str = "";
        try {
            str = new String(resource.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static Set<String> keySet() {
        return resource.keySet();
    }



}
