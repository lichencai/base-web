package com.zriot.ebike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zriot.ebike.entity.SysAdmin;
import com.zriot.ebike.entity.SysMenu;
import com.zriot.ebike.mapper.SysAdminMapper;
import com.zriot.ebike.mapper.SysMenuMapper;
import com.zriot.ebike.mapper.SysRoleMapper;
import com.zriot.ebike.request.SysAdminRequest;
import com.zriot.ebike.service.CrudService;
import com.zriot.ebike.service.ISysAdminService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class SysAdminService extends CrudService<SysAdminMapper, SysAdmin> implements ISysAdminService {
	
	
	@Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
	
	public SysAdmin findByLoginName(String loginName) {
		log.info("{} is trying login", loginName);
		SysAdmin admin = getDao().findByLoginName(loginName);
		String adminId = admin.getAdminId();
		admin.setRoles(sysRoleMapper.findListBySalerId(adminId));
		List<SysMenu> menuList;
		//超级管理员
        if (SysAdmin.SUPER_ADMIN_ID.equals(adminId)) {
            menuList = sysMenuMapper.findAllList();
        } else {
            menuList = sysMenuMapper.findListBySalerId(adminId);
        }
		admin.setMenus(menuList);
		return admin;
	}

	@Transactional(readOnly = false)
	@Override
	public void saveSysUser(SysAdminRequest sysAdminRequest) {
	}

	@Override
	public SysAdmin editSysUser(String adminId) {
		return null;
	}

	@Override
	public void delSysUser(String adminId) {
	}
}
