package com.mmt.oa.dao.model;

import java.util.Date;

public class WorkflowNodeTemplate {

	private int workflowNodeId;

	private int workflowId;

	private String workflowNodeName;

	private int sort;

	private String describe;

	private String approver;

	private String approverName;

	private Boolean isValid;

	private String createBy;

	private Date createTime;

	private String modifiedBy;

	private Date modifiedTime;

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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "WorkflowNodeTemplate [workflowNodeId=" + workflowNodeId + ", workflowId=" + workflowId
				+ ", workflowNodeName=" + workflowNodeName + ", sort=" + sort + ", describe=" + describe + ", approver="
				+ approver + ", approverName=" + approverName + ", isValid=" + isValid + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime + "]";
	}

}
