/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message;

import java.io.Serializable;

import com.lj.base.mvc.base.json.JsonUtils;

/**
 * 
 * 类说明：DTO基类
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
public class BaseDto implements Serializable {

	private static final long serialVersionUID = -7104444570172121339L;
	
	/**
	 * 
	 *
	 * 方法说明：将对象转为json格式字符串
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月14日
	 *
	 */
	public String toJson() {
		return JsonUtils.jsonFromObject_AllToString(this);
	}

}
