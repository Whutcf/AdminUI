package com.smic.cf.service;

import java.util.List;

import com.smic.cf.domain.UserRole;

public interface UserRoleService {

	void add(List<UserRole> userRoles);

	void deleteRole(Integer roleId, Integer userId);

	void deleteRoles(List<UserRole> userRoles);

}
