package com.mmt.oa.dao;

import com.mmt.oa.dao.model.WorkflowNode;

public interface WorkflowNodeMapper {
    int deleteByPrimaryKey(Integer workflowNodeId);

    int insert(WorkflowNode record);

    int insertSelective(WorkflowNode record);

    WorkflowNode selectByPrimaryKey(Integer workflowNodeId);

    int updateByPrimaryKeySelective(WorkflowNode record);

    int updateByPrimaryKey(WorkflowNode record);
}