package com.mmt.oa.dao;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.UserRole;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}