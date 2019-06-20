package com.smic.cf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("tgls/toModifyPassword")
	public String modifyPassword() {
		logger.info("进入修改密码页面！");
		return "tgls/modify_password";
	}
	
	@GetMapping("/toLogin")
	public String login() {
		logger.info("登录页面");
		return "login1";
	}

}
