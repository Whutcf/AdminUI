package com.smic.cf.mapper.master;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.smic.cf.domain.User;

@Mapper
@Repository
public interface UsersMapper {
	User verifyUser(String username,String password);

	String findUserPasswordById(Integer userid);

	void updatePasswordById(@Param("userid")Integer userid,@Param("newpassword")String newPassword);

	void insertUser(HashMap<String, String> userMap);

	User findUserByUserName(String username);

	List<User> findAllUsers();

	void updateStateById(@Param("state")String state,@Param("userId") Integer userId);

	void deleteUserById(Integer userId);
}
