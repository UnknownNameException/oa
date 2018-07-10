package com.mmt.oa.dao.model;

import java.util.Date;

public class Workflow {
	private Integer workflowId;

	private String workflowName;

	private String workflowContent;

	private Integer workflowNodeCount;

	private Boolean isValid;

	private String createBy;

	private Date createTime;

	private String modifiedBy;

	private Date modifiedTime;

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
		this.workflowName = workflowName == null ? null : workflowName.trim();
	}

	public String getWorkflowContent() {
		return workflowContent;
	}

	public void setWorkflowContent(String workflowContent) {
		this.workflowContent = workflowContent == null ? null : workflowContent.trim();
	}

	public Integer getWorkflowNodeCount() {
		return workflowNodeCount;
	}

	public void setWorkflowNodeCount(Integer workflowNodeCount) {
		this.workflowNodeCount = workflowNodeCount;
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
		return "Workflow [workflowId=" + workflowId + ", workflowName=" + workflowName + ", workflowContent="
				+ workflowContent + ", workflowNodeCount=" + workflowNodeCount + ", isValid=" + isValid + ", createBy="
				+ createBy + ", createTime=" + createTime + ", modifiedBy=" + modifiedBy + ", modifiedTime="
				+ modifiedTime + "]";
	}

}