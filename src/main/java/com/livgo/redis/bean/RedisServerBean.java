package com.livgo.redis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * redis server bean
 * livgo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisServerBean {

    private String id;
    private String ip;
    private Integer port;
    private String pwd;
    private Integer status;//连接状态 1可连接 0不可连接
    private String exception;//异常信息

}
