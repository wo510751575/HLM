package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class WxRedpackDetailInfoDto extends PageParamEntity implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -7085072876105065648L;

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
     * 用户手机号
     */
    private String phoneNumber;

	/**
     * 红包文字内容 .
     */
    private String content;

    /**
     * 红包金额 .
     */
    private Long amount;
    
    private String strAmount;



	/**
     * 红包状态 .
     */
    private String status;
    
    
    private String errorMsg;

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
     * 红包类型，用于区分任务红包，加好友红包等
     */
    private Integer type;
    
    private String merchantNo;
    
    /**
     * 发红包或转账的msgId
     * @return
     */
    private String msgId;
    
    /**
     * 发送源 ：0 或null为系统， 1 APP,  2.WEB 
     */
    private String source="0";

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

	public String getStrAmount() {
		return strAmount;
	}

	public void setStrAmount(String strAmount) {
		this.strAmount = strAmount;
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
        this.code = code;
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
        this.redPackId = redPackId;
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
        this.batchCode = batchCode;
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
        this.noWxShop = noWxShop;
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
        this.memberNameGm = memberNameGm;
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
        this.memberNoGm = memberNoGm;
    }

    /**
     * 门店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }
    public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
    /**
     * 门店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
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
        this.memberNo = memberNo;
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
        this.memberName = memberName;
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
        this.noWx = noWx;
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
        this.content = content;
    }

    /**
     * 红包金额 .
     *
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * 红包金额 .（分）
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
        this.status = status;
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
    
    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
    public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WxRedpackDetailInfoDto [code=");
        builder.append(code);
        builder.append(", redPackId=");
        builder.append(redPackId);
        builder.append(", batchCode=");
        builder.append(batchCode);
        builder.append(", noWxShop=");
        builder.append(noWxShop);
        builder.append(", memberNameGm=");
        builder.append(memberNameGm);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", shopNo=");
        builder.append(shopNo);
        builder.append(", memberNo=");
        builder.append(memberNo);
        builder.append(", memberName=");
        builder.append(memberName);
        builder.append(", noWx=");
        builder.append(noWx);
        builder.append(", content=");
        builder.append(content);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(", status=");
        builder.append(status);
        builder.append(", errorMsg=");
        builder.append(errorMsg);
        builder.append(", createDate=");
        builder.append(createDate);
        builder.append(", sendDate=");
        builder.append(sendDate);
        builder.append(", receiveDate=");
        builder.append(receiveDate);
        builder.append(", type=");
        builder.append(type);
        builder.append(", phoneNumber=");
        builder.append(phoneNumber);
        builder.append("]");
        return builder.toString();
    }

}
