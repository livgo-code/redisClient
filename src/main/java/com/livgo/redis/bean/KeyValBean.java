package com.livgo.redis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * key val
 * livgo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValBean {

    private String key;
    private String val;
    private Integer len;
}
