package com.zriot.ebike.controller;

import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zriot.ebike.service.ISalerCaptchaService;
import com.zriot.ebike.util.AESUtil;

import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@Slf4j
@RequestMapping("/salerCaptcha")
public class SalerCaptchaController extends BaseController {
	
	
	@Autowired
	private ISalerCaptchaService salerCaptchaService;
	
	/**
	 * 发送验证码
	 */
	@RequestMapping(value = "/send_verification_code", method = RequestMethod.POST)
    public Map<String, Object> sendSMSVerificationCode(@NotBlank(message="{param.empty}") String mobile) throws Exception{
        log.info("send verification phone number params : {}", mobile);
        mobile = AESUtil.aesEncryptString(mobile, "mMwWfT6MqC6XNJMs");
        return salerCaptchaService.sendVerificationCode(mobile);
    }
	
}
