package com.zriot.ebike.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zriot.ebike.entity.ShopSaler;
import com.zriot.ebike.security.model.AuthUserFactory;
import com.zriot.ebike.service.IShopSalerService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 用户服务
     */
    @Autowired
    private IShopSalerService shopSalerService;
    
    @Override
    public UserDetails loadUserByUsername(String mobile) {
    	ShopSaler shopSaler = shopSalerService.getByMobile(mobile);
        if (shopSaler == null) {
            throw new UsernameNotFoundException(String.format("根据手机号码'%s'查找不到用户.", mobile));
        } else {
            return AuthUserFactory.create(shopSaler);
        }
    }
}
