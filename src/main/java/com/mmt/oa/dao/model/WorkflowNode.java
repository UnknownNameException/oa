package com.mmt.oa.dao.model;

import java.util.Date;

public class WorkflowNode {
	private Integer workflowNodeId;

	private Integer workflowId;

	private String workflowNodeName;

	private Integer sort;

	private String remark;

	private String approver;

	private Boolean isValid;

	private String createBy;

	private Date createTime;

	private String modifiedBy;

	private Date modifiedTime;

	public Integer getWorkflowNodeId() {
		return workflowNodeId;
	}

	public void setWorkflowNodeId(Integer workflowNodeId) {
		this.workflowNodeId = workflowNodeId;
	}

	public Integer getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowNodeName() {
		return workflowNodeName;
	}

	public void setWorkflowNodeName(String workflowNodeName) {
		this.workflowNodeName = workflowNodeName == null ? null : workflowNodeName.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
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
		this.approver = approver == null ? null : approver.trim();
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
		this.createBy = createBy == null ? null : createBy.trim();
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
		this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "WorkflowNode [workflowNodeId=" + workflowNodeId + ", workflowId=" + workflowId + ", workflowNodeName="
				+ workflowNodeName + ", sort=" + sort + ", remark=" + remark + ", approver=" + approver + ", isValid="
				+ isValid + ", createBy=" + createBy + ", createTime=" + createTime + ", modifiedBy=" + modifiedBy
				+ ", modifiedTime=" + modifiedTime + "]";
	}

}