package com.livgo.redis.web;

import com.livgo.redis.bean.RedisServerBean;
import com.livgo.redis.service.RedisServerService;
import com.livgo.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Monitor
 * livgo
 */
@Controller
@RequestMapping("/monitor")
public class MonitorCtrl {

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisServerService redisServerService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        List<RedisServerBean> redisServerBeans = redisServerService.getRedisServer();
        session.setAttribute("sessionServers", redisServerBeans);
        modelMap.put("servers", redisServerBeans);
        return "monitor";
    }

    @RequestMapping(value = "/pattern", method = RequestMethod.POST)
    public String getKeys(ModelMap modelMap, HttpServletRequest request, HttpSession session) {

        String serverId = request.getParameter("serverId");
        String pattern = request.getParameter("pattern");
        if (StringUtils.isEmpty(serverId) || StringUtils.isEmpty(pattern)) {
            return "redirect:index";
        }
        modelMap.put("keySet", redisService.getKeys(serverId, pattern));
        List<RedisServerBean> redisServerBeans = (List<RedisServerBean>) session.getAttribute("sessionServers");
        modelMap.put("servers", redisServerBeans);
        modelMap.put("pattern", pattern);
        return "monitor";
    }

}
