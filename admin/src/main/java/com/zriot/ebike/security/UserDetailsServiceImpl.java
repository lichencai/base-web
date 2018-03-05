package com.zriot.ebike.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zriot.ebike.entity.SysAdmin;
import com.zriot.ebike.security.model.AuthUserFactory;
import com.zriot.ebike.service.ISysAdminService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 用户服务
     */
    @Autowired
    private ISysAdminService sysAdminService;
    
    @Override
    public UserDetails loadUserByUsername(String loginName) {
    	SysAdmin sysAdmin = sysAdminService.findByLoginName(loginName);
        if (sysAdmin == null) {
            throw new UsernameNotFoundException(String.format("根据登录名'%s'查找不到用户.", loginName));
        } else {
            return AuthUserFactory.create(sysAdmin);
        }
    }
}
