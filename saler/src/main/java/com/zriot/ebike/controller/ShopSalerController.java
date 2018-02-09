package com.zriot.ebike.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zriot.ebike.entity.Paging;
import com.zriot.ebike.entity.ShopSaler;
import com.zriot.ebike.service.IShopSalerService;

@Validated
@RestController
@RequestMapping("/shopSaler")
public class ShopSalerController extends BaseController{
	
	@Autowired
	private IShopSalerService shopSalerService;
	
	/**
	 * 根据条件查询用户列表
	 */
	@PreAuthorize("hasAuthority('saler:menu:edit')")
	@PostMapping(value = "/queryPage")
	public PageInfo<ShopSaler> queryPage(Paging page, @RequestParam Map<String, Object> query){
		PageInfo<ShopSaler> list = shopSalerService.queryPage(page, query);
		return list;
	}
	
}
