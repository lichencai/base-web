package com.zriot.ebike.entity;

/**
 * 商店实体
 */
public class Shop extends DataEntity {

	private static final long serialVersionUID = -3049389639189066315L;
	private String name;	//门店名称
	private String tel;		//联系电话
	private String address;		//门店地址
	private String description;		//门店详情
	private String latitude;		//经度
	private String longitude;		//纬度
	private String geohash;		//geohash算法计算附近的车
	private Integer status;		//门店状态:0禁用 1正常
	private String remarks;		//备注
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getGeohash() {
		return geohash;
	}
	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
