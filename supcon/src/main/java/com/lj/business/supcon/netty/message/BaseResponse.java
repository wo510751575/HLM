/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message;


/**
 * 
 * 类说明：响应报文参数对象基类，所有响应报文参数都必须继承此基类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月19日
 */
public class BaseResponse extends BaseDto {

	private static final long serialVersionUID = -8046205127390041494L;
	
	/*** 操作是否成功，默认为成功. */
	private boolean result = Boolean.TRUE; 

	/** * 返回结果Code. */
	private String code;

	/** * 返回结果信息. */
	private String message;
	
	public BaseResponse() {
		super();
	}
	
	public BaseResponse(ResponseCode responseCode) {
		super();
		this.result = Boolean.FALSE;
		this.code = responseCode.getCode();
		this.message = responseCode.getMessage();
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
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
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseResponse [result=");
		builder.append(result);
		builder.append(", code=");
		builder.append(code);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
