package com.mmt.oa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mmt.oa.dao.model.User;
import com.mmt.oa.dao.model.Workflow;
import com.mmt.oa.dao.model.WorkflowNode;
import com.mmt.oa.dao.model.WorkflowNodeTemplate;
import com.mmt.oa.emun.Constant;
import com.mmt.oa.model.RequestModel;
import com.mmt.oa.model.ResponseModel;
import com.mmt.oa.model.WorkflowNodeTemplateDto;
import com.mmt.oa.model.WorkflowTemplateDto;
import com.mmt.oa.service.UserService;
import com.mmt.oa.service.Impl.WorkflowServiceImpl;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

	private static Logger logger = LoggerFactory.getLogger(WorkflowController.class);

	@Autowired
	private WorkflowServiceImpl workflowServiceImpl;

	/**
	 * 添加工作流模板
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addNewWorkflow", method = RequestMethod.POST)
	public ResponseModel addNewWorkflow(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Workflow workflow = JSON.parseObject(JSON.toJSONString(obj), Workflow.class);
			workflow.setWorkflowNodeCount(0);
			workflow.setWorkflowId(null);
			workflow.setModifiedBy(workflow.getCreateBy());
			workflow.setCreateTime(new Date());
			workflow.setModifiedTime(new Date());
			Integer i = workflowServiceImpl.addNewWorkflow(workflow);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,添加流程模版失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("添加流程模版成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("添加流程模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/addNewWorkflow出现异常", e);
		}
		return response;
	}

	/**
	 * 添加工作流节点模板
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addNewWorkflowNode", method = RequestMethod.POST)
	public ResponseModel addNewWorkflowNode(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			WorkflowNode node = JSON.parseObject(JSON.toJSONString(obj), WorkflowNode.class);
			node.setIsValid(true);
			node.setCreateTime(new Date());
			node.setModifiedBy(node.getCreateBy());
			node.setModifiedTime(new Date());
			Integer i = workflowServiceImpl.addNewWorkflowNode(node);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,添加流程节点模版失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("添加流程节点模版成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("添加流程节点模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/addNewWorkflowNode出现异常", e);
		}
		return response;
	}

	/**
	 * 修改流程模板信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyWorkflowInfo", method = RequestMethod.POST)
	public ResponseModel modifyWorkflowInfo(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Workflow workflow = JSON.parseObject(JSON.toJSONString(obj), Workflow.class);
			workflow.setWorkflowNodeCount(null);
			workflow.setIsValid(null);
			Integer i = workflowServiceImpl.modifyWorkflowInfo(workflow);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,修改流程模版失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("修改流程模版成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("修改流程模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/modifyWorkflowInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 修改流程节点模板信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyWorkflowNodeInfo", method = RequestMethod.POST)
	public ResponseModel modifyWorkflowNodeInfo(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			WorkflowNode node = JSON.parseObject(JSON.toJSONString(obj), WorkflowNode.class);
			node.setIsValid(null);
			Integer i = workflowServiceImpl.modifyWorkflowNodeInfo(node);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,修改流程节点模版失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("修改流程节点模版成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("修改流程节点模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/modifyWorkflowNodeInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 删除流程模板(模板删除,对应的节点也一并删除)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteWorkflowInfo", method = RequestMethod.POST)
	public ResponseModel deleteWorkflowInfo(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			Workflow workflow = JSON.parseObject(JSON.toJSONString(obj), Workflow.class);
			workflow.setIsValid(false);
			Integer i = workflowServiceImpl.deleteWorkflowInfo(workflow);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,删除流程模版失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("删除流程模版成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("删除流程模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/deleteWorkflowInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 删除流程节点模板
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteWorkflowNodeInfo", method = RequestMethod.POST)
	public ResponseModel deleteWorkflowNodeInfo(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		// 验证token
		if (StringUtils.isEmpty(request.getToken()) || !request.getToken().equals(Constant.TOKEN)) {
			response.setResCode(1);
			response.setResMsg("接口调用,token验证失败");
			return response;
		}

		try {
			Object obj = request.getContent();
			WorkflowNode node = JSON.parseObject(JSON.toJSONString(obj), WorkflowNode.class);
			node.setIsValid(false);
			Integer i = workflowServiceImpl.deleteWorkflowNodeInfo(node);
			if (i == 0) {
				response.setResCode(1);
				response.setResMsg("内部异常,删除流程节点模版失败,请稍后重试!");
			} else {
				response.setResCode(0);
				response.setResMsg("删除流程节点模版成功!");
			}
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("删除流程节点模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/deleteWorkflowNodeInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 查询已创建的工作流模板
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryWorkflowInfo", method = RequestMethod.POST)
	public ResponseModel queryWorkflowInfo(@RequestBody RequestModel request) {
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
			Workflow workflow = JSON.parseObject(JSON.toJSONString(obj), Workflow.class);
			List<Workflow> workflows = workflowServiceImpl.queryWorkflowInfo(workflow);
			List<WorkflowTemplateDto> dtos = JSON.parseArray(JSON.toJSONString(workflows), WorkflowTemplateDto.class);
			if (workflows == null || workflows.isEmpty()) {
				response.setResCode(1);
				response.setResMsg("未查询到数据,请更改查询条件后重试!");
			} else {
				for (WorkflowTemplateDto dto : dtos) {
					WorkflowNode node = new WorkflowNode();
					node.setWorkflowId(dto.getWorkflowId());
					node.setIsValid(true);
					List<WorkflowNodeTemplate> nodes = workflowServiceImpl.queryWorkflowNodeTemplateInfo(node);
					List<WorkflowNodeTemplateDto> nodeDtos = new ArrayList<>();
					for (WorkflowNodeTemplate template : nodes) {
						WorkflowNodeTemplateDto tem = JSON.parseObject(JSON.toJSONString(template),
								WorkflowNodeTemplateDto.class);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
						tem.setCreateTime(sdf.format(template.getCreateTime()));
						tem.setModifiedTime(sdf.format(template.getModifiedTime()));
						nodeDtos.add(tem);
					}
					dto.setWorkflowNodes(nodeDtos);
				}
				response.setResCode(0);
				response.setResMsg("查询成功!");
				Map<String, List<WorkflowTemplateDto>> map = new HashMap<>();
				map.put("WorkflowTemplates", dtos);
				response.setResult(map);
			}

		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("查询流程模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/queryWorkflowInfo出现异常", e);
		}
		return response;
	}

	/**
	 * 查询已创建的工作流节点模板信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryWorkflowNodeInfo", method = RequestMethod.POST)
	public ResponseModel queryWorkflowNodeInfo(@RequestBody RequestModel request) {
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
			WorkflowNode node = JSON.parseObject(JSON.toJSONString(obj), WorkflowNode.class);
			List<WorkflowNodeTemplate> nodes = workflowServiceImpl.queryWorkflowNodeTemplateInfo(node);
			List<WorkflowNodeTemplateDto> nodeDtos = new ArrayList<>();
			for (WorkflowNodeTemplate template : nodes) {
				WorkflowNodeTemplateDto tem = JSON.parseObject(JSON.toJSONString(template),
						WorkflowNodeTemplateDto.class);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
				tem.setCreateTime(sdf.format(template.getCreateTime()));
				tem.setModifiedTime(sdf.format(template.getModifiedTime()));
				nodeDtos.add(tem);
			}
			response.setResCode(0);
			response.setResMsg("查询成功!");
			Map<String, List<WorkflowNodeTemplateDto>> map = new HashMap<>();
			map.put("WorkflowTemplateNodes", nodeDtos);
			response.setResult(map);

		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("查询流程节点模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/queryWorkflowNodeInfo出现异常", e);
		}
		return response;
	}
}
