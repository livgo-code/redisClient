package com.livgo.redis.service;

import com.livgo.redis.bean.KeyValBean;
import com.livgo.redis.bean.RedisBean;

import java.util.List;
import java.util.Set;

/**
 * Redis 查询
 * livgo
 */
public interface RedisService {

    /**
     * 对指定服务器匹配KEY
     *
     * @param serverId
     * @param pattern
     * @return
     */
    public Set<String> getKeys(String serverId, String pattern);

    /**
     * 返回相应的KEY信息
     *
     * @param serverIds
     * @param key
     * @return
     */
    public List<RedisBean> getResult(String[] serverIds, String key);

    public List<KeyValBean> getDetail(String serverId, String key, String type);

    public List<KeyValBean> getDetailPatternMap(String serverId, String key, String k);
}
