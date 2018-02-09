package com.zriot.ebike.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

public class ShopSaler extends DataEntity {

	private static final long serialVersionUID = -3051577789533440381L;
	
	public static final String PASSWORD = "zriot";
	public static final String PASSWORD_ENCODE = "$2a$08$eD8gfgug4VIcYOzGXJa9zOAKIOFHQdzHnr.9Z54NLfQib8Z/p.Cva";
	public static final String ADMIN_SALER_ID = "0";
	
	private String salerId;
	private String name;
	private String avatar;
	private Integer gender;
	private Double money;
	private String email;
	private String mobile;
	private Boolean enabled;
	private Integer shopId;
	private String remarks;
    private List<SysRole> roles = new ArrayList<>();
    private List<SysMenu> menus = new ArrayList<>();
	
	public String getSalerId() {
		return salerId;
	}
	public void setSalerId(String salerId) {
		this.salerId = salerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	@Length(min = 0, max = 255)
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
	public static String getPassword() {
		return PASSWORD;
	}
	public static String getPasswordencode() {
		return PASSWORD_ENCODE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
