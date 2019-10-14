package com.lj.business.cf.dto.clientNoteInfo;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindClientNoteInfoPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074790417391273479L; 
	/**
	 * 导购编号
	 */
	private String  memberNo;
	/**
	 * 手机号码
	 */
     private String mobile;
     /**
      * 状态
      */
	private String sendType;
	
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	
	
}
