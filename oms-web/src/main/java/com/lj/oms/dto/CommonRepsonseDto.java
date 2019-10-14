/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.dto;

import java.io.Serializable;

/**
 * 
 * 类说明：controller返回通用对象
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年4月10日
 */
public class CommonRepsonseDto implements Serializable {

	private static final long serialVersionUID = 7386590680342719679L;

	/**
	 * 返回结果类型 info、success、warning、error、loading .
	 */
	private String type;

	/** * 返回结果信息. */
	private String msg;

	public CommonRepsonseDto() {
		super();
	}

	public CommonRepsonseDto(String type, String msg) {
		super();
		this.type = type;
		this.msg = msg;
	}

	/**
	 * 
	 *
	 * 方法说明：操作成功返回
	 *
	 * @param msg
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	public static CommonRepsonseDto generateSuccessResponse(String msg) {
		return new CommonRepsonseDto("success", msg);
	}

	/**
	 * 
	 *
	 * 方法说明：操作成功返回
	 *
	 * @param msg
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	public static CommonRepsonseDto generateSuccessResponse() {
		return new CommonRepsonseDto("success", null);
	}

	/**
	 * 
	 *
	 * 方法说明：操作失败返回
	 *
	 * @param msg
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	public static CommonRepsonseDto generateFailureResponse(String msg) {
		return new CommonRepsonseDto("error", msg);
	}

	/**
	 * 
	 *
	 * 方法说明：操作失败返回
	 *
	 * @param msg
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	public static CommonRepsonseDto generateFailureResponse() {
		return new CommonRepsonseDto("error", null);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonRepsonseDto [type=");
		builder.append(type);
		builder.append(", msg=");
		builder.append(msg);
		builder.append("]");
		return builder.toString();
	}

}
