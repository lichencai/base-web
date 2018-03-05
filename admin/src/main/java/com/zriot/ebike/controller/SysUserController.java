package com.zriot.ebike.controller;

import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zriot.ebike.entity.SysAdmin;
import com.zriot.ebike.request.SysAdminRequest;
import com.zriot.ebike.response.MsgResponse;
import com.zriot.ebike.service.ISysAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * 后台管理员管理
 */
@Validated
@RestController
@Slf4j
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {
	
	
	@Autowired
	private ISysAdminService sysAdminService;
	
	
	@RequestMapping(value = "/saveSysUser", method = RequestMethod.POST)
	public Map<String, Object> saveSysUser(@RequestBody @Validated SysAdminRequest sysAdminRequest){
		log.info("create SysUser {}", sysAdminRequest.getLoginName());
		sysAdminService.saveSysUser(sysAdminRequest);
		return MsgResponse.responseSuccess();
	}
	
	@RequestMapping(value = "/editSysUser", method = RequestMethod.POST)
	public Map<String, Object> editSysUser(@NotBlank(message="{param.empty}") @RequestParam("adminId") String adminId){
		log.info("edit SysUser {}", adminId);
		SysAdmin user = sysAdminService.editSysUser(adminId);
		return MsgResponse.responseSuccess(user);
	}
	
	@PreAuthorize("hasRole('SYS_SUPER_ADMIN')")
	@RequestMapping(value = "/delSysUser", method = RequestMethod.POST)
	public Map<String, Object> delSysUser(@NotBlank(message="{param.empty}") @RequestParam("adminId") String adminId){
		log.info("edit SysUser {}", adminId);
		sysAdminService.delSysUser(adminId);
		return MsgResponse.responseSuccess();
	}
	
}
