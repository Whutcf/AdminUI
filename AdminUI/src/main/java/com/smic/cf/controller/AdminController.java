package com.smic.cf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.smic.cf.domain.Role;
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
		return "admin/admin_maintain";
	}
	
	/**
	 * 页面跳转的新方法，进入页面后会直接加载用户信息调用 /showUserListWithRoles
	 * 
	 * @return
	 */
	@RequestMapping("/toAdminMaintain2")
	public String toAdminMaintain2() {
		log.info("进入管理员维护页面2！");
		return "admin/admin_maintain2";
	}
	
	@RequestMapping("/showUserList")
	@ResponseBody
	public Map<String, Object> userList(){
		log.info("查询所有用户信息给前台！");
		List<User> userList = usersService.findAllUsers();
		Map<String, Object> users = new HashMap<String, Object>(16);
		users.put("code", 0);
		users.put("data", userList);
		return users;
	}
	
	@RequestMapping("/showUserListWithRoles")
	@ResponseBody
	public Map<String, Object> userListWithRoles(){
		log.info("查询用户信息（包含角色信息）给前台！");
		List<User> userListWithRoles = usersService.findAllUserWithRoles();
		Map<String, Object> users = new HashMap<String, Object>(16);
		users.put("code", 0);
		users.put("data", userListWithRoles);
		log.info("传送用户信息给前台！");
		return users;
	}
	
	@RequestMapping("/changeState")
	@ResponseBody
	public String changeState(@RequestParam Map<String, String> jsonMap) {
		log.info("修改用户的状态！");
		String state = jsonMap.get("state").toString();
		String strUserId = jsonMap.get("userId").toString();
		Integer userId = (Integer) Integer.parseInt(strUserId);
		if ("true".equalsIgnoreCase(state)) {
			usersService.updateStateById("T",userId);
			log.info("用户的状态被修改为有效！");
		}else {
			usersService.updateStateById("F", userId);
			log.info("用户的状态被修改为无效！");
		}
		return "success";
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(@RequestParam("userId")Integer userId) {
		log.info("删除用户！");
		usersService.deleteUserById(userId);
		return "success";
	}
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public String deleteAll(@RequestBody List<User> users) {
		log.info("删除多个用户！");
		usersService.deleteUesrs(users);
		log.info("用户删除成功！");
		return "SUCCESS";
	}
	
	/*用户角色处理模块！*/
	
	@RequestMapping("/toAdminMaintainRole")
	public String toAdminMaintainRoles(String userId,Model model) {
		log.info("进入管理员角色维护页面！");
		model.addAttribute("userId", userId);
		return "admin/admin_maintain_roles";
	}
	
	@RequestMapping("/findUserRolesByUserId")
	@ResponseBody
	public Map<String,Object> findUserRolesByUserId(@RequestParam("userId")String userId) {
		log.info("查询用户的角色信息！");
		List<Role> roleList = usersService.findUserRolesByUserId(userId);
		Map<String, Object> roles = new HashMap<String, Object>(16);
		roles.put("code", 0);
		roles.put("data", roleList);
		log.info("返回用户角色信息！");
		return roles;
	}
	
	@RequestMapping("/toAddminAddRoles")
	public String toAddminAddRoles(String userId,Model model) {
		log.info("进入管理员添加角色页面！");
		model.addAttribute("userId", userId);
		return "admin/admin_add_roles";
	}
	
	@RequestMapping("/findUnAddedRolesByUserId")
	@ResponseBody
	public String findUnAddedRolesByUserId(@RequestParam("userId")Integer userId) {
		log.info("查询当前用户为添加的角色信息！");
		List<Role> roleList = usersService.findUnAddedRolesByUserId(userId);
		Map<String, Object> roles = new HashMap<String, Object>(16);
		roles.put("code", 0);
		roles.put("data", roleList);
		log.info("返回未添加的用户角色信息！");
		return JSON.toJSONString(roles);
	}
	
	@RequestMapping("/addRoles")
	@ResponseBody
	public String addRoles(@RequestBody List<Role> roles) {
		log.info("为用户添加角色！");
		usersService.addRoles(roles);
		log.info("为用户添加角色！");
		return "SUCCESS";
	}
	
	
	
}
