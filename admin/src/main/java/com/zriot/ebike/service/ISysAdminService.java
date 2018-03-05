package com.zriot.ebike.service;

import com.zriot.ebike.entity.SysAdmin;
import com.zriot.ebike.request.SysAdminRequest;

public interface ISysAdminService {
	/**
	 * 根据登录名查询系统管理员
	 */
	SysAdmin findByLoginName(String loginName);
	/**
	 * 保存或更新系统管理员信息
	 */
	void saveSysUser(SysAdminRequest sysAdminRequest);
	/**
	 * 根据adminId编辑系统管理员信息
	 */
	SysAdmin editSysUser(String adminId);
	/**
	 * 根据adminId删除系统管理员
	 */
	void delSysUser(String adminId);
}
