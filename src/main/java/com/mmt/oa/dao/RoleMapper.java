package com.mmt.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.Role;

@Repository
public interface RoleMapper {
	int deleteByPrimaryKey(Integer roleId);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer roleId);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	int addNewRole(Role role);

	List<Role> queryRoleInfo(Role role);

	Integer modifyRoleInfo(Role role);

	Integer deleteRoleInfo(Role role);
}