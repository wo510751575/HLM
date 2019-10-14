/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.terminalimstatus;

import java.io.Serializable;

/**
 * 
 * 类说明：终端登出中控服务器请求参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月2日
 */
public class TerminalImLogoutRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1601721971234292818L;

	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;
	
	/**
	 * 登出类型，非空，
	 * GM导购客户端APP登出
	 * ZK中控客户端APP登出
	 */
	private String type;
	
	/**
	 * 手机串号，ZK中控客户端APP非空
	 */
	private String terminalCode;

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerminalImLogoutRequest [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", type=");
		builder.append(type);
		builder.append(", terminalCode=");
		builder.append(terminalCode);
		builder.append("]");
		return builder.toString();
	}

}
