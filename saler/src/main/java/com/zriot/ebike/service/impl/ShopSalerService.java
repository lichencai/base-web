package com.zriot.ebike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zriot.ebike.entity.ShopSaler;
import com.zriot.ebike.entity.SysMenu;
import com.zriot.ebike.mapper.ShopSalerMapper;
import com.zriot.ebike.mapper.SysMenuMapper;
import com.zriot.ebike.mapper.SysRoleMapper;
import com.zriot.ebike.service.CrudService;
import com.zriot.ebike.service.IShopSalerService;

@Service
@Transactional(readOnly = true)
public class ShopSalerService extends CrudService<ShopSalerMapper, ShopSaler> implements IShopSalerService {

	
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
	
	@Override
	public ShopSaler getByMobile(String mobile) {
		ShopSaler saler = getDao().getByMobile(mobile);
		String salerId = saler.getSalerId();
		saler.setRoles(sysRoleMapper.findListBySalerId(salerId));
		List<SysMenu> menuList;
		//超级管理员
        if (ShopSaler.ADMIN_SALER_ID.equals(salerId)) {
            menuList = sysMenuMapper.findAllList();
        } else {
            menuList = sysMenuMapper.findListBySalerId(salerId);
        }
		saler.setMenus(menuList);
		return saler;
	}
	
}
