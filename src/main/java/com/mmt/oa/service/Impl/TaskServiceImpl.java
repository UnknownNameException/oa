package com.mmt.oa.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.oa.dao.TaskMapper;
import com.mmt.oa.dao.TaskNodeMapper;
import com.mmt.oa.dao.WorkflowMapper;
import com.mmt.oa.dao.WorkflowNodeMapper;
import com.mmt.oa.dao.model.Task;
import com.mmt.oa.dao.model.TaskNode;
import com.mmt.oa.dao.model.TaskNodeTemplate;
import com.mmt.oa.dao.model.TaskTemplate;
import com.mmt.oa.dao.model.Workflow;
import com.mmt.oa.dao.model.WorkflowNode;
import com.mmt.oa.dao.model.WorkflowNodeTemplate;
import com.mmt.oa.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private TaskNodeMapper nodeMapper;

	@Autowired
	private WorkflowMapper workflowMapper;

	@Autowired
	private WorkflowNodeMapper wnMapper;

	/**
	 * 发起流程
	 */
	@Override
	public Integer addNewTask(Task task) {
		Workflow workflow = new Workflow();
		workflow.setWorkflowId(task.getWorkflowId());
		workflow = workflowMapper.queryWorkflowInfoById(workflow);
		String instenceId = workflow.getWorkflowName() + System.currentTimeMillis();
		task.setInstenceId(instenceId);
		task.setTaskNodeCount(workflow.getWorkflowNodeCount());
		Integer i = taskMapper.addNewTask(task);// 先创建流程主表
		if (i != 0) {
			task = taskMapper.queryTaskInfoById(task);// 查询主表taskId
			WorkflowNode wn = new WorkflowNode();
			wn.setWorkflowId(task.getWorkflowId());
			List<WorkflowNodeTemplate> templates = wnMapper.queryWorkflowNodeTemplateInfo(wn);// 获取工作流节点模板
			templates = templates.stream().filter(t -> t.getSort() == 1).collect(Collectors.toList());
			TaskNode node = new TaskNode();
			node.setTaskId(task.getTaskId());
			node.setWorkflowId(task.getWorkflowId());
			node.setWorkflowNodeId(templates.get(0).getWorkflowNodeId());
			node.setTaskNodeSort(1);
			node.setTaskNodeApprover(templates.get(0).getApprover());
			node.setTaskNodeStatus(0);
			node.setIsValid(true);
			node.setCreateBy(task.getModifiedBy());
			node.setCreateTime(new Date());
			node.setModifiedBy(task.getModifiedBy());
			node.setModifiedTime(new Date());
			i = nodeMapper.addNewTaskNode(node);
		}
		return i;
	}

	/**
	 * 流程审批
	 */
	@Override
	public Integer approveTaskNode(TaskNode node) {
		Integer i = nodeMapper.approveTaskNode(node);// 修改当前节点的状态
		if (i != 0) {
			WorkflowNode wn = new WorkflowNode();
			wn.setWorkflowId(node.getWorkflowId());
			wn.setIsValid(true);
			List<WorkflowNodeTemplate> templates = wnMapper.queryWorkflowNodeTemplateInfo(wn);// 取出按sort和创建日期排序后的节点列表
			WorkflowNodeTemplate template = templates.get(node.getTaskNodeSort());
			int taskId = node.getTaskId();
			String createBy = node.getCreateBy();
			node = new TaskNode();
			node.setTaskId(taskId);
			node.setWorkflowId(template.getWorkflowId());
			node.setWorkflowNodeId(template.getWorkflowNodeId());
			node.setTaskNodeSort(template.getSort());
			node.setTaskNodeApprover(template.getApprover());
			node.setTaskNodeStatus(0);
			node.setIsValid(true);
			node.setCreateBy(createBy);
			node.setCreateTime(new Date());
			node.setModifiedBy(createBy);
			node.setModifiedTime(new Date());
			i = nodeMapper.addNewTaskNode(node);// 开启下一节点
		}
		return i;
	}

	@Override
	public List<TaskNodeTemplate> queryTaskNodeInfoByModel(TaskNode node) {
		List<TaskNodeTemplate> nodes = nodeMapper.queryTaskNodeInfoByModel(node);
		return nodes;
	}

	@Override
	public List<TaskTemplate> queryTaskInfoByModel(Task task) {
		List<TaskTemplate> tasks = taskMapper.queryTaskInfoByModel(task);
		return tasks;
	}
	
	

}
