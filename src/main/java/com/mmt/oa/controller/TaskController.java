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
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.mmt.oa.dao.model.Task;
import com.mmt.oa.dao.model.TaskNode;
import com.mmt.oa.dao.model.TaskNodeTemplate;
import com.mmt.oa.dao.model.TaskTemplate;
import com.mmt.oa.dao.model.WorkflowNode;
import com.mmt.oa.dao.model.WorkflowNodeTemplate;
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

	/**
	 * 新增流程任务(开启流程)
	 * 
	 * @param request
	 * @return
	 */
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

	/**
	 * 修改流程节点状态(审批流程)
	 * 
	 * @param request
	 * @return
	 */
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
			} else if (i == 2) {
				response.setResCode(0);
				response.setResMsg("流程全部审批完成!");
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
		int pageNum = 1;
		int pageSize = 10;
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			JSONObject jo = JSON.parseObject(JSONObject.toJSONString(obj));
			if (jo.containsKey("pageNum")) {
				pageNum = jo.getInteger("pageNum");
			}
			if (jo.containsKey("pageSize")) {
				pageSize = jo.getInteger("pageSize");
			}
			PageHelper.startPage(pageNum, pageSize);
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
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCompetedTask", method = RequestMethod.POST)
	public ResponseModel queryCompetedTask(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		int pageNum = 1;
		int pageSize = 10;
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			JSONObject jo = JSON.parseObject(JSONObject.toJSONString(obj));
			if (jo.containsKey("pageNum")) {
				pageNum = jo.getInteger("pageNum");
			}
			if (jo.containsKey("pageSize")) {
				pageSize = jo.getInteger("pageSize");
			}
			PageHelper.startPage(pageNum, pageSize);
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
			response.setResMsg("查询归档流程失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/queryCompetedTask出现异常", e);
		}
		return response;
	}

	/**
	 * 查询审批中流程
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryApprovalPendingTask", method = RequestMethod.POST)
	public ResponseModel queryApprovalPendingTask(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		int pageNum = 1;
		int pageSize = 10;
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			JSONObject jo = JSON.parseObject(JSONObject.toJSONString(obj));
			if (jo.containsKey("pageNum")) {
				pageNum = jo.getInteger("pageNum");
			}
			if (jo.containsKey("pageSize")) {
				pageSize = jo.getInteger("pageSize");
			}
			PageHelper.startPage(pageNum, pageSize);
			Task task = JSON.parseObject(JSON.toJSONString(obj), Task.class);
			task.setTaskStatus(1);
			List<TaskTemplate> template = taskService.queryTaskInfoByModel(task);
			List<TaskDto> td = JSON.parseArray(JSON.toJSONString(template), TaskDto.class);
			for (TaskDto taskDto : td) {
				TaskNode node = new TaskNode();
				node.setTaskId(taskDto.getTaskId());
				List<TaskNodeTemplate> tnTemplate = taskService.queryTaskNodeInfoByModel(node);
				List<TaskNodeDto> tnDtos = JSON.parseArray(JSON.toJSONString(tnTemplate), TaskNodeDto.class);
				TaskNodeDto dto = tnDtos.get(tnDtos.size() - 1);
				List<TaskNodeDto> uncheckeds = queryUnckeckedNode(dto, tnDtos.size());
				tnDtos.addAll(uncheckeds);
				taskDto.setNodes(tnDtos);
			}
			response.setResCode(0);
			response.setResMsg("查询成功!");
			Map<String, List<TaskDto>> map = new HashMap<>();
			map.put("tasks", td);
			response.setResult(map);
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("查询审批中流程失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/queryApprovalPendingTask出现异常", e);
		}
		return response;
	}

	/**
	 * 查询流程节点详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTaskNodeDetailInfo", method = RequestMethod.POST)
	public ResponseModel queryTaskNodeDetailInfo(@RequestBody RequestModel request) {
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
			node.setIsValid(true);
			List<TaskNodeTemplate> templates = taskService.queryTaskNodeInfoByModel(node);
			if (templates.isEmpty() || templates == null) {
				WorkflowNode wn = new WorkflowNode();
				wn.setWorkflowNodeId(node.getWorkflowNodeId());
				wn.setWorkflowId(node.getWorkflowId());
				wn.setSort(node.getTaskNodeSort());
				wn.setIsValid(true);
				List<WorkflowNodeTemplate> wns = workflowService.queryWorkflowNodeTemplateByModel(wn);
				WorkflowNodeTemplate wnt = wns.get(0);
				TaskNodeDto tnd = new TaskNodeDto();
				tnd.setTaskId(node.getTaskId());
				tnd.setTaskNodeId(wnt.getWorkflowNodeId());
				tnd.setWorkflowId(wnt.getWorkflowId());
				tnd.setWorkflowNodeId(wnt.getWorkflowNodeId());
				tnd.setTaskNodeApprover(wnt.getApprover());
				tnd.setTaskNodeApproverName(wnt.getApproverName());
				tnd.setTaskNodeSort(wnt.getSort());
				tnd.setTaskNodeStatus(0);
				tnd.setIsValid(false);
				response.setResCode(0);
				response.setResMsg("流程节点查询成功!");
				Map<String, TaskNodeDto> map = new HashMap<>();
				map.put("TaskNode", tnd);
				response.setResult(map);
			} else {
				TaskNodeDto dto = JSON.parseObject(JSON.toJSONString(templates.get(0)), TaskNodeDto.class);
				response.setResCode(0);
				response.setResMsg("流程节点查询成功!");
				Map<String, TaskNodeDto> map = new HashMap<>();
				map.put("TaskNode", dto);
				response.setResult(map);
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("查询流程节点失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/queryTaskNodeDetailInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 查询单条流程(包含节点)所有信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTaskDetailInfo", method = RequestMethod.POST)
	public ResponseModel queryTaskDetailInfo(@RequestBody RequestModel request) {
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
			List<TaskTemplate> temp = taskService.queryTaskInfoByModel(task);
			TaskDto td = JSON.parseObject(JSON.toJSONString(temp.get(0)), TaskDto.class);
			TaskNode node = new TaskNode();
			node.setTaskId(td.getTaskId());
			node.setIsValid(true);
			List<TaskNodeTemplate> templates = taskService.queryTaskNodeInfoByModel(node);
			List<TaskNodeDto> nodes = JSON.parseArray(JSON.toJSONString(templates), TaskNodeDto.class);
			TaskNodeDto dto = nodes.get(nodes.size() - 1);
			List<TaskNodeDto> uncheckeds = queryUnckeckedNode(dto, nodes.size());
			nodes.addAll(uncheckeds);
			td.setNodes(nodes);
			response.setResCode(0);
			response.setResMsg("流程详细信息查询成功");
			Map<String, TaskDto> map = new HashMap<>();
			map.put("task", td);
			response.setResult(map);
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("查询流程失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/task/queryTaskDetailInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 查询未开始审批节点
	 * 
	 * @param dto
	 * @param start
	 * @return
	 */
	public List<TaskNodeDto> queryUnckeckedNode(TaskNodeDto dto, int start) {
		List<TaskNodeDto> tns = new ArrayList<>();
		WorkflowNode wn = new WorkflowNode();
		wn.setWorkflowId(dto.getWorkflowId());
		wn.setIsValid(true);
		List<WorkflowNodeTemplate> wns = workflowService.queryWorkflowNodeTemplateInfo(wn);
		for (int i = start; i < wns.size(); i++) {
			WorkflowNodeTemplate wnt = wns.get(i);
			TaskNodeDto tnd = new TaskNodeDto();
			tnd.setTaskId(dto.getTaskId());
			tnd.setTaskNodeId(wnt.getWorkflowNodeId());
			tnd.setWorkflowId(wnt.getWorkflowId());
			tnd.setWorkflowNodeId(wnt.getWorkflowNodeId());
			tnd.setTaskNodeApprover(wnt.getApprover());
			tnd.setTaskNodeApproverName(wnt.getApproverName());
			tnd.setTaskNodeSort(wnt.getSort());
			tnd.setTaskNodeStatus(0);
			tnd.setIsValid(false);
			tns.add(tnd);
		}
		return tns;
	}

}
