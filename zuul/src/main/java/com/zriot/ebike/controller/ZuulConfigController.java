package com.zriot.ebike.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zriot.ebike.config.CustomRouteLocator;
import com.zriot.ebike.entity.ZuulConfigInfo;
import com.zriot.ebike.service.ZuulConfigInfoService;

@RestController
public class ZuulConfigController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZuulConfigController.class);
	
	@Autowired
    ZuulConfigInfoService zuulConfigInfoService;

	@Autowired
    ZuulHandlerMapping zuulHandlerMapping;
	
	@Autowired
    ApplicationEventPublisher publisher;
	
	@Autowired
    RouteLocator routeLocator;
	
    @RequestMapping("/refreshRoute")
    public String refreshRoute(){
    	if(routeLocator instanceof CustomRouteLocator)
    		System.out.println("routeLocator iss customRouteLocator");
    	CustomRouteLocator temp = (CustomRouteLocator)routeLocator;
    	List<ZuulConfigInfo> configs = zuulConfigInfoService.findAllList();
    	temp.setZuulConfigInfos(configs);
    	RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(temp);
        publisher.publishEvent(routesRefreshedEvent);
        return "refreshRoute";
    }

    @RequestMapping("/watchNowRoute")
    public String watchNowRoute(){
        //可以用debug模式看里面具体是什么
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        LOGGER.info(handlerMap.toString());
        return "watchNowRoute";
    }
    
    @RequestMapping("/createRouteConfig")
    public Map<String, Object> createRouteConfig(ZuulConfigInfo info) {
    	zuulConfigInfoService.createRouteConfig(info);
    	return null;
    }
}
