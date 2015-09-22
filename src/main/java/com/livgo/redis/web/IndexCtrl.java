package com.livgo.redis.web;

import com.livgo.redis.service.RedisServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 首页
 * livgo
 */
@Controller
@RequestMapping("/index")
public class IndexCtrl {

    @Autowired
    private RedisServerService redisServerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        return "index";
    }
}
