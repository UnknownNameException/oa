package com.mmt.oa.model;

import java.util.Date;

public class UserReturn {

	private Integer userId;

	private String name;

	private String department;

	private Integer roleId;

	private String userName;

	private Boolean isValid;

	private Date createTime;

	private String createBy;

	private Date modifiedTime;

	private String modifiedBy;

	private int isSuccess;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "UserReturn [userId=" + userId + ", name=" + name + ", department=" + department + ", roleId=" + roleId
				+ ", userName=" + userName + ", isValid=" + isValid + ", createTime=" + createTime + ", createBy="
				+ createBy + ", modifiedTime=" + modifiedTime + ", modifiedBy=" + modifiedBy + ", isSuccess="
				+ isSuccess + "]";
	}

}
