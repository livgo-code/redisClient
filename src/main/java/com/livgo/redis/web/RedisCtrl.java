package com.livgo.redis.web;

import com.livgo.redis.bean.KeyValBean;
import com.livgo.redis.bean.RedisDetailBean;
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
 * Redis
 * livgo
 */
@Controller
@RequestMapping("/redis")
public class RedisCtrl {
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisServerService redisServerService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
        List<RedisServerBean> redisServerBeans = redisServerService.getRedisServer();
        session.setAttribute("sessionServers", redisServerBeans);
        modelMap.put("servers", redisServerBeans);
        return "redis";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String getResult(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
        String[] serverIds = request.getParameterValues("serverIds");
        String key = request.getParameter("key");
        if (null == serverIds || serverIds.length == 0 || StringUtils.isEmpty(key)) {
            return "redirect:index";
        }
        modelMap.put("redisBeans", redisService.getResult(serverIds, key));
        List<RedisServerBean> redisServerBeans = (List<RedisServerBean>) session.getAttribute("sessionServers");
        modelMap.put("servers", redisServerBeans);
        modelMap.put("key", key);
        return "redis";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String getDetail(ModelMap modelMap, HttpServletRequest request) {
        String serverId = request.getParameter("serverId");
        String key = request.getParameter("key");
        String type = request.getParameter("type");
        String size = request.getParameter("size");
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(type)) {
            return "detail";
        }

        List<KeyValBean> keyValBeans = redisService.getDetail(serverId, key, type);

        RedisDetailBean redisDetailBean = new RedisDetailBean(serverId, key, type, StringUtils.isEmpty(size) ? 0L : Long.parseLong(size), keyValBeans);
        modelMap.put("redisDetailBean", redisDetailBean);
        return "detail";
    }

    @RequestMapping(value = "/detail/pattern/map", method = RequestMethod.POST)
    public String getDetailPatternMap(ModelMap modelMap, HttpServletRequest request) {
        String serverId = request.getParameter("serverId");
        String key = request.getParameter("key");
        String type = request.getParameter("type");
        String size = request.getParameter("size");
        String k = request.getParameter("k");
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(type)) {
            return "detail";
        }

        List<KeyValBean> keyValBeans = redisService.getDetailPatternMap(serverId, key, k);

        RedisDetailBean redisDetailBean = new RedisDetailBean(serverId, key, type, StringUtils.isEmpty(size) ? 0L : Long.parseLong(size), keyValBeans);
        modelMap.put("redisDetailBean", redisDetailBean);
        return "detail";
    }


}
