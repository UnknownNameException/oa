package com.mmt.oa.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.oa.dao.RoleMapper;
import com.mmt.oa.dao.model.Role;
import com.mmt.oa.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> queryRoleInfo(Role role) {
		List<Role> roles = roleMapper.queryRoleInfo(role);
		return roles;
	}

	@Override
	public Integer addNewRole(Role role) {
		Integer i = roleMapper.addNewRole(role);
		return i;
	}

	@Override
	public Integer modifyRoleInfo(Role role) {
		Integer i = roleMapper.modifyRoleInfo(role);
		return i;
	}

	@Override
	public Integer deleteRoleInfo(Role role) {
		Integer i = roleMapper.deleteRoleInfo(role);
		return i;
	}

}
