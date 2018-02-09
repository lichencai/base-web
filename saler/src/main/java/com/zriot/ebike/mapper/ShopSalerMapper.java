package com.zriot.ebike.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zriot.ebike.dao.CrudDao;
import com.zriot.ebike.entity.ShopSaler;

@Mapper
public interface ShopSalerMapper extends CrudDao<ShopSaler> {

	ShopSaler getByMobile(String mobile);
}
