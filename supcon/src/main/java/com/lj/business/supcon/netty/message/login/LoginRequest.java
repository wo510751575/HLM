/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.login;

import com.lj.business.supcon.netty.message.BaseRequest;

/**
 * 
 * 类说明：IM登录请求报文参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月23日
 */
public class LoginRequest extends BaseRequest {

	private static final long serialVersionUID = -4587855958989318537L;

	/**
	 * APP登录时的返回的访问令牌，非空
	 */
	private String token;
	
	/**
	 * 登录类型，非空
	 */
	private String type;
	
	/**
	 * 客户端时间戳，非空
	 */
	private long timestamp;
	
	/**
	 * 客户端版本名称，中控手机客户端登录时非空
	 */
	private String versionName;
	
	/**
	 * 客户端版本号，中控手机客户端登录时非空
	 */
	private int versionCode;
	
	/**
	 * 微信版本号，中控手机客户端登录时非空
	 */
	private int wxVersionCode;
	
	/**
	 * 微信版本名称，中控手机客户端登录时非空
	 */
	private String wxVersionName;
	
	/**
	 * 微信username，中控手机客户端登录时非空
	 */
	private String usernameWx;
	/**
	 * 微信号
	 */
	private String noWx;
	
	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
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
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return the versionCode
	 */
	public int getVersionCode() {
		return versionCode;
	}

	/**
	 * @param versionCode the versionCode to set
	 */
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return the wxVersionCode
	 */
	public int getWxVersionCode() {
		return wxVersionCode;
	}

	/**
	 * @param wxVersionCode the wxVersionCode to set
	 */
	public void setWxVersionCode(int wxVersionCode) {
		this.wxVersionCode = wxVersionCode;
	}

	/**
	 * @return the wxVersionName
	 */
	public String getWxVersionName() {
		return wxVersionName;
	}

	/**
	 * @param wxVersionName the wxVersionName to set
	 */
	public void setWxVersionName(String wxVersionName) {
		this.wxVersionName = wxVersionName;
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
		builder.append("LoginRequest [token=");
		builder.append(token);
		builder.append(", type=");
		builder.append(type);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", versionName=");
		builder.append(versionName);
		builder.append(", versionCode=");
		builder.append(versionCode);
		builder.append(", wxVersionCode=");
		builder.append(wxVersionCode);
		builder.append(", wxVersionName=");
		builder.append(wxVersionName);
		builder.append(", usernameWx=");
		builder.append(usernameWx);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append("]");
		return builder.toString();
	}

}
