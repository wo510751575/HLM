/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 类说明：客户端查询离线聊天记录请求参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月30日
 */
public class FindOfflineChatInfo implements Serializable {

	private static final long serialVersionUID = -6560339886887034513L;
	
    /**
     * 客户端标识：1导购客户端、2中控客户端，非空
     */
    private Integer clientFlag;

    /**
     * 导购编号，导购客户端时非空 .
     */
    private String memberNoGm;
    
    /**
     * 导购微信号，中控客户端时非空
     */
    private String noWxGm;
    
    /**
     * 最后登录登出时间
     */
    private Date lastLoginTime;

	/**
	 * @return the clientFlag
	 */
	public Integer getClientFlag() {
		return clientFlag;
	}

	/**
	 * @param clientFlag the clientFlag to set
	 */
	public void setClientFlag(Integer clientFlag) {
		this.clientFlag = clientFlag;
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
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindOfflineChatInfo [clientFlag=");
		builder.append(clientFlag);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", lastLoginTime=");
		builder.append(lastLoginTime);
		builder.append("]");
		return builder.toString();
	}

}
