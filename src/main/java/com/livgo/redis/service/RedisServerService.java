package com.livgo.redis.service;

import com.livgo.redis.bean.RedisServerBean;

import java.util.List;

/**
 * Redis服务器
 * livgo
 */
public interface RedisServerService {

    /**
     * 获取Redis 服务器的配置信息
     */
    public List<RedisServerBean> getRedisServer();

    /**
     * 通过serviceId获取server信息
     */
    public RedisServerBean getRedisServerById(String id);
}
