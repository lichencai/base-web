package com.zriot.ebike.service;

import java.util.List;

import com.zriot.ebike.entity.ZuulConfigInfo;

public interface ZuulConfigInfoService {
	public List<ZuulConfigInfo> findAllList();
	public String createRouteConfig(ZuulConfigInfo info);
}
