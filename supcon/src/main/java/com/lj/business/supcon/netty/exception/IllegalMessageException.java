/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.exception;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
public class IllegalMessageException extends RuntimeException {

	private static final long serialVersionUID = 8267456625789948715L;

	public IllegalMessageException() {
		super();
	}

	public IllegalMessageException(String message) {
		super(message);
	}
}
