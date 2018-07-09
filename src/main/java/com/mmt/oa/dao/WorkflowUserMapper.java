package com.mmt.oa.dao;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.WorkflowUser;

@Repository
public interface WorkflowUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowUser record);

    int insertSelective(WorkflowUser record);

    WorkflowUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowUser record);

    int updateByPrimaryKey(WorkflowUser record);
}