package com.livgo.redis.common;

/**
 * 常量池
 * livgo
 */
public class Constants {

    public static String REDIS_IP = "10.10.38.94";
    public static int REDIS_PORT = 6379;
    public static String REDIS_PWD = "";
    public static int REDIS_MAX_IDLE = 300;
    public static int REDIS_MAX_TOTAL = 1000;
    public static int REDIS_MAX_WAIT_MILLIS = 1000;
    public static boolean REDIS_TEST_ON_BORROW = false;


    public static String KV = "KV", MAP = "MAP", LIST = "LIST", SET = "SET", SORTSET = "SORTSET";

    public static int REDIS_RESULT_MAX_SIZE = 100;

}
