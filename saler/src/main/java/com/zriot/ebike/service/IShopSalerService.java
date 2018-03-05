package com.zriot.ebike.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zriot.ebike.entity.Paging;
import com.zriot.ebike.entity.ShopSaler;

public interface IShopSalerService {
	PageInfo<ShopSaler> queryPage(Paging page, Map<String, Object> query);
	ShopSaler getByMobile(String mobile);
}
