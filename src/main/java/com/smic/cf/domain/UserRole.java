package com.smic.cf.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 1719056360399156720L;
	@TableId(type=IdType.AUTO)
	private Integer id;
	private Integer userId;
	private Integer roleId;

}
