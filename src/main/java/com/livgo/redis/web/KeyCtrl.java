package com.livgo.redis.web;

import com.livgo.redis.bean.KeyBean;
import com.livgo.redis.service.RedisKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Key
 * livgo
 */
@Controller
@RequestMapping("/key")
public class KeyCtrl {

    @Autowired
    private RedisKeyService redisKeyService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getKey(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
        List<KeyBean> keyBeans = redisKeyService.getKeys();
//        session.setAttribute("sessionKeys", keyBeans);
        modelMap.put("keyBeans", keyBeans);
        return "keys";
    }

}
