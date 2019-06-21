package com.smic.cf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@RequestMapping("tgls/toModifyPassword")
	public String modifyPassword() {
		log.info("进入修改密码页面！");
		return "tgls/modify_password";
	}
	
	@GetMapping("/toLogin")
	public String login() {
		log.info("登录页面");
		return "login1";
	}

}
