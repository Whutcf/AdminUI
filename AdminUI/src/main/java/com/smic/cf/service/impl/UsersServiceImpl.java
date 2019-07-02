package com.smic.cf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smic.cf.domain.Role;
import com.smic.cf.domain.User;
import com.smic.cf.mapper.master.UsersMapper;
import com.smic.cf.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
/**
 * 
 * @ClassName UsersServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @author cai feng
 * @date 2019年6月21日
 *
 */
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public User verifyUser(String username, String password) {
		log.info("进入UsersService层:verifyUser!");
		return usersMapper.verifyUser(username, password);
	}

	@Override
	public String findUserById(Integer userid) {
		log.info("查看用户的旧密码！");
		String password = usersMapper.findUserPasswordById(userid);
		return password;
	}

	@Override
	public void updatePasswordById(Integer userid,String newPassword) {
		log.info("修改密码！");
		usersMapper.updatePasswordById(userid,newPassword);
	}

	@Override
	public void addUser(String username, String password, String state) {
		log.info("处理注册用户！");
		HashMap<String, String> userMap = new HashMap<>(16);
		userMap.put("username", username);
		userMap.put("password", password);
		userMap.put("state", state);
		usersMapper.insertUser(userMap);
	}

	@Override
	public User findUserByUsername(String username) {
		 
		return usersMapper.findUserByUserName(username);
	}

	@Override
	public List<User> findAllUsers() {
		 
		return usersMapper.findAllUsers();
	}

	@Override
	public void updateStateById(String state, Integer userId) {
		usersMapper.updateStateById(state,userId);
	}

	@Override
	public void deleteUserById(Integer userId) {
		usersMapper.deleteUserById(userId);
	}

	@Override
	public void deleteUesrs(List<User> users) {
		usersMapper.deleteUsers(users);
		
	}

	@Override
	public List<User> findAllUserWithRoles() {

		return usersMapper.findAllUserWithRoles();
	}

	@Override
	public List<Role> findUserRolesByUserId(Integer userId) {
		
		return usersMapper.findUserRolesByUserId(userId);
	}

	@Override
	public List<Role> findUnAddedRolesByUserId(Integer userId) {
		List<Role> roles = usersMapper.findUnAddedRolesByUserId(userId);//查询未添加的角色
		List<Role> existRoles = usersMapper.findUserRolesByUserId(userId);//查询已添加的角色
		int rolesCount = usersMapper.countRols();
		log.info("查询角色表当前的角色数是："+rolesCount);
		log.info("用户已经拥有的角色数是："+existRoles.size());
		if(roles.size()==rolesCount || (roles.size()==existRoles.size() && roles.size()==0)) {
			log.info("当前用户未添加角色！");
			List<Role> roles2 = usersMapper.findAllRoles();
			for (Role role : roles2) {
				User user = new User();
				user.setUserId(userId);
				ArrayList<User> users = new ArrayList<>();
				users.add(user);
				role.setUsers(users);
			}
			return	roles2;
		}
		if(roles.isEmpty()) {
			log.info("当前用户无需添加角色！");
			return null;
		}
		return roles;
	}

	@Override
	public void addRoles(List<Role> roles) {
		for (Role role : roles) {
			ArrayList<User> users = role.getUsers();
			User user = users.get(0);
			usersMapper.insertRoles(role.getRoleId(),user.getUserId());
			log.info("成功添加角色"+role.getRolename());
		}
		
	}

	@Override
	public void deleteRole(Integer roleId, Integer userId) {
		usersMapper.deleteRole(roleId,userId);		
	}

	@Override
	public void deleteRoles(List<Role> roles) {
		for (Role role : roles) {
			ArrayList<User> users = role.getUsers();
			User user = users.get(0);
			usersMapper.deleteRole(role.getRoleId(), user.getUserId());
		}
		
	}

}
