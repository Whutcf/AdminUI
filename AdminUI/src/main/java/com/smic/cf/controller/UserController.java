package com.smic.cf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smic.cf.domain.User;
import com.smic.cf.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("tgls/toModifyPassword")
	public String modifyPassword() {
		log.info("进入修改密码页面！");
		return "tgls/modify_password";
	}
	
	@GetMapping("/toLogin")
	public String toLogin() {
		log.info("跳转登录页面!");
		return "login1";
	}
	
	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request) {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		User user = usersService.verifyUser(username, password);
		if(!ObjectUtils.isEmpty(user)) {
			return "frame";
		}
		model.addAttribute("name", username);
		return "login1";
	}

}
