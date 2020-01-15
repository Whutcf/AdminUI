package com.smic.cf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.smic.cf.domain.Role;
import com.smic.cf.domain.User;
import com.smic.cf.domain.UserRole;
import com.smic.cf.service.RoleService;
import com.smic.cf.service.UserRoleService;
import com.smic.cf.service.UserService;
import com.smic.cf.util.Result;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
/**
 * AdminUI的admin模块
 * 
 * @author cai feng
 *
 */
public class AdminController {

	private final String STATE_DEFULT = "true";

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping("/toAdminMaintain")
	public String toAdminMaintain(Model model) {
		log.info("进入管理员维护页面！");
		List<User> userList = userService.findAllUsers();
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
	public Result<User> userList(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(value="userName",required=false,defaultValue="") String userName) {
		log.info("查询所有用户信息给前台！");
		Result<User> result = userService.selectPage(currentPage,limit,userName);
		log.info("传送用户信息给前台！");
		return result;
	}

	@RequestMapping("/showUserListWithRoles")
	@ResponseBody
	public Object userListWithRoles() {
		log.info("查询用户信息（包含角色信息）给前台！");
		List<User> userListWithRoles = userService.findAllUserWithRoles();
		Map<String, Object> users = new HashMap<String, Object>(16);
		users.put("code", 0);
		users.put("data", userListWithRoles);
		log.info("传送用户信息（包含角色信息）给前台！");
		return JSON.toJSON(users);
	}

	@RequestMapping("/changeState")
	@ResponseBody
	public String changeState(@RequestParam Map<String, String> jsonMap,HttpServletRequest request) {
		log.info("修改用户的状态！");
		String state = jsonMap.get("state").toString();
		String strUserId = jsonMap.get("userId").toString();
		User userInfo = (User) request.getSession().getAttribute("userinfo");
		String username = userInfo.getUserName();
		Integer userId = (Integer) Integer.parseInt(strUserId);
		if (STATE_DEFULT.equalsIgnoreCase(state)) {
			userService.updateStateById("T", userId, username);
			log.info("用户的状态被修改为有效！");
		} else {
			userService.updateStateById("F", userId, username);
			log.info("用户的状态被修改为无效！");
		}
		return "success";
	}

	
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(@RequestParam("userId") Integer userId) {
		log.info("删除用户！");
		userService.deleteUserById(userId);
		return "success";
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public String deleteAll(@RequestBody List<User> users) {
		log.info("删除多个用户！");
		userService.deleteUsers(users);
		log.info("用户删除成功！");
		return "SUCCESS";
	}

	@RequestMapping("/toAdminMaintainUser")
	public String toAdminMaintainUser() {
		log.info("进入管理员添加角色页面！");
		return "admin/admin_maintain_user";
	}

	@RequestMapping("/updateUserInfo")
	@ResponseBody
	@CrossOrigin
	public String updateUserInfo(@RequestBody User user,HttpServletRequest request) {
		log.info("更新用户信息！");
		User userinfo = (User) request.getSession().getAttribute("userinfo");
		user.setUpdatePerson(userinfo.getUserName());
		user.setUpdateTime(new Date());
		userService.updateUserInfo(user);
		log.info("{} 更新用户信息成功！",user.getUpdatePerson());
		return "SUCCESS";
	}

	/* 用户角色处理模块！ */

	@RequestMapping("/toAdminMaintainRole")
	public String toAdminMaintainRoles(String userId, Model model) {
		log.info("进入管理员角色维护页面！");
		model.addAttribute("userId", userId);
		return "admin/admin_maintain_roles";
	}

	@RequestMapping("/findUserRolesByUserId")
	@ResponseBody
	public Result<Role> findUserRolesByUserId(@RequestParam("userId") Integer userId) {
		log.info("查询 {} 的角色信息！",userService.findUserNameById(userId));
		Result<Role> result = roleService.findUserRolesByUserId(userId);		
		log.info("返回用户角色信息！");
		return result;
	}

	@RequestMapping("/toAddminAddRoles")
	public String toAddminAddRoles(String userId, Model model) {
		log.info("进入管理员添加角色页面！");
		model.addAttribute("userId", userId);
		return "admin/admin_add_roles";
	}

	@RequestMapping("/findUnAddedRolesByUserId")
	@ResponseBody
	public Result<Role> findUnAddedRolesByUserId(@RequestParam("userId") Integer userId) {
		log.info("查询 {} 未拥有的角色信息！",userService.findUserNameById(userId));	
		Result<Role> result = roleService.findUnAddedRolesByUserId(userId);
		return result;
	}

	@RequestMapping("/addRoles")
	@ResponseBody
	public String addRoles(@RequestBody List<UserRole> userRoles,HttpServletRequest request ) {
		User userinfo = (User) request.getSession().getAttribute("userinfo");
		Integer userId = userRoles.get(0).getUserId();
		String loginUserName = userinfo.getUserName();
		User user = userService.findUserById(userId);
		log.info("{} 开始为用户 {} 添加角色！",loginUserName,user.getUserName());
		userRoleService.add(userRoles);
		
		log.info("{} 成功为用户 {} 添加角色！",loginUserName,user.getUserName());
		
		user.setUpdatePerson(loginUserName);
		user.setUpdateTime(new Date());
		userService.updateUserInfo(user);
		return "SUCCESS";
	}

	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(@RequestParam("roleId") Integer roleId, @RequestParam("userId") Integer userId) {
		log.info("为用户删除某个角色！");
		userRoleService.deleteRole(roleId, userId);
		log.info("成功为用户删除某个角色！");
		return "SUCCESS";
	}

	@RequestMapping("/deleteRoles")
	@ResponseBody
	public String deleteRoles(@RequestBody List<UserRole> userRoles) {
		log.info("为用户删除多个角色！");
		userRoleService.deleteRoles(userRoles);
		log.info("成功为用户删除多个角色！");
		return "SUCCESS";
	}
	
	
	/**
	 * 查询角色清单
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	@RequestMapping("/showRoleList")
	@ResponseBody
	public Result<Role> roleList(@RequestParam(value="page",required=false,defaultValue="1")Integer currentPage,
			@RequestParam(value="limit",required=false,defaultValue="10")Integer limit){
		log.info("查询角色清单！");

		Result<Role> result = roleService.selectPage(currentPage,limit);
	

		log.info("返回角色清单给前台！");
		return result;
	}

	
	//文件上传下载模块
}
