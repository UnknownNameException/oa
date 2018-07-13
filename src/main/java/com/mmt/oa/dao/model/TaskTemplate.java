package com.mmt.oa.dao.model;

import java.util.Date;

public class TaskTemplate {

	private Integer taskId;

	private Integer workflowId;

	private String workflowName;

	private String workflowContent;

	private String remark;

	private Integer taskNodeCount;

	private Integer taskStatus;

	private Boolean isValid;

	private String createBy;

	private String createTime;

	private String modifiedBy;

	private String modifiedTime;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getWorkflowContent() {
		return workflowContent;
	}

	public void setWorkflowContent(String workflowContent) {
		this.workflowContent = workflowContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTaskNodeCount() {
		return taskNodeCount;
	}

	public void setTaskNodeCount(Integer taskNodeCount) {
		this.taskNodeCount = taskNodeCount;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "TaskTemplate [taskId=" + taskId + ", workflowId=" + workflowId + ", workflowName=" + workflowName
				+ ", workflowContent=" + workflowContent + ", remark=" + remark + ", taskNodeCount=" + taskNodeCount
				+ ", taskStatus=" + taskStatus + ", isValid=" + isValid + ", createBy=" + createBy + ", createTime="
				+ createTime + ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime + "]";
	}

}
