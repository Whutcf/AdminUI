package com.smic.cf.service;

import java.util.List;

import com.smic.cf.domain.Role;
import com.smic.cf.domain.User;

public interface UsersService {
	User verifyUser(String username, String password);

	String findUserById(Integer userid);

	void updatePasswordById(Integer userid,String newPassword);

	void addUser(String username, String password, String state);

	User findUserByUsername(String username);

	List<User> findAllUsers();

	List<User> findAllUserWithRoles();
	
	void updateStateById(String state, Integer userId);

	void deleteUserById(Integer userId);

	void deleteUesrs(List<User> users);

	List<Role> findUserRolesByUserId(Integer userId);

	List<Role> findUnAddedRolesByUserId(Integer userId);

	void addRoles(List<Role> roles);

	void deleteRole(Integer roleId, Integer userId);

	void deleteRoles(List<Role> roles);
}
