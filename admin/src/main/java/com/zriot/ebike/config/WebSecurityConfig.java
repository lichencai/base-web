package com.zriot.ebike.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import com.zriot.ebike.security.config.AbstractWebSecurityConfig;

@Configuration
@Order(0)
public class WebSecurityConfig extends AbstractWebSecurityConfig {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略权限校验的访问路径
        web
            .ignoring()
            .antMatchers(
                "/**/sms/captcha",
                "/**/user/password"
            )
            .antMatchers(HttpMethod.POST, "/**/user/registry")
        ;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/**/auth/token").permitAll()
            .antMatchers(HttpMethod.POST, "/**/salerCaptcha/send_verification_code").permitAll();
        super.configure(security);
    }
}
