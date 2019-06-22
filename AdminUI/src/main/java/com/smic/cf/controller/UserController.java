package com.smic.cf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.smic.cf.domain.User;
import com.smic.cf.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes("hasmsg")
@Controller
/**
 * 
 * @ClassName UserController
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @author cai feng
 * @date 2019年6月22日
 *
 */
public class UserController {
	
	private static String STATE = "disable";
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request) {
		log.info("进入用户登录模块！");
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		User user = usersService.verifyUser(username, password);
		if(!ObjectUtils.isEmpty(user)) {
			String state = user.getState();
			if(STATE.equalsIgnoreCase(state)) {
				log.info("用户已失效！");
				model.addAttribute("loginError",true);
				model.addAttribute("error", "用户已失效，请联系管理员！");
				return "login1";
			}
			request.getSession().setAttribute("userid", user.getUserId());
			return "frame";
		}
		log.info("用户名或密码错误！");
		model.addAttribute("name", username);
		model.addAttribute("loginError",true);
		model.addAttribute("error", "用户名或密码错误！");
		return "login1";
	}
	
	@RequestMapping("/modifyPassword")
	public String modifyPassword(Model model,HttpServletRequest request) {
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("password");
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		String initialPassword = usersService.findUserById(userid);
		if(!initialPassword.equalsIgnoreCase(oldPassword)) {
			model.addAttribute("verifyFailure",true);
			model.addAttribute("error", "密码不正确！");
 			return "tgls/modify_password";
		}
		usersService.updatePasswordById(userid,newPassword);
		log.info("密码已修改！");
		model.addAttribute("hasmsg",true);
		model.addAttribute("msg", "密码已修改，请重新登录！");
		return "login1";
	}

}
