/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳前海顶顶网络科技有限公司 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.dto;

import java.io.Serializable;

/**
 * 
 * 类说明：Eoms请求返回对象。
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 深圳前海顶顶网络科技有限公司
 * @author lhy
 * 
 *         CreateDate: 2017年8月24日
 */
public class ResponseDto implements Serializable {
	private static final long serialVersionUID = -7629309590846227983L;
	/*** 操作是否成功.true 成功 false失败 */
	private boolean result;

	/** * 返回结果Code. */
	private String code;

	/** * 返回结果信息. */
	private String msg;

	/** * 返回数据的对象（操作成功才有）. */
	private Object data;

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public static ResponseDto successResp(Object respData) {
		return createResp(Boolean.TRUE, "", "", respData);
	}
	
	public static ResponseDto successResp() {
		return createResp(Boolean.TRUE, "", "", "");
	}
	
	public static ResponseDto failureResp(String errorCode, String errorMessage) {
		return createResp(Boolean.FALSE, errorCode, errorMessage, null);
	}

	public static ResponseDto createResp(boolean isSucess, String errorCode,
			String errorMessage, Object respData) {
		ResponseDto r = new ResponseDto();
		r.setResult(isSucess);
		r.setCode(errorCode);
		r.setMsg(errorMessage);
		r.setData(respData);
		return r;
	}

	 
}