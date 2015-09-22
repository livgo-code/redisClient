package com.livgo.redis.service.impl;

import com.livgo.redis.bean.KeyBean;
import com.livgo.redis.service.RedisKeyService;
import com.livgo.redis.util.KeyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RedisKeyServiceImpl implements RedisKeyService {

    /**
     * 获取全部key
     *
     * @return
     */
    public List<KeyBean> getKeys() {
        List<KeyBean> lst = new ArrayList<KeyBean>();
        Set<String> set = KeyUtil.keySet();
        for (String key : set) {
            KeyBean keyBean = new KeyBean(key, KeyUtil.get(key));
            lst.add(keyBean);
        }
        return lst;
    }


}
