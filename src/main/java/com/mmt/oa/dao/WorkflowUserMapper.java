package com.mmt.oa.dao;

import com.mmt.oa.dao.model.WorkflowUser;

public interface WorkflowUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowUser record);

    int insertSelective(WorkflowUser record);

    WorkflowUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowUser record);

    int updateByPrimaryKey(WorkflowUser record);
}