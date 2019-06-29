package com.smic.cf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smic.cf.domain.User;
import com.smic.cf.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/toAdminMaintain")
	public String toAdminMaintain(Model model) {
		log.info("进入管理员维护页面！");
		List<User> userList = usersService.findAllUsers();
		System.out.println(userList.size());
		for (User user : userList) {
			System.out.println(user.getUserId());
		}
		model.addAttribute("userList", userList);
		return "/admin_maintain";
	}
	
	@RequestMapping("/toAdminMaintain2")
	public String toAdminMaintain2(Model model) {
		log.info("进入管理员维护页面2！");
		return "admin/admin_maintain2";
	}
	
	@RequestMapping("/showUserList")
	@ResponseBody
	public Map<String, Object> userList(){
		List<User> userList = usersService.findAllUsers();
		Map<String, Object> users = new HashMap<String, Object>(16);
		users.put("code", 0);
		users.put("data", userList);
		return users;
	}
	
	@RequestMapping("/changeState")
	@ResponseBody
	public String changeState(@RequestParam Map<String, String> jsonMap) {
		log.info("修改用户的状态！");
		String stateString = jsonMap.get("state").toString();
		boolean state = Boolean.getBoolean(stateString);
		String strUserId = jsonMap.get("userId").toString();
		Integer userId = (Integer) Integer.parseInt(strUserId);
		if ((state)) {
			usersService.updateStateById("able",userId);
			return "success";
		}
		usersService.updateStateById("disable", userId);
		return "success";
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(@RequestParam("userId")Integer userId) {
		log.info("删除用户！");
		usersService.deleteUserById(userId);
		return "success";
	}
}
