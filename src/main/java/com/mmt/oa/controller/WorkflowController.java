package com.mmt.oa.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.oa.model.ResponseModel;
import com.mmt.oa.model.WorkflowTemplateDto;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

	@RequestMapping(value = "/addNewWorkflow", method = RequestMethod.POST)
	public ResponseModel addNewWorkflow(@RequestBody WorkflowTemplateDto workflow) {
		return null;
	}

	@RequestMapping(value = "/addNewWorkflowNode", method = RequestMethod.POST)
	public ResponseModel addNewWorkflowNode(@RequestBody WorkflowTemplateDto workflow) {
		return null;
	}
	
	@RequestMapping(value = "/modifyWorkflowInfo", method = RequestMethod.POST)
	public ResponseModel modifyWorkflowInfo(@RequestBody WorkflowTemplateDto workflow) {
		return null;
	}

	@RequestMapping(value = "/modifyWorkflowNodeInfo", method = RequestMethod.POST)
	public ResponseModel modifyWorkflowNodeInfo(@RequestBody WorkflowTemplateDto workflow) {
		return null;
	}

	@RequestMapping(value = "/deleteWorkflowInfo", method = RequestMethod.POST)
	public ResponseModel deleteWorkflowInfo(@RequestBody WorkflowTemplateDto workflow) {
		return null;
	}

	@RequestMapping(value = "/deleteWorkflowNodeInfo", method = RequestMethod.POST)
	public ResponseModel deleteWorkflowNodeInfo(@RequestBody WorkflowTemplateDto workflow) {
		return null;
	}

	@RequestMapping(value = "/queryWorkflowInfo", method = RequestMethod.POST)
	public ResponseModel queryWorkflowInfo(@RequestBody WorkflowTemplateDto workflow) {
		return null;
	}
}
