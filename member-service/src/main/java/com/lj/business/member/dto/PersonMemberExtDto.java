package com.lj.business.member.dto;

import java.io.Serializable;

public class PersonMemberExtDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6450462640070716863L;
	
	private String code;
    /**
     * 客户编号 .
     */
    private String memberNo;
    /**
     * 导购编号
     */
    private String memberNoGm;

    /**
     *  .
     */
    private String openId;

    private String mobile;
    private String remark4;
    private String remark3;
    private String remark2;
    private String remark;
    private String remark1;
    /**
     * 真实姓名
     */
    private String realName;
    
    public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public String toString() {
		return "PersonMemberExtDto [memberNo=" + memberNo + ", openId="
				+ openId + "]";
	}
    
}
