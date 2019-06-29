package com.smic.cf.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private Integer roleId;
	private String roleName;
	private ArrayList<User> roleUsers;

}
