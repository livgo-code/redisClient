package com.livgo.redis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 详细信息
 * livgo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisDetailBean {

    private String serverId;
    private String key;
    private String type;
    private Long size;
    private List<KeyValBean> keyValBeans;

}
