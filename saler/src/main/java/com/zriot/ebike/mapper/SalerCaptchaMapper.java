package com.zriot.ebike.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zriot.ebike.dao.CrudDao;
import com.zriot.ebike.entity.SalerCaptcha;

@Mapper
public interface SalerCaptchaMapper extends CrudDao<SalerCaptcha> {
	SalerCaptcha getByMobile(String mobile);
}
