package com.lj.business.cf.domain;

import java.util.Date;

public class CallChatInfo {
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
     * 通话状态      （1  来电    2  已拨    3  未接      4 语音    5 拒接     6 阻塞） .
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

    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 导购编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 联系人手机号 .
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 联系人手机号 .
     *
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 联系人备注 .
     *
     */
    public String getLinkmanRemark() {
        return linkmanRemark;
    }

    /**
     * 联系人备注 .
     *
     */
    public void setLinkmanRemark(String linkmanRemark) {
        this.linkmanRemark = linkmanRemark == null ? null : linkmanRemark.trim();
    }

    /**
     * 通话时长(时间戳) .
     *
     */
    public Long getCallTime() {
        return callTime;
    }

    /**
     * 通话时长(时间戳) .
     *
     */
    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    /**
     * 通话时间 .
     *
     */
    public Long getCallDate() {
        return callDate;
    }

    /**
     * 通话时间 .
     *
     */
    public void setCallDate(Long callDate) {
        this.callDate = callDate;
    }

    /**
     * 通话状态      （1  呼出    2  呼入    3  未接      4  拒接） .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 通话状态      （1  呼出    2  呼入    3  未接      4  拒接） .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CallChatInfo [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",mobile=").append(mobile); 
        builder.append(",linkmanRemark=").append(linkmanRemark); 
        builder.append(",callTime=").append(callTime); 
        builder.append(",callDate=").append(callDate); 
        builder.append(",status=").append(status); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}