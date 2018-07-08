package com.mmt.oa.model;

import java.util.List;

public class ResponseModel {

	private int resCode;

	private String resMsg;

	private Object result;

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ResponseModel [resCode=" + resCode + ", resMsg=" + resMsg + ", result=" + result + "]";
	}

}
