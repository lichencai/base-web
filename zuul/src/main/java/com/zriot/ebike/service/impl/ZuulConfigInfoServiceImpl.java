package com.zriot.ebike.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zriot.ebike.constant.ReturnCode;
import com.zriot.ebike.entity.ZuulConfigInfo;
import com.zriot.ebike.mapper.ZuulConfigInfoMapper;
import com.zriot.ebike.service.ZuulConfigInfoService;

@Service
@Transactional(readOnly = true)
public class ZuulConfigInfoServiceImpl implements ZuulConfigInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZuulConfigInfoServiceImpl.class);
	
	@Autowired
	private ZuulConfigInfoMapper zuulConfigInfoMapper;

	@Override
	public List<ZuulConfigInfo> findAllList() {
		List<ZuulConfigInfo> list = zuulConfigInfoMapper.findAllList();
		LOGGER.info("result list size is : " + list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public String createRouteConfig(ZuulConfigInfo info) {
		int result = zuulConfigInfoMapper.createRouteConfig(info);
		LOGGER.info("insert result is {}", result);
		return ReturnCode.SUCCESS;
	}
}
