package com.smic.cf.domain;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{

	private static final long serialVersionUID = -8262093903425648981L;
	private Integer roleId;
	private String rolename;
	private ArrayList<User> users;

}
