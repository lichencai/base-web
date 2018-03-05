package com.zriot.ebike.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zriot.ebike.constant.Message;
import com.zriot.ebike.constant.ReturnCode;
import com.zriot.ebike.exception.BusinessException;
import com.zriot.ebike.security.AuthenticationTokenFilter;
import com.zriot.ebike.security.utils.TokenUtil;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {
	/**
	 * 权限管理
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	/**
	 * 用户信息服务
	 */
	@Autowired
	private UserDetailsService userDetailsService;
	/**
	 * Token工具类
	 */
	@Autowired
	private TokenUtil jwtTokenUtil;
	
	/**
	 * 用户登录获取token
	 * @throws BusinessException 
	 */
	@PostMapping(value = "/token")
	public Map<String, Object> createAuthenticationToken(@NotBlank(message="{param.empty}") @RequestParam("loginName") String loginName,
			@NotBlank(message="{param.empty}") @RequestParam("password") String password) throws BusinessException {
		
		// 完成授权
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginName, password));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		final String token = jwtTokenUtil.generateToken(userDetails); // 生成Token

		Map<String, Object> tokenMap = new HashMap<>();
		tokenMap.put("access_token", token);
		tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
		tokenMap.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);

		Map<String, Object> message = new HashMap<>();
		message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
		message.put(Message.RETURN_FIELD_DATA, tokenMap);

		return message;
	}

	/**
	 * 刷新token
	 */
	@GetMapping(value = "/refresh")
	public Map<String, Object> refreshAndGetAuthenticationToken(HttpServletRequest request) {

		String tokenHeader = request.getHeader(AuthenticationTokenFilter.TOKEN_HEADER);
		String token = tokenHeader.split(" ")[1];

		// 重新生成Token
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String refreshedToken = jwtTokenUtil.generateToken(userDetails);

		Map<String, Object> tokenMap = new HashMap<>();
		tokenMap.put("access_token", refreshedToken);
		tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
		tokenMap.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);

		Map<String, Object> message = new HashMap<>();
		message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
		message.put(Message.RETURN_FIELD_DATA, tokenMap);

		return message;
	}

	/**
	 * 删除token
	 */
	@DeleteMapping(value = "/token", produces = "application/json; charset=UTF-8")
	public Map<String, Object> deleteAuthenticationToken(HttpServletRequest request) {

		String tokenHeader = request.getHeader(AuthenticationTokenFilter.TOKEN_HEADER);
		String token = tokenHeader.split(" ")[1];

		// 移除token
		jwtTokenUtil.removeToken(token);

		Map<String, Object> message = new HashMap<>();
		message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);

		return message;
	}
}
