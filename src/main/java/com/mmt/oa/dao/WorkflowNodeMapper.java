package com.mmt.oa.dao;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.WorkflowNode;

@Repository
public interface WorkflowNodeMapper {
    int deleteByPrimaryKey(Integer workflowNodeId);

    int insert(WorkflowNode record);

    int insertSelective(WorkflowNode record);

    WorkflowNode selectByPrimaryKey(Integer workflowNodeId);

    int updateByPrimaryKeySelective(WorkflowNode record);

    int updateByPrimaryKey(WorkflowNode record);
}