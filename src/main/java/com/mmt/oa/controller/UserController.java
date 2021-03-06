package com.mmt.oa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.mmt.oa.dao.model.User;
import com.mmt.oa.emun.Constant;
import com.mmt.oa.model.RequestModel;
import com.mmt.oa.model.ResponseModel;
import com.mmt.oa.model.UserDto;
import com.mmt.oa.model.UserReturn;
import com.mmt.oa.service.Impl.UserServiceImpl;
import com.mmt.oa.util.EncodeMD5Util;

@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private EncodeMD5Util encode;

	/**
	 * 创建用户账号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public ResponseModel addNewUser(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}
		try {
			Object obj = request.getContent();
			User user = JSON.parseObject(JSONObject.toJSONString(obj), User.class);
			user.setUserId(null);
			user.setModifiedBy(user.getCreateBy());
			user.setCreateTime(new Date());
			user.setModifiedTime(new Date());
			user.setUserPwd(encode.encrypt16(user.getUserPwd()));
			if (user.getIsValid() == null) {
				user.setIsValid(true);
			}
			UserReturn re = userServiceImpl.addNewUser(user);
			if (re.getIsSuccess() == 0) {
				response.setResCode(1);
				response.setResMsg("用户添加失败!" + re.getUserName());
			} else {
				UserDto dto = JSON.parseObject(JSON.toJSONString(re), UserDto.class);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
				dto.setCreateTime(sdf.format(re.getCreateTime()));
				dto.setModifiedTime(sdf.format(re.getModifiedTime()));
				response.setResCode(0);
				response.setResMsg("用户添加成功!");
				Map<String, UserDto> map = new HashMap<>();
				map.put("User", dto);
				response.setResult(map);
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("用户添加失败!" + e.getMessage());
			logger.error("/user/addNewUser出现异常", e);
		}
		return response;
	}

	/**
	 * 查询用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryUserDetails", method = RequestMethod.POST)
	public ResponseModel queryUserDetails(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		int pageNum = 1;
		int pageSize = 10;
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			JSONObject jo = JSON.parseObject(JSONObject.toJSONString(obj));
			if (jo.containsKey("pageNum")) {
				pageNum = jo.getInteger("pageNum");
			}
			if (jo.containsKey("pageSize")) {
				pageSize = jo.getInteger("pageSize");
			}
			User user = JSON.parseObject(JSONObject.toJSONString(obj), User.class);
			if (user.getUserId() == 0) {
				user.setUserId(null);
			}
			PageHelper.startPage(pageNum, pageSize);
			List<User> users = userServiceImpl.queryUserDetails(user);
			List<UserDto> dtos = new ArrayList<>();
			for (User user2 : users) {
				UserDto dto = JSON.parseObject(JSON.toJSONString(user2), UserDto.class);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
				dto.setCreateTime(sdf.format(user2.getCreateTime()));
				dto.setModifiedTime(sdf.format(user2.getModifiedTime()));
				dtos.add(dto);
			}
			response.setResCode(0);
			response.setResMsg("查询成功!");
			Map<String, List<UserDto>> map = new HashMap<>();
			map.put("Users", dtos);
			response.setResult(map);
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("查询失败!" + e.getMessage());
			logger.error("/user/queryUserDetails出现异常", e);
		}

		return response;
	}

	/**
	 * 更新用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyUserAccount", method = RequestMethod.POST)
	public ResponseModel modifyUserAccount(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}
		
		try {
			Object obj = request.getContent();
			User user = JSON.parseObject(JSONObject.toJSONString(obj), User.class);
			if (user.getUserId() == null || user.getUserId() == 0) {
				response.setResCode(1);
				response.setResMsg("UserId为空!");
				return response;
			}
			if (!StringUtils.isEmpty(user.getUserPwd())) {
				user.setUserPwd(encode.encrypt16(user.getUserPwd()));
			}
			Integer i = userServiceImpl.modifyUserAccount(user);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("更新失败!");
			} else {
				List<User> users = userServiceImpl.queryUserDetails(user);
				UserDto dto = JSON.parseObject(JSON.toJSONString(users.get(0)), UserDto.class);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
				dto.setCreateTime(sdf.format(users.get(0).getCreateTime()));
				dto.setModifiedTime(sdf.format(users.get(0).getModifiedTime()));
				response.setResCode(0);
				response.setResMsg("修改成功!");
				Map<String, UserDto> map = new HashMap<>();
				map.put("User", dto);
				response.setResult(map);
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("更新失败!" + e.getMessage());
			logger.error("/user/modifyUserAccount出现异常", e);
		}
		
		return response;
	}

	/**
	 * 删除用户账号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteUserAccount", method = RequestMethod.POST)
	public ResponseModel deleteUserAccount(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}
		
		try {
			Object obj = request.getContent();
			User user = JSON.parseObject(JSONObject.toJSONString(obj), User.class);
			if ((user.getUserId() == null || user.getUserId() == 0) && StringUtils.isEmpty(user.getUserName()) ) {
				response.setResCode(1);
				response.setResMsg("UserId和UserName不能都为空!");
				return response;
			}
			user.setIsValid(false);
			Integer i = userServiceImpl.deleteUserAccount(user);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("刪除失敗！");
			} else {
				response.setResCode(0);
				response.setResMsg("刪除成功！");
			}
			
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("删除失败!" + e.getMessage());
			logger.error("/user/deleteUserAccount出现异常", e);
		}
		return response;
	}

	/**
	 * 登录验证
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public ResponseModel userLogin(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}
		
		try {
			Object obj = request.getContent();
			User user = JSON.parseObject(JSONObject.toJSONString(obj), User.class);
			User u = userServiceImpl.userLogin(user);
			UserDto dto = JSON.parseObject(JSON.toJSONString(u), UserDto.class);
			String pwd = encode.encrypt16(user.getUserPwd());
			if (u.getUserPwd().equals(pwd) && dto.getIsValid()) {
				response.setResCode(0);
				response.setResMsg("登陆成功!");
				Map<String, UserDto> map = new HashMap<>();
				map.put("User", dto);
				response.setResult(map);
			} else if (!dto.getIsValid()) {
				response.setResCode(1);
				response.setResMsg("登录失败,账号不存在!");
			} else {
				response.setResCode(1);
				response.setResMsg("登录失败,用户名或密码错误!");
			}
			
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("登录失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/user/userLogin出现异常", e);
		}
		return response;
	}

}
