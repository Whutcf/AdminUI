package com.smic.cf.mapper.master;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.smic.cf.domain.User;

@Mapper
@Repository
public interface UsersMapper {
	User verifyUser(String username,String password);
}
