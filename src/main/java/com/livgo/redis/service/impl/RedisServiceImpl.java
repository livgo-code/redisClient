package com.livgo.redis.service.impl;

import com.livgo.redis.bean.KeyValBean;
import com.livgo.redis.bean.RedisBean;
import com.livgo.redis.bean.RedisServerBean;
import com.livgo.redis.client.RedisClient;
import com.livgo.redis.service.RedisServerService;
import com.livgo.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisServerService redisServerService;

    public Set<String> getKeys(String serverId, String pattern) {
        RedisClient redisClient = getRedisClient(serverId);
        return redisClient == null ? null : redisClient.getKeys(pattern);
    }


    public List<RedisBean> getResult(String[] serverIds, String key) {
        List<RedisBean> redisBeans = new ArrayList<RedisBean>();
        for (String serverId : serverIds) {
            if (!StringUtils.isEmpty(serverId)) {
                RedisClient redisClient = getRedisClient(serverId);
                RedisBean redisBean = redisClient.getRedisBean(key);
                if (null != redisBean) {
                    redisBeans.add(redisBean);
                }
            }
        }
        return redisBeans;
    }

    public List<KeyValBean> getDetail(String serverId, String key, String type) {
        RedisClient redisClient = getRedisClient(serverId);
        return redisClient.getKeyVal(key, type);
    }

    public List<KeyValBean> getDetailPatternMap(String serverId, String key, String k) {
        RedisClient redisClient = getRedisClient(serverId);
        return redisClient.getMapOne(key, k);
    }

    private RedisClient getRedisClient(String serverId) {
        RedisServerBean redisServerBean = redisServerService.getRedisServerById(serverId);
        if (null == redisServerBean || redisServerBean.getStatus() == 0) {
            return null;
        }
        return new RedisClient(serverId, redisServerBean.getIp(), redisServerBean.getPort(), redisServerBean.getPwd());
    }
}
