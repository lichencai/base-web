package com.zriot.ebike.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zriot.ebike.entity.Shop;
import com.zriot.ebike.service.IShopService;

@Service
@Transactional(readOnly = true)
public class ShopService implements IShopService{

	@Override
	public int createShop(Shop shop) {
		return 0;
	}
}
