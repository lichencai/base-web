package com.zriot.ebike.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 商店管理
 */
@Validated
@RestController
@Slf4j
@RequestMapping("/shop")
public class ShopController extends BaseController {
	
}
