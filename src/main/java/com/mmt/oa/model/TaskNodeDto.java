package com.mmt.oa.model;

public class TaskNodeDto {

	private int taskNodeId;

	private int taskId;

	private int workflowId;

	private int workflowNodeId;

	private String approvalOpinion;

	private int taskNodeSort;

	private String taskNodeApprover;

	private String taskNodeApproverName;

	private int taskNodeStatus;

	private Boolean isValid;

	private String createBy;

	private String createTime;

	private String modifiedBy;

	private String modifiedTime;

	public int getTaskNodeId() {
		return taskNodeId;
	}

	public void setTaskNodeId(int taskNodeId) {
		this.taskNodeId = taskNodeId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}

	public int getWorkflowNodeId() {
		return workflowNodeId;
	}

	public void setWorkflowNodeId(int workflowNodeId) {
		this.workflowNodeId = workflowNodeId;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

	public int getTaskNodeSort() {
		return taskNodeSort;
	}

	public void setTaskNodeSort(int taskNodeSort) {
		this.taskNodeSort = taskNodeSort;
	}

	public String getTaskNodeApprover() {
		return taskNodeApprover;
	}

	public void setTaskNodeApprover(String taskNodeApprover) {
		this.taskNodeApprover = taskNodeApprover;
	}

	public int getTaskNodeStatus() {
		return taskNodeStatus;
	}

	public void setTaskNodeStatus(int taskNodeStatus) {
		this.taskNodeStatus = taskNodeStatus;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getTaskNodeApproverName() {
		return taskNodeApproverName;
	}

	public void setTaskNodeApproverName(String taskNodeApproverName) {
		this.taskNodeApproverName = taskNodeApproverName;
	}

	@Override
	public String toString() {
		return "TaskNodeDto [taskNodeId=" + taskNodeId + ", taskId=" + taskId + ", workflowId=" + workflowId
				+ ", workflowNodeId=" + workflowNodeId + ", approvalOpinion=" + approvalOpinion + ", taskNodeSort="
				+ taskNodeSort + ", taskNodeApprover=" + taskNodeApprover + ", taskNodeApproverName="
				+ taskNodeApproverName + ", taskNodeStatus=" + taskNodeStatus + ", isValid=" + isValid + ", createBy="
				+ createBy + ", createTime=" + createTime + ", modifiedBy=" + modifiedBy + ", modifiedTime="
				+ modifiedTime + "]";
	}

}
