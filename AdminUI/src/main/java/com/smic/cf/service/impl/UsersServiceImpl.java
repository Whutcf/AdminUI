package com.smic.cf.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		HashMap<String, String> userMap = new HashMap<>();
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

}
