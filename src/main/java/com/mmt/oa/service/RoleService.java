package com.mmt.oa.service;

import java.util.List;

import com.mmt.oa.dao.model.Role;

public interface RoleService {

	public List<Role> queryRoleInfo(Role role);

	public Integer addNewRole(Role role);

	public Integer modifyRoleInfo(Role role);

	public Integer deleteRoleInfo(Role role);

}
