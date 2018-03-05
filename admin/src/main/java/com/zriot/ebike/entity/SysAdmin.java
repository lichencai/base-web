package com.zriot.ebike.entity;

import java.util.ArrayList;
import java.util.List;

public class SysAdmin extends DataEntity {

	private static final long serialVersionUID = -5793530681997109230L;

	public static final String SUPER_ADMIN_ID = "0";

	private String adminId;
	private String loginName;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String mobile;
	private Boolean enabled;
	private String remarks;
	private List<SysRole> roles = new ArrayList<>();
	private List<SysMenu> menus = new ArrayList<>();
	
	public String getAdminId() {
		return adminId;
	}
	
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
