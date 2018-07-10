package com.mmt.oa.dao;

import java.util.List;

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
    
    Integer addNewWorkflow(Workflow workflow);
    
    Integer modifyWorkflowInfo(Workflow workflow);
    
    Integer deleteWorkflowInfo(Workflow workflow);
    
    List<Workflow> queryWorkflowInfo(Workflow workflow);
    
    Workflow queryWorkflowInfoById(Workflow workflow);
}