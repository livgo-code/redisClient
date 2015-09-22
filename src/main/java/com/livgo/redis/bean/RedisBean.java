package com.livgo.redis.bean;

import lombok.Data;

/**
 * redis bean
 * livgo
 */
@Data
public class RedisBean {

    private String serverId;//服务器ID
    private String rtype;//数据类型
    private String rkey;
    private String rname;//key name
    private String rvalue;
    private Long rsize;//数据大小
    private Long rexpire;//过期时间

}
