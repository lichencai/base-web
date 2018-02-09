package com.zriot.ebike.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zriot.ebike.entity.ZuulConfigInfo;
import com.zriot.ebike.service.ZuulConfigInfoService;

/**
 * 初始化配置routeLocator
 */
@Configuration
public class CustomZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    @Autowired
    ZuulConfigInfoService zuulConfigInfoService;

    @Bean
    public CustomRouteLocator routeLocator() {
        CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
        List<ZuulConfigInfo> lists = zuulConfigInfoService.findAllList();
        routeLocator.setZuulConfigInfos(lists);
        return routeLocator;
    }
}
