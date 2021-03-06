package com.smic.cf.mapper.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.smic.cf.domain.User;
import com.smic.cf.domain.UserRole;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

	List<Integer> selectRoleIds(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
