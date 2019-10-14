package com.lj.business.cf.domain;

import java.util.Date;

public class ClientNoteInfo {
    /**
     * CODE .
     */
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
     * 客户手机号码 .
     */
    private String mobile;

    /**
     * Y-1接收，0送达,64发送中,128失败
     */
    private String sendType;

    /**
     * 发送时间 (时间戳)
     */
    private String sendTime;

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

    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
		return memberNoGm;
	}
    /**
     * 导购编号 .
     *
     */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 客户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 客户手机号码 .
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 客户手机号码 .
     *
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * YES(发送成功)   NO(发送失败) .
     *
     */
    public String getSendType() {
        return sendType;
    }

    /**
     * YES(发送成功)   NO(发送失败) .
     *
     */
    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    /**
     * 发送时间 .
     *
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间 .
     *
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientNoteInfo [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",mobile=").append(mobile); 
        builder.append(",sendType=").append(sendType); 
        builder.append(",sendTime=").append(sendTime); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark2=").append(remark2); 
        builder.append("]");
        return builder.toString();
    }
}