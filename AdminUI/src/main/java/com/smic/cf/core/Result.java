package com.smic.cf.core;

import java.io.Serializable;

/**
 * 
 * @Description: :返回对象实体 code为状态码、msg为提示信息、data为返回的数据
 * @author cai feng
 * @date 2019年7月17日
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	public int code;
	private String msg;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result success() {
		Result result = new Result();
		result.setCode(code);
		return result;
	}
}
