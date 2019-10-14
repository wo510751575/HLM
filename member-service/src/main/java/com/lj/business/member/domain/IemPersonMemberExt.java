package com.lj.business.member.domain;

import java.util.Date;

public class IemPersonMemberExt {
    /**
     * CODE .
     */
    private String code;

    /**
     * 会员编号 .
     */
    private String memberNo;

    /**
     * 会员名称 .
     */
    private String memberName;

    /**
     * 昵称 .
     */
    private String nickname;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 头像地址 .
     */
    private String headAddr;

    /**
     * 微信公众号OPENID .
     */
    private String openIdGzhWx;

    /**
     * 微信小程序OPENID .
     */
    private String openIdXcxWx;

    /**
     * 微信号 .
     */
    private String noWx;

    /**
     * 微信号别名 .
     */
    private String noWxAlias;

    /**
     * 来源：0-CRM、1-聚客、2-注册 .
     */
    private Integer source;

    /**
     * 聚客客户编号 .
     */
    private String jkMemberNo;

    /**
     * 聚客所属导购编号 .
     */
    private String jkMemberNoGm;

    /**
     * 敏华会员编号 .
     */
    private String mhMemberNo;

    /**
     * 所属商户 .
     */
    private String merchantNo;

    /**
     * 性别 .
     */
    private String sex;

    /**
     * 注册时间 .
     */
    private Date registerTime;

    /**
     * 邀请人会员编号 .
     */
    private String inviteMemberNo;

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
     * 会员编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 会员编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 会员名称 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 会员名称 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 昵称 .
     *
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称 .
     *
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 手机号 .
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号 .
     *
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 头像地址 .
     *
     */
    public String getHeadAddr() {
        return headAddr;
    }

    /**
     * 头像地址 .
     *
     */
    public void setHeadAddr(String headAddr) {
        this.headAddr = headAddr == null ? null : headAddr.trim();
    }

    /**
     * 微信公众号OPENID .
     *
     */
    public String getOpenIdGzhWx() {
        return openIdGzhWx;
    }

    /**
     * 微信公众号OPENID .
     *
     */
    public void setOpenIdGzhWx(String openIdGzhWx) {
        this.openIdGzhWx = openIdGzhWx == null ? null : openIdGzhWx.trim();
    }

    /**
     * 微信小程序OPENID .
     *
     */
    public String getOpenIdXcxWx() {
        return openIdXcxWx;
    }

    /**
     * 微信小程序OPENID .
     *
     */
    public void setOpenIdXcxWx(String openIdXcxWx) {
        this.openIdXcxWx = openIdXcxWx == null ? null : openIdXcxWx.trim();
    }

    /**
     * 微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 微信号别名 .
     *
     */
    public String getNoWxAlias() {
        return noWxAlias;
    }

    /**
     * 微信号别名 .
     *
     */
    public void setNoWxAlias(String noWxAlias) {
        this.noWxAlias = noWxAlias == null ? null : noWxAlias.trim();
    }

    /**
     * 来源：0-CRM、1-聚客、2-注册 .
     *
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 来源：0-CRM、1-聚客、2-注册 .
     *
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 聚客客户编号 .
     *
     */
    public String getJkMemberNo() {
        return jkMemberNo;
    }

    /**
     * 聚客客户编号 .
     *
     */
    public void setJkMemberNo(String jkMemberNo) {
        this.jkMemberNo = jkMemberNo == null ? null : jkMemberNo.trim();
    }

    /**
     * 聚客所属导购编号 .
     *
     */
    public String getJkMemberNoGm() {
        return jkMemberNoGm;
    }

    /**
     * 聚客所属导购编号 .
     *
     */
    public void setJkMemberNoGm(String jkMemberNoGm) {
        this.jkMemberNoGm = jkMemberNoGm == null ? null : jkMemberNoGm.trim();
    }

    /**
     * 敏华会员编号 .
     *
     */
    public String getMhMemberNo() {
        return mhMemberNo;
    }

    /**
     * 敏华会员编号 .
     *
     */
    public void setMhMemberNo(String mhMemberNo) {
        this.mhMemberNo = mhMemberNo == null ? null : mhMemberNo.trim();
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
     * 性别 .
     *
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别 .
     *
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 注册时间 .
     *
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 注册时间 .
     *
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 邀请人会员编号 .
     *
     */
    public String getInviteMemberNo() {
        return inviteMemberNo;
    }

    /**
     * 邀请人会员编号 .
     *
     */
    public void setInviteMemberNo(String inviteMemberNo) {
        this.inviteMemberNo = inviteMemberNo == null ? null : inviteMemberNo.trim();
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
        builder.append("IemPersonMemberExt [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",nickname=").append(nickname); 
        builder.append(",mobile=").append(mobile); 
        builder.append(",headAddr=").append(headAddr); 
        builder.append(",openIdGzhWx=").append(openIdGzhWx); 
        builder.append(",openIdXcxWx=").append(openIdXcxWx); 
        builder.append(",noWx=").append(noWx); 
        builder.append(",noWxAlias=").append(noWxAlias); 
        builder.append(",source=").append(source); 
        builder.append(",jkMemberNo=").append(jkMemberNo); 
        builder.append(",jkMemberNoGm=").append(jkMemberNoGm); 
        builder.append(",mhMemberNo=").append(mhMemberNo); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",sex=").append(sex); 
        builder.append(",registerTime=").append(registerTime); 
        builder.append(",inviteMemberNo=").append(inviteMemberNo); 
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