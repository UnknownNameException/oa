package com.mmt.oa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.mmt.oa.dao.model.Task;
import com.mmt.oa.dao.model.TaskNode;
import com.mmt.oa.dao.model.TaskNodeTemplate;
import com.mmt.oa.dao.model.TaskTemplate;
import com.mmt.oa.dao.model.Workflow;
import com.mmt.oa.emun.Constant;
import com.mmt.oa.model.RequestModel;
import com.mmt.oa.model.ResponseModel;
import com.mmt.oa.model.TaskDto;
import com.mmt.oa.model.TaskNodeDto;
import com.mmt.oa.service.Impl.TaskServiceImpl;
import com.mmt.oa.service.Impl.WorkflowServiceImpl;

@RestController
@RequestMapping("/task")
public class TaskController {

	private static Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskServiceImpl taskService;

	@Autowired
	private WorkflowServiceImpl workflowService;

	@RequestMapping(value = "/addNewTask", method = RequestMethod.POST)
	public ResponseModel addNewTask(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Task task = JSON.parseObject(JSON.toJSONString(obj), Task.class);
			task.setIsValid(true);
			task.setCreateTime(new Date());
			task.setModifiedTime(new Date());
			task.setTaskStatus(1);
			Integer i = taskService.addNewTask(task);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,发起流程失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("发起流程成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("发起流程失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/addNewTask出现异常", e);
		}

		return response;
	}

	@RequestMapping(value = "/approveTaskNode", method = RequestMethod.POST)
	public ResponseModel approveTaskNode(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			TaskNode node = JSON.parseObject(JSON.toJSONString(obj), TaskNode.class);
			node.setTaskNodeStatus(1);
			Integer i = taskService.approveTaskNode(node);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,流程审批失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("流程审批成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("流程审批失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/approveTaskNode出现异常", e);
		}
		return response;
	}

	/**
	 * 查询待审批节点
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryOnProcessingTask", method = RequestMethod.POST)
	public ResponseModel queryOnProcessingTask(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			TaskNode node = JSON.parseObject(JSON.toJSONString(obj), TaskNode.class);
			node.setTaskNodeStatus(0);
			List<TaskNodeTemplate> nodes = taskService.queryTaskNodeInfoByModel(node);
			Set<Integer> taskIds = nodes.stream().map(n -> n.getTaskId()).collect(Collectors.toSet());
			List<TaskDto> taskDtos = new ArrayList<>();
			for (Integer taskId : taskIds) {
				Task task = new Task();
				task.setTaskId(taskId);
				List<TaskTemplate> tasks = taskService.queryTaskInfoByModel(task);
				TaskDto taskDto = JSON.parseObject(JSON.toJSONString(tasks.get(0)), TaskDto.class);
				List<TaskNodeTemplate> tns = nodes.stream().filter(n -> n.getTaskId() == taskId)
						.collect(Collectors.toList());
				List<TaskNodeDto> tnds = JSON.parseArray(JSON.toJSONString(tns), TaskNodeDto.class);
				taskDto.setNodes(tnds);
				taskDtos.add(taskDto);
			}
			response.setResCode(0);
			response.setResMsg("查询成功!");
			Map<String, List<TaskDto>> map = new HashMap<>();
			map.put("tasks", taskDtos);
			response.setResult(map);
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("查询审批中流程失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/queryOnProcessingTask出现异常", e);
		}
		return response;
	}

	/**
	 * 查询归档流程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCompetedTask", method = RequestMethod.POST)
	public ResponseModel queryCompetedTask(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Task task = JSON.parseObject(JSON.toJSONString(obj), Task.class);
			task.setTaskStatus(2);
			List<TaskTemplate> template = taskService.queryTaskInfoByModel(task);
			List<TaskDto> td = JSON.parseArray(JSON.toJSONString(template), TaskDto.class);
			for (TaskDto taskDto : td) {
				TaskNode node = new TaskNode();
				node.setTaskId(taskDto.getTaskId());
				List<TaskNodeTemplate> tnTemplate = taskService.queryTaskNodeInfoByModel(node);
				List<TaskNodeDto> tnDtos = JSON.parseArray(JSON.toJSONString(tnTemplate), TaskNodeDto.class);
				taskDto.setNodes(tnDtos);
			}
			response.setResCode(0);
			response.setResMsg("查询成功!");
			Map<String, List<TaskDto>> map = new HashMap<>();
			map.put("tasks", td);
			response.setResult(map);
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("流程审批失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/approveTaskNode出现异常", e);
		}
		return response;
	}
	
	/**
	 * 查询审批中流程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCompetedTask", method = RequestMethod.POST)
	public ResponseModel queryApprovalPendingTask(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Task task = JSON.parseObject(JSON.toJSONString(obj), Task.class);
			task.setTaskStatus(2);
			List<TaskTemplate> template = taskService.queryTaskInfoByModel(task);
			List<TaskDto> td = JSON.parseArray(JSON.toJSONString(template), TaskDto.class);
			for (TaskDto taskDto : td) {
				TaskNode node = new TaskNode();
				node.setTaskId(taskDto.getTaskId());
				List<TaskNodeTemplate> tnTemplate = taskService.queryTaskNodeInfoByModel(node);
				List<TaskNodeDto> tnDtos = JSON.parseArray(JSON.toJSONString(tnTemplate), TaskNodeDto.class);
				taskDto.setNodes(tnDtos);
			}
			response.setResCode(0);
			response.setResMsg("查询成功!");
			Map<String, List<TaskDto>> map = new HashMap<>();
			map.put("tasks", td);
			response.setResult(map);
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("流程审批失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/approveTaskNode出现异常", e);
		}
		return response;
	}
	
	

}
