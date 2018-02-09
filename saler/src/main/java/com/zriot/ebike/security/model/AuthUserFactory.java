package com.zriot.ebike.security.model;

import com.zriot.ebike.entity.ShopSaler;

public final class AuthUserFactory {

	private AuthUserFactory() {
	}

	public static AuthUser create(ShopSaler shopSaler) {
		return new AuthUser(shopSaler);
	}

}
