package com.livgo.redis.client;

import com.livgo.redis.bean.KeyValBean;
import com.livgo.redis.bean.RedisBean;
import com.livgo.redis.common.Constants;
import com.livgo.redis.util.KeyUtil;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.*;

/**
 * 查询redis
 * livgo
 */
public class RedisClient {

//    public static void main(String[] args) {
//        RedisClient redisService = new RedisClient(1,"10.10.38.94", 7000, null);
//
//        RedisClient redisService = new RedisClient();
//        long beginTime = System.currentTimeMillis();
//        RedisBean redisBean = redisService.get("testset");
//        System.out.println( System.currentTimeMillis()-beginTime);
//        System.out.println(redisBean == null ? "NULL" : redisBean.getRtype());
//        System.out.println("-----------------------");
//         redisService = new RedisClient();
//         redisBean = redisService.get("testset");
//        System.out.println( System.currentTimeMillis()-beginTime);
//        System.out.println(redisBean == null ? "NULL" : redisBean.getRtype());
//        System.out.println("-----------------------");
//
//    }

    public List<KeyValBean> getMapOne(String key, String k) {
        Jedis jedis = getJedis();
        try {
            if (StringUtils.isEmpty(key))
                return null;
            List<KeyValBean> keyValBeans = new ArrayList<KeyValBean>();
            String result = jedis.hget(key, k);
            keyValBeans.add(new KeyValBean(k, result, result.length()));
            return keyValBeans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }


    /**
     * 获取某KEY的VALUE信息
     *
     * @param key
     * @param type
     * @return
     */
    public List<KeyValBean> getKeyVal(String key, String type) {
        Jedis jedis = getJedis();
        try {
            if (StringUtils.isEmpty(key))
                return null;
            List<KeyValBean> keyValBeans = new ArrayList<KeyValBean>();
            if (type.equals(Constants.KV)) {
                String result = jedis.get(key);
                if (!StringUtils.isEmpty(result)) {
                    keyValBeans.add(new KeyValBean(null, result, result.length()));
                }
            } else if (type.equals(Constants.MAP)) {
                ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(key, "0");
                if (null != scanResult) {
                    for (Map.Entry<String, String> entry : scanResult.getResult()) {
                        keyValBeans.add(new KeyValBean(entry.getKey(), entry.getValue(), entry.getValue().length()));
                    }
                }
            } else if (type.equals(Constants.LIST)) {
                List<String> results = jedis.lrange(key, 0, Constants.REDIS_RESULT_MAX_SIZE);
                if (null != results) {
                    for (String result : results) {
                        keyValBeans.add(new KeyValBean(null, result, result.length()));
                    }
                }
            } else if (type.equals(Constants.SET)) {
                ScanResult<String> scanResult = jedis.sscan(key, "0");
                if (null != scanResult) {
                    for (String result : scanResult.getResult()) {
                        keyValBeans.add(new KeyValBean(null, result, result.length()));
                    }
                }
            } else if (type.equals(Constants.SORTSET)) {
                ScanResult<Tuple> scanResult = jedis.zscan(key, "0");
                if (null != scanResult) {
                    for (Tuple result : scanResult.getResult()) {
                        keyValBeans.add(new KeyValBean(null, result.toString(), result.getElement().length()));
                    }
                }
            }
            return keyValBeans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }
//

    /**
     * 获取所有KEY
     *
     * @param pattern
     * @return
     */
    public Set<String> getKeys(String pattern) {
        Set<String> set = new HashSet<String>();
        Jedis jedis = null;
        try {
            jedis = getJedis();
            set = jedis.keys(pattern);
        } catch (Exception e) {
            return null;
        }
        return set;
    }

    /**
     * 获取某KEY的基本信息
     *
     * @param key
     * @return
     */
    public RedisBean getRedisBean(String key) {
        RedisBean redisBean = new RedisBean();
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (!StringUtils.isEmpty(key)) {
                String result = jedis.get(key);
                if (null == result)
                    return null;
                redisBean.setServerId(SERVER_ID);
                redisBean.setRtype(Constants.KV);
                redisBean.setRkey(key);
                redisBean.setRvalue(result);
                redisBean.setRsize(1L);
                redisBean.setRexpire(jedis.ttl(key));
                redisBean.setRname(KeyUtil.get(key));
                return redisBean;
            }
            return null;
        } catch (JedisDataException e) {
            try {
                Long hsize = jedis.hlen(key);
                redisBean.setServerId(SERVER_ID);
                redisBean.setRtype(Constants.MAP);
                redisBean.setRkey(key);
                redisBean.setRvalue(null);
                redisBean.setRsize(hsize);
                redisBean.setRexpire(jedis.ttl(key));
            } catch (JedisDataException e1) {
                try {
                    Long lsize = jedis.llen(key);
                    redisBean.setServerId(SERVER_ID);
                    redisBean.setRtype(Constants.LIST);
                    redisBean.setRkey(key);
                    redisBean.setRvalue(null);
                    redisBean.setRsize(lsize);
                    redisBean.setRexpire(jedis.ttl(key));
                } catch (JedisDataException e2) {
                    try {
                        Long ssize = jedis.scard(key);
                        redisBean.setServerId(SERVER_ID);
                        redisBean.setRtype(Constants.SET);
                        redisBean.setRkey(key);
                        redisBean.setRvalue(null);
                        redisBean.setRsize(ssize);
                        redisBean.setRexpire(jedis.ttl(key));
                    } catch (JedisDataException e3) {
                        try {
                            Long zsize = jedis.zcard(key);
                            redisBean.setServerId(SERVER_ID);
                            redisBean.setRtype(Constants.SORTSET);
                            redisBean.setRkey(key);
                            redisBean.setRvalue(null);
                            redisBean.setRsize(zsize);
                            redisBean.setRexpire(jedis.ttl(key));
                        } catch (JedisDataException e4) {
                            redisBean = null;
                        }
                    }
                }
            }
            if (null != redisBean)
                return redisBean;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    private String SERVER_ID = null;
    private String REDIS_IP = Constants.REDIS_IP;
    private int REDIS_PORT = Constants.REDIS_PORT;
    private String REDIS_PWD = Constants.REDIS_PWD;
    private int REDIS_MAX_IDLE = Constants.REDIS_MAX_IDLE;
    private int REDIS_MAX_TOTAL = Constants.REDIS_MAX_TOTAL;
    private int REDIS_MAX_WAIT_MILLIS = Constants.REDIS_MAX_WAIT_MILLIS;
    private boolean REDIS_TEST_ON_BORROW = Constants.REDIS_TEST_ON_BORROW;
    private JedisPool pool = null;


    public RedisClient() {

    }

    public RedisClient(String SERVER_ID, String REDIS_IP, int REDIS_PORT, String REDIS_PWD) {
        this.SERVER_ID = SERVER_ID;
        this.REDIS_IP = REDIS_IP;
        this.REDIS_PORT = REDIS_PORT;
        this.REDIS_PWD = REDIS_PWD;
    }


    /**
     * 获取一个jedis 客户端
     */
    public Jedis getJedis() {
        return getRedisPool().getResource();
    }

    /**
     * 构建redis连接池
     */
    public JedisPool getRedisPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //如果赋值为-1，则表示不限制；
            config.setMaxTotal(REDIS_MAX_TOTAL);
            //控制一个pool最多有多少个状态为idle空闲的的jedis实例
            config.setMaxIdle(REDIS_MAX_IDLE);
            config.setMaxWaitMillis(REDIS_MAX_WAIT_MILLIS);
            config.setTestOnBorrow(REDIS_TEST_ON_BORROW);
            if (StringUtils.isEmpty(REDIS_PWD)) {
                pool = new JedisPool(config, REDIS_IP, REDIS_PORT);
            } else {
                pool = new JedisPool(config, REDIS_IP, REDIS_PORT, Protocol.DEFAULT_TIMEOUT, REDIS_PWD);
            }
        }
        return pool;
    }


    public String getSERVER_ID() {
        return SERVER_ID;
    }

    public void setSERVER_ID(String SERVER_ID) {
        this.SERVER_ID = SERVER_ID;
    }

    public String getREDIS_IP() {
        return REDIS_IP;
    }

    public void setREDIS_IP(String REDIS_IP) {
        this.REDIS_IP = REDIS_IP;
    }

    public int getREDIS_PORT() {
        return REDIS_PORT;
    }

    public void setREDIS_PORT(int REDIS_PORT) {
        this.REDIS_PORT = REDIS_PORT;
    }

    public String getREDIS_PWD() {
        return REDIS_PWD;
    }

    public void setREDIS_PWD(String REDIS_PWD) {
        this.REDIS_PWD = REDIS_PWD;
    }

    public int getREDIS_MAX_IDLE() {
        return REDIS_MAX_IDLE;
    }

    public void setREDIS_MAX_IDLE(int REDIS_MAX_IDLE) {
        this.REDIS_MAX_IDLE = REDIS_MAX_IDLE;
    }

    public int getREDIS_MAX_TOTAL() {
        return REDIS_MAX_TOTAL;
    }

    public void setREDIS_MAX_TOTAL(int REDIS_MAX_TOTAL) {
        this.REDIS_MAX_TOTAL = REDIS_MAX_TOTAL;
    }

    public int getREDIS_MAX_WAIT_MILLIS() {
        return REDIS_MAX_WAIT_MILLIS;
    }

    public void setREDIS_MAX_WAIT_MILLIS(int REDIS_MAX_WAIT_MILLIS) {
        this.REDIS_MAX_WAIT_MILLIS = REDIS_MAX_WAIT_MILLIS;
    }

    public boolean isREDIS_TEST_ON_BORROW() {
        return REDIS_TEST_ON_BORROW;
    }

    public void setREDIS_TEST_ON_BORROW(boolean REDIS_TEST_ON_BORROW) {
        this.REDIS_TEST_ON_BORROW = REDIS_TEST_ON_BORROW;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }
}
