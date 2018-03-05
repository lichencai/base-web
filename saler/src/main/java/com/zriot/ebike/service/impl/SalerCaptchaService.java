package com.zriot.ebike.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zriot.ebike.constant.Message;
import com.zriot.ebike.constant.ReturnCode;
import com.zriot.ebike.entity.SalerCaptcha;
import com.zriot.ebike.entity.ShopSaler;
import com.zriot.ebike.exception.BusinessException;
import com.zriot.ebike.mapper.SalerCaptchaMapper;
import com.zriot.ebike.mapper.ShopSalerMapper;
import com.zriot.ebike.response.MsgResponse;
import com.zriot.ebike.service.CrudService;
import com.zriot.ebike.service.ISalerCaptchaService;
import com.zriot.ebike.service.MessageSendBaseService;
import com.zriot.ebike.util.AESUtil;
import com.zriot.ebike.util.DateTimeUtil;
import com.zriot.ebike.util.RandomUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@ConfigurationProperties("aes")
@Slf4j
@Transactional(readOnly = true)
public class SalerCaptchaService extends CrudService<SalerCaptchaMapper, SalerCaptcha> implements ISalerCaptchaService{

	/**
	 * 重发验证码时间 60秒
	 */
	private static final long MIN_RESEND_PERIOD = 60 * 1000;
	
	/**
	 * 验证码的有效时间 10分钟
	 */
	private static final long EXPIRE_PERIOD = 10 * 60 * 1000;
	
	private String encryption;
	
	@Autowired
	private ShopSalerMapper shopSalerMapper;
	
	@Autowired
	private SalerCaptchaMapper salerCaptchaMapper;
	
	@Autowired
	private MessageSendBaseService messageSendBaseService;
	
	@Transactional(readOnly = false)
	@Override
	public Map<String, Object> sendVerificationCode(String mobile) throws Exception{
		String phone = AESUtil.aesDecryptString(mobile, encryption);
		log.info("send captcha mobile : {}", phone);
		//  TODO 校验手机号码的有效性
		
		ShopSaler saler = shopSalerMapper.getByMobile(phone);
		if(saler == null) {
			throw new BusinessException(ReturnCode.USER_NOT_EXIST, "user not exist", "user.not.exist", new Object[] {phone});
		}
		
		SalerCaptcha captcha = salerCaptchaMapper.getByMobile(phone);
		String code = null;
		if(captcha == null) {
			code = RandomUtil.getRandNum(6);
			captcha = new SalerCaptcha();
			captcha.setCaptcha(code);
			Date now = DateTimeUtil.now();
			captcha.setCreateDate(now);
			Date expire = DateTimeUtil.getAfterDate(EXPIRE_PERIOD);
			captcha.setExpiryDate(expire);
			captcha.setMobile(phone);
		}else {
			captcha.setIsNewRecord(true);
			Date createDate = captcha.getCreateDate();
			Date now = DateTimeUtil.now();
			if(now.getTime() - createDate.getTime() < MIN_RESEND_PERIOD) {
				throw new BusinessException(ReturnCode.SMS_TOO_MUCH, "Frequent text messages", "Frequent.text.messages", null);
			}
			Date expire = captcha.getExpiryDate();
			if(expire.getTime() < now.getTime()) {
				code = RandomUtil.getRandNum(6);
				captcha.setCaptcha(code);
			}
		}
		//  保存或更新记录表
		save(captcha);
		
		//  TODO 发送短信
		//messageSendBaseService.sendMessage(phoneNumber, paramMap, freeSignName, templateCode)
		return MsgResponse.responseSuccess();
	}

	@Override
	public void verificationCode(String mobile, String captcha) throws BusinessException{
		SalerCaptcha model = salerCaptchaMapper.getByMobile(mobile);
		if(model == null) {
			throw new BusinessException(ReturnCode.FAIL, "captcha not exist", "captcha.not.exist", new Object[] {mobile});
		}
		//  验证码会不会过期以及是否正确
		Date now = DateTimeUtil.now();
		Date expire = model.getExpiryDate();
		if(now.getTime() > expire.getTime()) {
			throw new BusinessException(ReturnCode.FAIL, "captcha expire", "captcha.expire", null);
		}
		if(!model.getCaptcha().equals(captcha)) {
			throw new BusinessException(ReturnCode.FAIL, "captcha error", "captcha.error", null);
		}
	}
	
	public String getEncryption() {
		return encryption;
	}

	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

}
