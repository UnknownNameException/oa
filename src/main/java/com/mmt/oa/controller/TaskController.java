package com.mmt.oa.controller;

import java.util.Date;

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
import com.mmt.oa.emun.Constant;
import com.mmt.oa.model.RequestModel;
import com.mmt.oa.model.ResponseModel;
import com.mmt.oa.service.Impl.TaskServiceImpl;

@RestController
@RequestMapping("/task")
public class TaskController {

	private static Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskServiceImpl taskService;
	
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
			taskService.addNewTask(task);
			
		} catch (Exception e) {
			response.setResCode(1);
			response.setResMsg("添加流程模版失败!" + e.toString() + ":" + e.getMessage());
			logger.error("/workflow/addNewWorkflow出现异常", e);
		}
		
		
		
		return null;
	}
	
}
