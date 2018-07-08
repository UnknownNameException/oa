package com.mmt.oa.dao;

import com.mmt.oa.dao.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}