package com.lj.business.cf.dto.clientNoteInfo;

import java.io.Serializable;
import java.util.Date;

public class UpdateClientNoteInfoReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5382158436050786224L; 

	/**
     * CODE .
     */
    private String code;

    /**
     * 客户编号 .
     */
    private String memberNo;
    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 客户手机号码 .
     */
    private String mobile;

    /**
     * YES(发送成功)   NO(发送失败) .
     */
    private String sendType;

    /**
     * 发送时间 .
     */
    private Date sendTime;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark4;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark2;

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

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		return "UpdateClientNoteInfoReturn [code=" + code + ", memberNo="
				+ memberNo + ", memberNoGm=" + memberNoGm + ", mobile="
				+ mobile + ", sendType=" + sendType + ", sendTime=" + sendTime
				+ ", createDate=" + createDate + ", remark=" + remark
				+ ", remark4=" + remark4 + ", remark3=" + remark3
				+ ", remark2=" + remark2 + "]";
	}
    
    
}
