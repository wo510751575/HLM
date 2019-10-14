package com.lj.business.member.domain;

import java.util.Date;

public class GuidCard {
    /**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;
    
    /**
     * 职位 .
     */
    private String position;

    /**
     * 终端地址 .
     */
    private String addrInfo;

    /**
     * 状态              NORMAL正常、             CANCEL注销。             FREEZE冻结              .
     */
    private String status;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 年龄 .
     */
    private Integer age;

    /**
     * 性别:male,female .
     */
    private String gender;

    /**
     * 头像地址 .
     */
    private String headAddress;

    /**
     * 二维码地址 .
     */
    private String qcord;

    /**
     * 人气 .
     */
    private Integer pageView;

    /**
     * 点赞量 .
     */
    private Integer numPraise;

    /**
     * 收藏量 .
     */
    private Integer numCollection;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	/**
     * 终端地址 .
     *
     */
    public String getAddrInfo() {
        return addrInfo;
    }

    /**
     * 终端地址 .
     *
     */
    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo == null ? null : addrInfo.trim();
    }

    /**
     * 状态              NORMAL正常、             CANCEL注销。             FREEZE冻结              .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态              NORMAL正常、             CANCEL注销。             FREEZE冻结              .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 年龄 .
     *
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 年龄 .
     *
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 性别:male,female .
     *
     */
    public String getGender() {
        return gender;
    }

    /**
     * 性别:male,female .
     *
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 头像地址 .
     *
     */
    public String getHeadAddress() {
        return headAddress;
    }

    /**
     * 头像地址 .
     *
     */
    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress == null ? null : headAddress.trim();
    }

    /**
     * 二维码地址 .
     *
     */
    public String getQcord() {
        return qcord;
    }

    /**
     * 二维码地址 .
     *
     */
    public void setQcord(String qcord) {
        this.qcord = qcord == null ? null : qcord.trim();
    }

    /**
     * 人气 .
     *
     */
    public Integer getPageView() {
        return pageView;
    }

    /**
     * 人气 .
     *
     */
    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    /**
     * 点赞量 .
     *
     */
    public Integer getNumPraise() {
        return numPraise;
    }

    /**
     * 点赞量 .
     *
     */
    public void setNumPraise(Integer numPraise) {
        this.numPraise = numPraise;
    }

    /**
     * 收藏量 .
     *
     */
    public Integer getNumCollection() {
        return numCollection;
    }

    /**
     * 收藏量 .
     *
     */
    public void setNumCollection(Integer numCollection) {
        this.numCollection = numCollection;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuidCard [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", position=");
		builder.append(position);
		builder.append(", addrInfo=");
		builder.append(addrInfo);
		builder.append(", status=");
		builder.append(status);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", age=");
		builder.append(age);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", qcord=");
		builder.append(qcord);
		builder.append(", pageView=");
		builder.append(pageView);
		builder.append(", numPraise=");
		builder.append(numPraise);
		builder.append(", numCollection=");
		builder.append(numCollection);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}

}