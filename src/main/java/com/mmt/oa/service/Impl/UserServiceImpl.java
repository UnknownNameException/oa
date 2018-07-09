package com.mmt.oa.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mmt.oa.dao.UserMapper;
import com.mmt.oa.dao.model.User;
import com.mmt.oa.model.UserReturn;
import com.mmt.oa.service.UserService;

@Service
public class UserServiceImpl implements UserService {


    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public List<User> queryUserDetails(User user) {
		List<User> users = userMapper.queryUserDetails(user);
		return users;
	}

	@Override
	public UserReturn addNewUser(User user) {
		UserReturn re = new UserReturn();
		List<User> list = userMapper.queryUserDetails(user);
		if (!list.isEmpty()) {
			re.setIsSuccess(0);
			re.setUserName("用户名已存在!");
			return re;
		}
		int i = userMapper.addNewUser(user);
		user = userMapper.queryUserDetails(user).get(0);
		re = JSON.parseObject(JSON.toJSONString(user), UserReturn.class);
		re.setIsSuccess(i);
		return re;
	}

	@Override
	public Integer modifyUserAccount(User user) {
		Integer i = userMapper.modifyUserAccount(user);
		return i;
	}

	@Override
	public Integer deleteUserAccount(User user) {
		Integer i = userMapper.deleteUserAccount(user);
		return i;
	}

	@Override
	public Integer userLogin(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
