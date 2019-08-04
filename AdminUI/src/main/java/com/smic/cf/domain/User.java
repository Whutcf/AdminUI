package com.smic.cf.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = -2317543120131319228L;
	@TableId("user_id")
	private Integer userId;
	private String username;
	private String password;
	private String state;
	@TableField(exist=false)
	private ArrayList<Role> roles;
	
}
