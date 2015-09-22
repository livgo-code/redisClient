package com.livgo.redis.service;

import com.livgo.redis.bean.KeyBean;

import java.util.List;

/**
 * Redis keys 自定义字典数据
 * livgo
 */
public interface RedisKeyService {

    /**
     * 获取全部key
     */
    public List<KeyBean> getKeys();

}
