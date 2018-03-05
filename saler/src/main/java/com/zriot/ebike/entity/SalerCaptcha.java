package com.zriot.ebike.entity;

import java.util.Date;

/**
 * 验证码记录表
 */
public class SalerCaptcha extends DataEntity {

	private static final long serialVersionUID = 887309013339149044L;
	private String mobile;
	private String captcha;
	private Date expiryDate;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
