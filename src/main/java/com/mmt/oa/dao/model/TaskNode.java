package com.mmt.oa.dao.model;

import java.util.Date;

public class TaskNode {
    private Integer taskNodeId;

    private Integer taskId;

    private Integer workflowId;

    private Integer workflowNodeId;

    private Integer taskNodeSort;

    private Integer taskNodeApprover;

    private Integer taskNodeStatus;

    private String approvalOpinion;

    private Boolean isValid;

    private String createBy;

    private Date createTime;

    private String modifiedBy;

    private Date modifiedTime;

    public Integer getTaskNodeId() {
        return taskNodeId;
    }

    public void setTaskNodeId(Integer taskNodeId) {
        this.taskNodeId = taskNodeId;
    }

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

    public Integer getWorkflowNodeId() {
        return workflowNodeId;
    }

    public void setWorkflowNodeId(Integer workflowNodeId) {
        this.workflowNodeId = workflowNodeId;
    }

    public Integer getTaskNodeSort() {
        return taskNodeSort;
    }

    public void setTaskNodeSort(Integer taskNodeSort) {
        this.taskNodeSort = taskNodeSort;
    }

    public Integer getTaskNodeApprover() {
        return taskNodeApprover;
    }

    public void setTaskNodeApprover(Integer taskNodeApprover) {
        this.taskNodeApprover = taskNodeApprover;
    }

    public Integer getTaskNodeStatus() {
        return taskNodeStatus;
    }

    public void setTaskNodeStatus(Integer taskNodeStatus) {
        this.taskNodeStatus = taskNodeStatus;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion == null ? null : approvalOpinion.trim();
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
}