package com.smic.cf.service.impl;

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

}
