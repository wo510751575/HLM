package com.lj.business.cf.domain;

import java.util.Date;

public class MessagePush {
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
     * 消息标题 .
     *
     */
    public String getMsgTitle() {
        return msgTitle;
    }

    /**
     * 消息标题 .
     *
     */
    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    /**
     * 消息内容 .
     *
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 消息内容 .
     *
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
     * 推送时间 .
     *
     */
    public Date getPushDate() {
        return pushDate;
    }

    /**
     * 推送时间 .
     *
     */
    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
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

    /**
     * 分店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
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
     * 接受人编号(用逗号隔开) .
     *
     */
    public String getMemberNoMsg() {
        return memberNoMsg;
    }

    /**
     * 接受人编号(用逗号隔开) .
     *
     */
    public void setMemberNoMsg(String memberNoMsg) {
        this.memberNoMsg = memberNoMsg == null ? null : memberNoMsg.trim();
    }

    /**
     * 接收人姓名（逗号隔开） .
     *
     */
    public String getMemberNamesMsg() {
        return memberNamesMsg;
    }

    /**
     * 接收人姓名（逗号隔开） .
     *
     */
    public void setMemberNamesMsg(String memberNamesMsg) {
        this.memberNamesMsg = memberNamesMsg == null ? null : memberNamesMsg.trim();
    }

    /**
     * 更新人 .
     *
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
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
		return "MessagePush [code=" + code + ", msgTitle=" + msgTitle
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