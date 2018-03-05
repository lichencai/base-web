package com.zriot.ebike.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zriot.ebike.entity.SysAdmin;
import com.zriot.ebike.entity.SysMenu;
import com.zriot.ebike.entity.SysRole;
import com.zriot.ebike.util.StringHelper;

public class AuthUser extends AbstractAuthUser {

	private static final long serialVersionUID = 2267584006596443218L;
	/**
	 * 用户默认角色
	 */
	private static final String GUEST_ROLE = "GUEST_USER";
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 手机号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 冻结
	 */
	private boolean enabled;
	/**
	 * 权限
	 */
	private Collection<SimpleGrantedAuthority> authorities;

	public AuthUser(SysAdmin sysAdmin) {
		this.id = sysAdmin.getId();
		this.username = sysAdmin.getLoginName();
		this.password = sysAdmin.getPassword();
		this.enabled = sysAdmin.getEnabled();
		this.setAuthorities(mapToGrantedAuthorities(sysAdmin.getRoles(), sysAdmin.getMenus()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }
	
	private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<SysRole> sysRoles, List<SysMenu> sysMenus) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (sysRoles == null || sysRoles.size() == 0) {
			authorities.add(new SimpleGrantedAuthority(GUEST_ROLE));
		} else {
			authorities = sysRoles.stream().filter(SysRole::getEnabled)
					.map(sysRole -> new SimpleGrantedAuthority(sysRole.getName())).collect(Collectors.toList());
			// 添加基于Permission的权限信息
			for(SysMenu menu : sysMenus) {
				if(StringHelper.isNotBlank(menu.getPermission())) {
					String[] permissions = StringHelper.split(menu.getPermission(), ",");
					for(String permission : permissions) {
						authorities.add(new SimpleGrantedAuthority(permission));
					}
				}
			}
		}
		return authorities;
	}
}
