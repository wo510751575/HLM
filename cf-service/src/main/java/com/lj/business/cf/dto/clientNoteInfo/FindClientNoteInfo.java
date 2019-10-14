package com.lj.business.cf.dto.clientNoteInfo;

import java.io.Serializable;

public class FindClientNoteInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2507320853932229439L; 
	
	private String code;
  
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	private String mobile;
	
	private String startTime;
	
	private String endTime;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	@Override
	public String toString() {
		return "FindClientNoteInfo [code=" + code + ", memberNoGm="
				+ memberNoGm + ", mobile=" + mobile + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	
}
