package com.mmt.oa.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.oa.dao.WorkflowMapper;
import com.mmt.oa.dao.WorkflowNodeMapper;
import com.mmt.oa.dao.model.Workflow;
import com.mmt.oa.dao.model.WorkflowNode;
import com.mmt.oa.dao.model.WorkflowNodeTemplate;
import com.mmt.oa.service.WorkflowService;

@Service
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private WorkflowMapper workflowMapper;

	@Autowired
	private WorkflowNodeMapper nodeMapper;

	@Override
	public Integer addNewWorkflow(Workflow workflow) {
		Integer i = workflowMapper.addNewWorkflow(workflow);
		return i;
	}

	/**
	 * 添加一个流程节点,对应的工作流模板节点数量+1
	 */
	@Override
	public Integer addNewWorkflowNode(WorkflowNode node) {
		Integer i = nodeMapper.addNewWorkflowNode(node);
		if (i == 1) {
			Workflow workflow = new Workflow();
			workflow.setWorkflowId(node.getWorkflowId());
			workflow = queryWorkflowInfoById(workflow);
			workflow.setWorkflowNodeCount(workflow.getWorkflowNodeCount() + 1);
			workflow.setModifiedBy(node.getModifiedBy());
			i = modifyWorkflowInfo(workflow);
		}
		return i;
	}

	@Override
	public Integer modifyWorkflowInfo(Workflow workflow) {
		Integer i = workflowMapper.modifyWorkflowInfo(workflow);
		return i;
	}

	@Override
	public Integer modifyWorkflowNodeInfo(WorkflowNode node) {
		Integer i = nodeMapper.modifyWorkflowNodeInfo(node);
		return i;
	}

	/**
	 * 删除工作流模板,模板对应的节点一起删除
	 */
	@Override
	public Integer deleteWorkflowInfo(Workflow workflow) {
		Integer i = workflowMapper.deleteWorkflowInfo(workflow);
		if (i == 1) {
			WorkflowNode node = new WorkflowNode();
			node.setWorkflowId(workflow.getWorkflowId());
			node.setIsValid(false);
			node.setModifiedBy(workflow.getModifiedBy());
			i = nodeMapper.deleteWorkflowNodeInfo(node);
		}
		return i;
	}

	/**
	 * 删除一个流程节点,对应的工作流模板节点数量-1
	 */
	@Override
	public Integer deleteWorkflowNodeInfo(WorkflowNode node) {
		Integer i = nodeMapper.deleteWorkflowNodeInfo(node);
		if (i == 1) {
			List<WorkflowNode> nodes = queryWorkflowNodeInfo(node);
			Workflow workflow = new Workflow();
			workflow.setWorkflowId(nodes.get(0).getWorkflowId());
			workflow = queryWorkflowInfoById(workflow);
			workflow.setWorkflowNodeCount(workflow.getWorkflowNodeCount() - 1);
			workflow.setModifiedBy(node.getModifiedBy());
			i = modifyWorkflowInfo(workflow);
		}
		return i;
	}

	@Override
	public List<Workflow> queryWorkflowInfo(Workflow workflow) {
		List<Workflow> workflows = workflowMapper.queryWorkflowInfo(workflow);
		return workflows;
	}
	
	@Override
	public List<WorkflowNode> queryWorkflowNodeInfo(WorkflowNode node) {
		List<WorkflowNode> nodes = nodeMapper.queryWorkflowNodeInfo(node);
		return nodes;
	}

	@Override
	public Workflow queryWorkflowInfoById(Workflow workflow) {
		Workflow wf = workflowMapper.queryWorkflowInfoById(workflow);
		return wf;
	}

	@Override
	public List<WorkflowNodeTemplate> queryWorkflowNodeTemplateInfo(WorkflowNode node) {
		List<WorkflowNodeTemplate> nodes = nodeMapper.queryWorkflowNodeTemplateInfo(node);
		return nodes;
	}

}
