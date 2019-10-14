package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;

public class FindImChatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7215892889860764170L; 

	private String code;
    /**
     * 导购编号
     */
	private String memberNoGm;
	
	/**
	 * 客户微信号
	 */
	private String noWx;
	
	  /** 开始时间. */
    private String startTime;
    
    
    /** 结束时间. */
    private String endTime;
    
    /**
     * 查询导购微信
     */
    private String noWxGm;
    
	
	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "FindImChatInfo [code=" + code + "]";
	}
	
}
