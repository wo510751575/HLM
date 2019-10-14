package com.lj.business.cf.dto.memberMessageRelation;

import java.io.Serializable;
import java.util.Date;

public class UpdateMemberMessageRelation implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 8016871914290091810L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 消息编号 .
     */
    private String msgNo;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 用户编号 .
     */
    private String memberNo;

    /**
     * 用户姓名 .
     */
    private String memberName;

    /**
     * 用户微信号 .
     */
    private String memberWxNo;

    /**
     * 推送时间 .
     */
    private Date pushDate;

    /**
     * 推送成功时间 .
     */
    private Date successDate;

    /**
     * 消息状态
             有效：VALID
             无效：INVALID .
     */
    private String msgStatus;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
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

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	public String getMemberWxNo() {
		return memberWxNo;
	}

	public void setMemberWxNo(String memberWxNo) {
		this.memberWxNo = memberWxNo;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	public Date getSuccessDate() {
		return successDate;
	}

	public void setSuccessDate(Date successDate) {
		this.successDate = successDate;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	@Override
	public String toString() {
		return "UpdateMemberMessageRelation [code=" + code + ", msgNo=" + msgNo
				+ ", merchantNo=" + merchantNo + ", merchantName="
				+ merchantName + ", shopNo=" + shopNo + ", shopName="
				+ shopName + ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", memberWxNo=" + memberWxNo + ", pushDate="
				+ pushDate + ", successDate=" + successDate + ", msgStatus="
				+ msgStatus + ", updateId=" + updateId + ", updateDate="
				+ updateDate + ", createId=" + createId + ", createDate="
				+ createDate + "]";
	}
}
