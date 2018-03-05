package com.zriot.ebike.request;

import org.hibernate.validator.constraints.NotBlank;

public class SysAdminRequest {
	@NotBlank(message="param.empty")
	private String loginName;
	@NotBlank(message="param.empty")
	private String password;
	@NotBlank(message="param.empty")
	private String name;
	private String email;
	private String phone;
	private String mobile;
	private Boolean enabled;
	private String remarks;
	
	/**
	 * 角色id 按,分隔
	 */
	@NotBlank(message="param.empty")
	private String roleIds;
	/**
	 * 菜单id 按,分隔
	 */
	private String menuIds;
	
	
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

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
}
