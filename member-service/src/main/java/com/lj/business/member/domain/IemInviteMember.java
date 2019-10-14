package com.lj.business.member.domain;

import java.util.Date;

public class IemInviteMember {
    /**
     * CODE .
     */
    private String code;

    /**
     * 邀请人会员编号 .
     */
    private String memberNo;

    /**
     * 邀请人会员名称 .
     */
    private String memberName;

    /**
     * 被邀请人会员编号 .
     */
    private String invitedMemberNo;

    /**
     * 被邀请人会员名称 .
     */
    private String invitedMemberName;

    /**
     * 邀请注册时间 .
     */
    private Date registerTime;

    /**
     * 获取积分 .
     */
    private Long integral;

    /**
     * 积分余额 .
     */
    private Long balance;

    /**
     * 所属商户 .
     */
    private String merchantNo;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

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
     * 邀请人会员编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 邀请人会员编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 邀请人会员名称 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 邀请人会员名称 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 被邀请人会员编号 .
     *
     */
    public String getInvitedMemberNo() {
        return invitedMemberNo;
    }

    /**
     * 被邀请人会员编号 .
     *
     */
    public void setInvitedMemberNo(String invitedMemberNo) {
        this.invitedMemberNo = invitedMemberNo == null ? null : invitedMemberNo.trim();
    }

    /**
     * 被邀请人会员名称 .
     *
     */
    public String getInvitedMemberName() {
        return invitedMemberName;
    }

    /**
     * 被邀请人会员名称 .
     *
     */
    public void setInvitedMemberName(String invitedMemberName) {
        this.invitedMemberName = invitedMemberName == null ? null : invitedMemberName.trim();
    }

    /**
     * 邀请注册时间 .
     *
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 邀请注册时间 .
     *
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取积分 .
     *
     */
    public Long getIntegral() {
        return integral;
    }

    /**
     * 获取积分 .
     *
     */
    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    /**
     * 积分余额 .
     *
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * 积分余额 .
     *
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    /**
     * 所属商户 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 所属商户 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
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
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
        builder.append("IemInviteMember [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",invitedMemberNo=").append(invitedMemberNo); 
        builder.append(",invitedMemberName=").append(invitedMemberName); 
        builder.append(",registerTime=").append(registerTime); 
        builder.append(",integral=").append(integral); 
        builder.append(",balance=").append(balance); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}