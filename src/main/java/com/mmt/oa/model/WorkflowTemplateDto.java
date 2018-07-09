package com.mmt.oa.model;

import java.util.List;

public class WorkflowTemplateDto {

	private int workflowId;

	private String workflowName;

	private int workflowNodeCount;

	private String workflowContent;

	private Boolean isValid;

	private String createBy;

	private String createTime;

	private String modifiedBy;

	private String modifiedTime;

	private List<WorkflowNodeTemplateDto> workflowNodes;

	public int getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public int getWorkflowNodeCount() {
		return workflowNodeCount;
	}

	public void setWorkflowNodeCount(int workflowNodeCount) {
		this.workflowNodeCount = workflowNodeCount;
	}

	public String getWorkflowContent() {
		return workflowContent;
	}

	public void setWorkflowContent(String workflowContent) {
		this.workflowContent = workflowContent;
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

	public List<WorkflowNodeTemplateDto> getWorkflowNodes() {
		return workflowNodes;
	}

	public void setWorkflowNodes(List<WorkflowNodeTemplateDto> workflowNodes) {
		this.workflowNodes = workflowNodes;
	}

	@Override
	public String toString() {
		return "WorkflowTemplateDto [workflowId=" + workflowId + ", workflowName=" + workflowName
				+ ", workflowNodeCount=" + workflowNodeCount + ", workflowContent=" + workflowContent + ", isValid="
				+ isValid + ", createBy=" + createBy + ", createTime=" + createTime + ", modifiedBy=" + modifiedBy
				+ ", modifiedTime=" + modifiedTime + ", workflowNodes=" + workflowNodes + "]";
	}

}
