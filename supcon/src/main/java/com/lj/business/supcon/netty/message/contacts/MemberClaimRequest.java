/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.contacts;

import com.lj.business.supcon.netty.message.BaseRequest;

/**
 * 
 * 类说明：请求认领客户参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月16日
 */
public class MemberClaimRequest extends BaseRequest {

	private static final long serialVersionUID = 2162726996193081385L;

	/**
	 * 认领code，非空
	 */
	private String mbrCode;
	
	/**
	 * 认领标识，true认领、false不认领
	 */
	private boolean flag;

	/**
	 * @return the mbrCode
	 */
	public String getMbrCode() {
		return mbrCode;
	}

	/**
	 * @param mbrCode the mbrCode to set
	 */
	public void setMbrCode(String mbrCode) {
		this.mbrCode = mbrCode;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberClaimRequest [mbrCode=");
		builder.append(mbrCode);
		builder.append(", flag=");
		builder.append(flag);
		builder.append("]");
		return builder.toString();
	}
}
