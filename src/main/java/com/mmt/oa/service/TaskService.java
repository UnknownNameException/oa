package com.mmt.oa.service;

import java.util.List;

import com.mmt.oa.dao.model.Task;
import com.mmt.oa.dao.model.TaskNode;
import com.mmt.oa.dao.model.TaskNodeTemplate;
import com.mmt.oa.dao.model.TaskTemplate;

public interface TaskService {

	public Integer addNewTask(Task task);

	public Integer approveTaskNode(TaskNode node);

	public List<TaskNodeTemplate> queryTaskNodeInfoByModel(TaskNode node);

	public List<TaskTemplate> queryTaskInfoByModel(Task task);

	public Integer modifyTaskInfoByModel(Task task);

	public Integer modifyTaskInfoById(Task task);
}
