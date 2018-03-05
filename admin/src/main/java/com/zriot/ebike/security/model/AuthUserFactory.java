package com.zriot.ebike.security.model;

import com.zriot.ebike.entity.SysAdmin;

public final class AuthUserFactory {

	private AuthUserFactory() {
	}

	public static AuthUser create(SysAdmin sysAdmin) {
		return new AuthUser(sysAdmin);
	}

}
