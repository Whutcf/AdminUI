package com.smic.cf.service;

import java.util.List;

import com.smic.cf.domain.User;

public interface UsersService {
	User verifyUser(String username, String password);

	String findUserById(Integer userid);

	void updatePasswordById(Integer userid,String newPassword);

	void addUser(String username, String password, String state);

	User findUserByUsername(String username);

	List<User> findAllUsers();

	void updateStateById(String state, Integer userId);

	void deleteUserById(Integer userId);
}
