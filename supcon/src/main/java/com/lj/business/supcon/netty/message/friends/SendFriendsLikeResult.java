/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.friends;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：发送朋友圈点赞结果返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月21日
 */
public class SendFriendsLikeResult extends BaseResponse {

	private static final long serialVersionUID = 918007288835828310L;

	/**
	 * 点赞CODE,服务器点赞CODE
	 */
	private String code;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendFriendsLikeResult [");
		builder.append(super.toString());
		builder.append(", code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}

}
