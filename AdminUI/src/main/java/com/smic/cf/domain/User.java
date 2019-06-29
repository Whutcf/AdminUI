package com.smic.cf.domain;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -2317543120131319228L;
	private Integer userId;
	private String username;
	private String password;
	private String state;
	private ArrayList<Role> userRoles;
	

}
