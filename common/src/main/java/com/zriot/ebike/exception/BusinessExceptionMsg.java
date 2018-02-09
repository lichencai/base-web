package com.zriot.ebike.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class BusinessExceptionMsg {
	
	private Locale locale;
	
	private MessageSource messageSource;
	
	public BusinessExceptionMsg() {
		
	}
	
	public String getLocalMsg(BusinessException businessException) {
		return messageSource.getMessage(businessException.getErrorDescription(), businessException.getObjs(), locale);
	}
	
	public String getLocalMsg(String errorDescription, Object[] objs) {
		return messageSource.getMessage(errorDescription, objs, locale);
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
