package com.zriot.ebike;

import java.util.Locale;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.zriot.ebike.exception.BusinessExceptionMsg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SalerApp {

	private static final String accessId = "accessId";
	private static final String accessKey = "accessKey";
	private static final String accountEndpoint = "accountEndpoint";
	
	
	@Value("${langues}")
	private String langues ;
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SalerApp.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
		log.info("SalerApp started!!!");
	}
	
	@Bean  
    public LocaleResolver localeResolver() {
		Locale locale = new Locale(langues);
		FixedLocaleResolver localeResolver = new FixedLocaleResolver(locale);
        return localeResolver;  
    }
	
	@Bean(name="messageSource")
	MessageSource getMessageSource() {
		ResourceBundleMessageSource parentMessageSource = new ResourceBundleMessageSource();
		parentMessageSource.setDefaultEncoding("UTF-8");
		parentMessageSource.setBasename("i18n/messages");
		return parentMessageSource;
	}
	
	@Bean(name="validator")  
	public Validator validator() {  
	    LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();  
	    localValidatorFactoryBean.setValidationMessageSource(getMessageSource());  
	    return localValidatorFactoryBean;
	}
	
	@Bean
	public BusinessExceptionMsg getBusinessExceptionMsg() {
		BusinessExceptionMsg bean = new BusinessExceptionMsg();
		Locale locale = new Locale(langues);
		bean.setLocale(locale);
		bean.setMessageSource(getMessageSource());
		return bean;
	}
	
	@Bean
	public MNSClient getMNSClient(){
		//这里需要传入阿里云短信服务的参数
		//CloudAccount account = new CloudAccount(accessId, accessKey, accountEndpoint);
		//return account.getMNSClient();
		return null;
	}
	
}
