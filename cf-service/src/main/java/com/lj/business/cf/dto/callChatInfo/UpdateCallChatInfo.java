package com.lj.business.cf.dto.callChatInfo;

import java.io.Serializable;
import java.util.Date;

public class UpdateCallChatInfo implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 3425892303397989394L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNo;

    /**
     * 导购姓名 .
     */
    private String memberName;

    /**
     * 联系人手机号 .
     */
    private String mobile;

    /**
     * 联系人备注 .
     */
    private String linkmanRemark;

    /**
     * 通话时长(时间戳) .
     */
    private Long callTime;

    /**
     * 通话时间 .
     */
    private Long callDate;

    /**
     * 通话状态      （1  呼出    2  呼入    3  未接      4  拒接） .
     */
    private String status;

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
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;

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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLinkmanRemark() {
		return linkmanRemark;
	}

	public void setLinkmanRemark(String linkmanRemark) {
		this.linkmanRemark = linkmanRemark;
	}

	public Long getCallTime() {
		return callTime;
	}

	public void setCallTime(Long callTime) {
		this.callTime = callTime;
	}

	public Long getCallDate() {
		return callDate;
	}

	public void setCallDate(Long callDate) {
		this.callDate = callDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UpdateCallChatInfo [code=" + code + ", memberNo=" + memberNo
				+ ", memberName=" + memberName + ", mobile=" + mobile
				+ ", linkmanRemark=" + linkmanRemark + ", callTime=" + callTime
				+ ", status=" + status + ", remark=" + remark + ", remark2="
				+ remark2 + ", remark3=" + remark3 + ", remark4=" + remark4
				+ "]";
	}
}
