package com.mmt.oa.service;

import java.util.List;

import com.mmt.oa.dao.model.User;
import com.mmt.oa.model.UserReturn;

public interface UserService {
	
	public List<User> queryUserDetails(User user);
	
	public UserReturn addNewUser(User user);
	
	public Integer modifyUserAccount(User user);
	
	public Integer deleteUserAccount(User user);
	
	public User userLogin(User user);
	
}
