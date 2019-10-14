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
 * 类说明：终端登录中控服务器请求参数
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
public class TerminalImLoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4679386202533685422L;

	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;
	
	/**
	 * 登录类型，非空，
	 * GM导购客户端APP登录
	 * ZK中控客户端APP登录
	 */
	private String type;
	
	/**
	 * 终端主键Code
	 */
	private String terminalCode;
	
	/**
	 * 客户端版本名称，中控手机客户端登录时非空
	 */
	private String versionName;
	
	/**
	 * 客户端版本号，中控手机客户端登录时非空
	 */
	private Integer versionCode;
	
	/**
	 * 微信版本名称，中控手机客户端登录时非空
	 */
	private String wxVersionName;

	/**
	 * 微信版本号，中控手机客户端登录时非空
	 */
	private Integer wxVersionCode;
	
	/**
	 * 微信username，中控手机客户端登录时非空
	 */
	private String usernameWx;
	/**
	 * 微信号，导购端登录时非空
	 */
	private String noWx;

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getWxVersionName() {
		return wxVersionName;
	}

	public void setWxVersionName(String wxVersionName) {
		this.wxVersionName = wxVersionName;
	}

	public Integer getWxVersionCode() {
		return wxVersionCode;
	}

	public void setWxVersionCode(Integer wxVersionCode) {
		this.wxVersionCode = wxVersionCode;
	}

	/**
	 * @return the usernameWx
	 */
	public String getUsernameWx() {
		return usernameWx;
	}

	/**
	 * @param usernameWx the usernameWx to set
	 */
	public void setUsernameWx(String usernameWx) {
		this.usernameWx = usernameWx;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerminalImLoginRequest [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", type=");
		builder.append(type);
		builder.append(", terminalCode=");
		builder.append(terminalCode);
		builder.append(", versionName=");
		builder.append(versionName);
		builder.append(", versionCode=");
		builder.append(versionCode);
		builder.append(", wxVersionName=");
		builder.append(wxVersionName);
		builder.append(", wxVersionCode=");
		builder.append(wxVersionCode);
		builder.append(", usernameWx=");
		builder.append(usernameWx);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append("]");
		return builder.toString();
	}

}
