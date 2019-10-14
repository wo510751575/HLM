/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.login;

import java.io.Serializable;
import java.util.Date;

import com.lj.business.supcon.netty.enums.ConnectSource;

/**
 * 
 * 类说明：客户端登录状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月4日
 */
public class ClientLoginStatus implements Serializable {

	private static final long serialVersionUID = 8203472135423399963L;

	/** 导购编号 **/
	private String memberNoGm;
	
	/**	手机串号 **/
	private String imei;
	
	/** 连接来源 **/
	private ConnectSource connectSource;
	
	/** 上次登录时间 **/
	private Date loginTime;
	
	/** 上次登出时间 **/
	private Date logoutTime;

	/** ip地址 **/
	private String ipAddr;
	
	/**
	 * 
	 *
	 * 方法说明：获取最后一次登录登出时间
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
	public Date getLastTime() {
		if(logoutTime == null) {
			return loginTime;
		} else {
			return loginTime.after(logoutTime) ? loginTime : logoutTime;
		}
	}

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
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the connectSource
	 */
	public ConnectSource getConnectSource() {
		return connectSource;
	}

	/**
	 * @param connectSource the connectSource to set
	 */
	public void setConnectSource(ConnectSource connectSource) {
		this.connectSource = connectSource;
	}

	/**
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the logoutTime
	 */
	public Date getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @param logoutTime the logoutTime to set
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientLoginStatus [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", connectSource=");
		builder.append(connectSource);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append(", logoutTime=");
		builder.append(logoutTime);
		builder.append(", ipAddr=");
		builder.append(ipAddr);
		builder.append("]");
		return builder.toString();
	}
	
}
