package com.livgo.redis.service.impl;

import com.livgo.redis.bean.RedisServerBean;
import com.livgo.redis.client.RedisClient;
import com.livgo.redis.service.RedisServerService;
import com.livgo.redis.util.RedisConfigUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisServerServiceImpl implements RedisServerService {

    /**
     * 获取Redis 服务器的配置信息
     */
    public List<RedisServerBean> getRedisServer() {
        List<RedisServerBean> lst = new ArrayList<RedisServerBean>();
        String redisServer = RedisConfigUtil.get("redises");
        String[] redises = redisServer.split(",");
        String ip = null;
        Integer port = null;
        String pwd = null;
        String serverId = null;
        for (String redisStr : redises) {
            String[] bean = redisStr.split(":");
            serverId = bean[0];
            ip = bean[1];
            port = Integer.parseInt(bean[2]);
            if (bean.length == 4) {
                pwd = bean[3];
            }
            RedisClient redisClient = new RedisClient(serverId, ip, port, pwd);
            Integer status = null;
            String exception = null;
            Jedis jedis = null;
            try {
                jedis = redisClient.getJedis();
                status = 1;
            } catch (Exception e) {
                exception = e.getMessage();
                status = 0;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
            RedisServerBean redisServerBean = new RedisServerBean(serverId, ip, port, pwd, status, exception);
            lst.add(redisServerBean);
        }
        return lst;
    }

    /**
     * 通过serviceId获取server信息
     */
    public RedisServerBean getRedisServerById(String id) {
        RedisServerBean redisServerBean = new RedisServerBean();
        String redisServer = RedisConfigUtil.get("redises");
        String[] redises = redisServer.split(",");
        String ip = null;
        Integer port = null;
        String pwd = null;
        for (String redisStr : redises) {
            String[] bean = redisStr.split(":");
            String serverId = bean[0];
            if (id.equals(serverId)) {
                ip = bean[1];
                port = Integer.parseInt(bean[2]);
                if (bean.length == 4) {
                    pwd = bean[3];
                }
                RedisClient redisClient = new RedisClient(serverId, ip, port, pwd);
                Integer status = null;
                String exception = null;
                Jedis jedis = null;
                try {
                    jedis = redisClient.getJedis();
                    status = 1;
                } catch (Exception e) {
                    exception = e.getMessage();
                    status = 0;
                } finally {
                    if (null != jedis) {
                        jedis.close();
                    }
                }
                redisServerBean.setId(serverId);
                redisServerBean.setIp(ip);
                redisServerBean.setPort(port);
                redisServerBean.setPwd(pwd);
                redisServerBean.setStatus(status);
                redisServerBean.setException(exception);
                break;
            }
        }
        return redisServerBean;
    }
}
