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
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class Role implements Serializable{

	private static final long serialVersionUID = -8262093903425648981L;
	@TableId("role_id")
	private Integer roleId;
	private String rolename;
	@TableField(exist=false)
	private ArrayList<User> users;

}
