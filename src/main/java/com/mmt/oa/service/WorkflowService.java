package com.mmt.oa.service;

import java.util.List;

import com.mmt.oa.dao.model.Workflow;
import com.mmt.oa.dao.model.WorkflowNode;
import com.mmt.oa.dao.model.WorkflowNodeTemplate;

public interface WorkflowService {

	Integer addNewWorkflow(Workflow workflow);

	Integer addNewWorkflowNode(WorkflowNode node);

	Integer modifyWorkflowInfo(Workflow workflow);

	Integer modifyWorkflowNodeInfo(WorkflowNode node);

	Integer deleteWorkflowInfo(Workflow workflow);

	Integer deleteWorkflowNodeInfo(WorkflowNode node);

	List<Workflow> queryWorkflowInfo(Workflow workflow);

	List<WorkflowNode> queryWorkflowNodeInfo(WorkflowNode node);

	Workflow queryWorkflowInfoById(Workflow workflow);

	List<WorkflowNodeTemplate> queryWorkflowNodeTemplateInfo(WorkflowNode node);

}
