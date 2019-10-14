package com.lj.business.weixin.domain;

import java.util.Date;

public class WxRedpackDetailInfo {
    /**
     *  .
     */
    private String code;

    /**
     * 微信红包ID .
     */
    private String redPackId;

    /**
     * 批量红包CODE .
     */
    private String batchCode;

    /**
     * 门店微信号 .
     */
    private String noWxShop;

    /**
     * 客户导购名称 .
     */
    private String memberNameGm;

    /**
     * 客户导购号 .
     */
    private String memberNoGm;

    /**
     * 门店编号 .
     */
    private String shopNo;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 客户微信号 .
     */
    private String noWx;

    /**
     * 红包文字内容 .
     */
    private String content;

    /**
     * 红包金额 .
     */
    private Long amount;

    /**
     * 红包状态 .
     */
    private String status;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 发送时间 .
     */
    private Date sendDate;

    /**
     * 领取时间 .
     */
    private Date receiveDate;

    /**
     * 错误信息 .
     */
    private String errorMsg;

    /**
     * 红包类型，用于区分任务红包，加好友红包等 .
     */
    private Integer type;

    /**
     * 轮询次数 .
     */
    private Integer pollCount;
    
    /**
     * 活动中根据电话号码判断是否是会员，是会员发送红包
     */
    private String phoneNumber;


    private String merchantNo;
    /**
               * 发红包转账的msgId
     */
    private String msgId;
    
    /**
     * 发送源：0 或null为系统， 1 APP,  2.WEB 
     */
    private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 微信红包ID .
     *
     */
    public String getRedPackId() {
        return redPackId;
    }

    /**
     * 微信红包ID .
     *
     */
    public void setRedPackId(String redPackId) {
        this.redPackId = redPackId == null ? null : redPackId.trim();
    }

    /**
     * 批量红包CODE .
     *
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * 批量红包CODE .
     *
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode == null ? null : batchCode.trim();
    }

    /**
     * 门店微信号 .
     *
     */
    public String getNoWxShop() {
        return noWxShop;
    }

    /**
     * 门店微信号 .
     *
     */
    public void setNoWxShop(String noWxShop) {
        this.noWxShop = noWxShop == null ? null : noWxShop.trim();
    }

    /**
     * 客户导购名称 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 客户导购名称 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 客户导购号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 客户导购号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 门店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 门店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
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
     * 客户名称 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 客户名称 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 客户微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 客户微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 红包文字内容 .
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * 红包文字内容 .
     *
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 红包金额 .
     *
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * 红包金额 .
     *
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * 红包状态 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 红包状态 .
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
     * 发送时间 .
     *
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * 发送时间 .
     *
     */
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * 领取时间 .
     *
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 领取时间 .
     *
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 错误信息 .
     *
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 错误信息 .
     *
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    /**
     * 红包类型，用于区分任务红包，加好友红包等 .
     *
     */
    public Integer getType() {
        return type;
    }

    /**
     * 红包类型，用于区分任务红包，加好友红包等 .
     *
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 轮询次数 .
     *
     */
    public Integer getPollCount() {
        return pollCount;
    }

    /**
     * 轮询次数 .
     *
     */
    public void setPollCount(Integer pollCount) {
        this.pollCount = pollCount;
    }
    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	
    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WxRedpackDetailInfo [code=").append(code);
        builder.append(",redPackId=").append(redPackId); 
        builder.append(",batchCode=").append(batchCode); 
        builder.append(",noWxShop=").append(noWxShop); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",phoneNumber=").append(phoneNumber); 
        
        builder.append(",noWx=").append(noWx); 
        builder.append(",content=").append(content); 
        builder.append(",amount=").append(amount); 
        builder.append(",status=").append(status); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",sendDate=").append(sendDate); 
        builder.append(",receiveDate=").append(receiveDate); 
        builder.append(",errorMsg=").append(errorMsg); 
        builder.append(",type=").append(type); 
        builder.append(",pollCount=").append(pollCount); 
        builder.append("]");
        return builder.toString();
    }
}