package com.lj.business.cf.dto.messagePush;

import java.io.Serializable;
import java.util.Date;

public class FindMessagePushReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -4158579754342619788L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 消息标题 .
     */
    private String msgTitle;

    /**
     * 消息内容 .
     */
    private String msgContent;

    /**
     * 消息类型 .
     */
    private String msgType;

    /**
     * 推送时间 .
     */
    private Date pushDate;

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
     * 接受人编号(用逗号隔开) .
     */
    private String memberNoMsg;

    /**
     * 接收人姓名（逗号隔开） .
     */
    private String memberNamesMsg;

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

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
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

	public String getMemberNoMsg() {
		return memberNoMsg;
	}

	public void setMemberNoMsg(String memberNoMsg) {
		this.memberNoMsg = memberNoMsg;
	}

	public String getMemberNamesMsg() {
		return memberNamesMsg;
	}

	public void setMemberNamesMsg(String memberNamesMsg) {
		this.memberNamesMsg = memberNamesMsg;
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
		return "FindMessagePushReturn [code=" + code + ", msgTitle=" + msgTitle
				+ ", msgContent=" + msgContent + ", msgType=" + msgType
				+ ", pushDate=" + pushDate + ", merchantNo=" + merchantNo
				+ ", merchantName=" + merchantName + ", shopNo=" + shopNo
				+ ", shopName=" + shopName + ", memberNoGm=" + memberNoGm
				+ ", memberNameGm=" + memberNameGm + ", memberNoMsg="
				+ memberNoMsg + ", memberNamesMsg=" + memberNamesMsg
				+ ", updateId=" + updateId + ", updateDate=" + updateDate
				+ ", createId=" + createId + ", createDate=" + createDate + "]";
	}
}
