package com.mmt.oa.dao.model;

import java.util.Date;

public class WorkflowNodeTemplate {

	private int workflowNodeId;

	private int workflowId;

	private String workflowNodeName;

	private int sort;

	private String remark;

	private String approver;

	private String approverName;

	private Boolean isValid;

	private String createBy;

	private String createTime;

	private String modifiedBy;

	private String modifiedTime;

	public int getWorkflowNodeId() {
		return workflowNodeId;
	}

	public void setWorkflowNodeId(int workflowNodeId) {
		this.workflowNodeId = workflowNodeId;
	}

	public int getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowNodeName() {
		return workflowNodeName;
	}

	public void setWorkflowNodeName(String workflowNodeName) {
		this.workflowNodeName = workflowNodeName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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

	@Override
	public String toString() {
		return "WorkflowNodeTemplate [workflowNodeId=" + workflowNodeId + ", workflowId=" + workflowId
				+ ", workflowNodeName=" + workflowNodeName + ", sort=" + sort + ", remark=" + remark + ", approver="
				+ approver + ", approverName=" + approverName + ", isValid=" + isValid + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime + "]";
	}

}
