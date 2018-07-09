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
import com.mmt.oa.dao.model.Role;
import com.mmt.oa.emun.Constant;
import com.mmt.oa.model.RequestModel;
import com.mmt.oa.model.ResponseModel;
import com.mmt.oa.model.RoleDto;
import com.mmt.oa.service.Impl.RoleServiceImpl;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	private static Logger logger = LoggerFactory.getLogger(RoleController.class);

	/**
	 * 查询角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryRoleInfo", method = RequestMethod.POST)
	public ResponseModel queryRoleInfo(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		int pageNum = 1;
		int pageSize = 10;
		if (request.getPageNum() != 0) {
			pageNum = request.getPageNum();
		}
		if (request.getPageSize() != 0) {
			pageSize = request.getPageSize();
		}
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Role role = JSON.parseObject(JSONObject.toJSONString(obj), Role.class);
			PageHelper.startPage(pageNum, pageSize);
			List<Role> roles = roleServiceImpl.queryRoleInfo(role);
			List<RoleDto> dtos = new ArrayList<>();
			for (Role role2 : roles) {
				RoleDto dto = JSON.parseObject(JSON.toJSONString(role2), RoleDto.class);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
				dto.setCreateTime(sdf.format(role2.getCreateTime()));
				dto.setModifiedTime(sdf.format(role2.getModifiedTime()));
				dtos.add(dto);
			}
			response.setResCode(0);
			response.setResMsg("查询成功!");
			Map<String, List<RoleDto>> map = new HashMap<>();
			map.put("Roles", dtos);
			response.setResult(map);
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("角色信息查询失败!" + e.getMessage());
			logger.error("/role/queryRoleInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 添加新角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addNewRole", method = RequestMethod.POST)
	public ResponseModel addNewRole(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Role role = JSON.parseObject(JSONObject.toJSONString(obj), Role.class);
			if (role.getIsValid() == null) {
				role.setIsValid(true);
			}
			role.setCreateTime(new Date());
			role.setModifiedTime(new Date());
			role.setModifiedBy(role.getCreateBy());
			int i = roleServiceImpl.addNewRole(role);
			if (i == 1) {
				response.setResCode(0);
				response.setResMsg("角色添加成功!");
			} else {
				response.setResCode(1);
				response.setResMsg("角色添加失败!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("角色添加失败!" + e.getMessage());
			logger.error("/role/addNewRole出现异常", e);
		}
		return response;
	}

	/**
	 * 修改角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyRoleInfo", method = RequestMethod.POST)
	public ResponseModel modifyRoleInfo(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Role role = JSON.parseObject(JSONObject.toJSONString(obj), Role.class);
			Integer i = roleServiceImpl.modifyRoleInfo(role);
			if (i == 1) {
				response.setResCode(0);
				response.setResMsg("角色信息修改成功!");
			} else {
				response.setResCode(1);
				response.setResMsg("角色信息修改失败!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("角色信息修改失败!" + e.getMessage());
			logger.error("/role/modifyRoleInfo出现异常", e);
		}
		return response;
	}

	@RequestMapping(value = "/deleteRoleInfo", method = RequestMethod.POST)
	public ResponseModel deleteRoleInfo(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Role role = JSON.parseObject(JSONObject.toJSONString(obj), Role.class);
			role.setIsValid(false);
			Integer i = roleServiceImpl.deleteRoleInfo(role);
			if (i == 1) {
				response.setResCode(0);
				response.setResMsg("角色信息删除成功!");
			} else {
				response.setResCode(1);
				response.setResMsg("角色信息删除失败!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("角色信息删除失败!" + e.getMessage());
			logger.error("/role/deleteRoleInfo出现异常", e);
		}
		return response;
	}

}
