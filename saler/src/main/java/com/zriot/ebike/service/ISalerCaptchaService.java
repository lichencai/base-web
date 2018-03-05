package com.zriot.ebike.service;

import java.util.Map;

import com.zriot.ebike.exception.BusinessException;

public interface ISalerCaptchaService {
	
	/**
	 * 发送验证码
	 */
	Map<String, Object> sendVerificationCode(String mobile) throws Exception;
	
	/**
	 * 验证验证码是否正确
	 */
	void verificationCode(String mobile, String captcha) throws BusinessException;
}
