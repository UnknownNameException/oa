package com.mmt.oa.dao;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.Workflow;

@Repository
public interface WorkflowMapper {
    int deleteByPrimaryKey(Integer workflowId);

    int insert(Workflow record);

    int insertSelective(Workflow record);

    Workflow selectByPrimaryKey(Integer workflowId);

    int updateByPrimaryKeySelective(Workflow record);

    int updateByPrimaryKey(Workflow record);
}