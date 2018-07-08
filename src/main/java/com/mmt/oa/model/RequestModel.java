package com.mmt.oa.model;

import java.util.List;

public class RequestModel {

	private String token;

	private Object content;

	private int pageNum;

	private int pageSize;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "RequestModel [token=" + token + ", content=" + content + ", pageNum=" + pageNum + ", pageSize="
				+ pageSize + "]";
	}

}
