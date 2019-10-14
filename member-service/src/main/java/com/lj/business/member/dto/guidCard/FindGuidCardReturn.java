package com.lj.business.member.dto.guidCard;

import java.io.Serializable;
import java.util.Date;

public class FindGuidCardReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 7848027382590247048L;

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
     * 状态 
             NORMAL正常、
             CANCEL注销。
             FREEZE冻结
              .
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
     * 口号 .
     */
    private String slogan;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;
    
    /**
     * 是否收藏 .   是：1,  否：0 .
     */
    private String isSave;
    
    /**
     * 是否点赞 .   是：1,  否：0 .
     */
    private String isPraise;
    
    /**
     *导购名片地址 
     */
    private String gmCardUrl;
    
    /**
     * 导购头像地址
     */
    private String guidDHUrl;
    
    

	public String getGmCardUrl() {
		return gmCardUrl;
	}

	public void setGmCardUrl(String gmCardUrl) {
		this.gmCardUrl = gmCardUrl;
	}

	public String getGuidDHUrl() {
		return guidDHUrl;
	}

	public void setGuidDHUrl(String guidDHUrl) {
		this.guidDHUrl = guidDHUrl;
	}

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

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}


	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddrInfo() {
		return addrInfo;
	}

	public void setAddrInfo(String addrInfo) {
		this.addrInfo = addrInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getQcord() {
		return qcord;
	}

	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public Integer getNumPraise() {
		return numPraise;
	}

	public void setNumPraise(Integer numPraise) {
		this.numPraise = numPraise;
	}

	public Integer getNumCollection() {
		return numCollection;
	}

	public void setNumCollection(Integer numCollection) {
		this.numCollection = numCollection;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsSave() {
		return isSave;
	}

	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}

	public String getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(String isPraise) {
		this.isPraise = isPraise;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGuidCardReturn [code=");
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
		builder.append(", slogan=");
		builder.append(slogan);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", isSave=");
		builder.append(isSave);
		builder.append(", isPraise=");
		builder.append(isPraise);
		builder.append(", gmCardUrl=");
		builder.append(gmCardUrl);
		builder.append(", guidDHUrl=");
		builder.append(guidDHUrl);
		builder.append("]");
		return builder.toString();
	}

}
