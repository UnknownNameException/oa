package com.mmt.oa.model;

import java.util.Date;
import java.util.List;

public class TaskDto {

	private Integer taskId;

	private Integer workflowId;

	private String remark;

	private Integer taskNodeCount;

	private Integer taskStatus;

	private Boolean isValid;

	private String createBy;

	private Date createTime;

	private String modifiedBy;

	private Date modifiedTime;

	private List<TaskNodeDto> nodes;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<TaskNodeDto> getNodes() {
		return nodes;
	}

	public void setNodes(List<TaskNodeDto> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "TaskDto [taskId=" + taskId + ", workflowId=" + workflowId + ", remark=" + remark + ", taskNodeCount="
				+ taskNodeCount + ", taskStatus=" + taskStatus + ", isValid=" + isValid + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime
				+ ", nodes=" + nodes + "]";
	}

}
