package com.smic.cf.service;

import com.smic.cf.domain.User;

public interface UsersService {
	User verifyUser(String username, String password);
}
