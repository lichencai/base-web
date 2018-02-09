package com.zriot.ebike.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zriot.ebike.dao.CrudDao;
import com.zriot.ebike.entity.ZuulConfigInfo;

@Mapper
public interface ZuulConfigInfoMapper extends CrudDao<ZuulConfigInfo> {
	List<ZuulConfigInfo> findAllList();
	int createRouteConfig(ZuulConfigInfo info);
}
