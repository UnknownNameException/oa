package com.mmt.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.WorkflowNode;
import com.mmt.oa.dao.model.WorkflowNodeTemplate;

@Repository
public interface WorkflowNodeMapper {
	int deleteByPrimaryKey(Integer workflowNodeId);

	int insert(WorkflowNode record);

	int insertSelective(WorkflowNode record);

	WorkflowNode selectByPrimaryKey(Integer workflowNodeId);

	int updateByPrimaryKeySelective(WorkflowNode record);

	int updateByPrimaryKey(WorkflowNode record);

	Integer addNewWorkflowNode(WorkflowNode node);

	Integer modifyWorkflowNodeInfo(WorkflowNode node);

	Integer deleteWorkflowNodeInfo(WorkflowNode node);

	List<WorkflowNode> queryWorkflowNodeInfo(WorkflowNode node);
	
	List<WorkflowNodeTemplate> queryWorkflowNodeTemplateInfo(WorkflowNode node);

}