package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShopOrderDto implements Serializable { 

    /** CODE*/
    private String code;

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 订单编号*/
    private String orderNo;

    /** 订单类型（SERVER:服务）*/
    private String orderType;

    /** 服务CODE*/
    private String serveCode;

    /** 服务名称*/
    private String serveName;

    /** 下单人编号*/
    private String memberNoGuid;

    /** 下单人姓名*/
    private String memberNameGuid;

    /** 下单人电话*/
    private String mobile;

    /** 支付流水编号*/
    private String serialNum;

    /** 付款方式(WX:微信，ALI:支付宝，BANK:银行转账)*/
    private String payType;

    /** 付款金额（分为单位）*/
    private Long amount;

    /** 付款时间*/
    private Date payTime;

    /** 支付凭证,多张逗号分隔*/
    private String payCert;

    /** 审核状态(WAIT:待审，PASS:通过，UNPASS：拒绝)*/
    private String status;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;

    /** 备注*/
    private String remark;

    /** 备注1*/
    private String remark2;

    /** 备注2*/
    private String remark3;

    /** 付款时间 格式 :yyyy-MM-dd HH:mm:ss*/
    private String payTimeStr;


    /** 付款金额（元为单位）*/
    private String amountStr;
    
    /** CODE  List */
    private List<String> codes;
    
    public String getPayTimeStr() {
		return payTimeStr;
	}

	public void setPayTimeStr(String payTimeStr) {
		this.payTimeStr = payTimeStr;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getServeCode() {
        return serveCode;
    }

    public void setServeCode(String serveCode) {
        this.serveCode = serveCode == null ? null : serveCode.trim();
    }

    public String getServeName() {
        return serveName;
    }

    public void setServeName(String serveName) {
        this.serveName = serveName == null ? null : serveName.trim();
    }

    public String getMemberNoGuid() {
        return memberNoGuid;
    }

    public void setMemberNoGuid(String memberNoGuid) {
        this.memberNoGuid = memberNoGuid == null ? null : memberNoGuid.trim();
    }

    public String getMemberNameGuid() {
        return memberNameGuid;
    }

    public void setMemberNameGuid(String memberNameGuid) {
        this.memberNameGuid = memberNameGuid == null ? null : memberNameGuid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayCert() {
        return payCert;
    }

    public void setPayCert(String payCert) {
        this.payCert = payCert == null ? null : payCert.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
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
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	@Override
	public String toString() {
		return "ShopOrderDto [code=" + code + ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", orderNo=" + orderNo + ", orderType=" + orderType
				+ ", serveCode=" + serveCode + ", serveName=" + serveName + ", memberNoGuid=" + memberNoGuid
				+ ", memberNameGuid=" + memberNameGuid + ", mobile=" + mobile + ", serialNum=" + serialNum
				+ ", payType=" + payType + ", amount=" + amount + ", payTime=" + payTime + ", payCert=" + payCert
				+ ", status=" + status + ", updateId=" + updateId + ", updateDate=" + updateDate + ", createId="
				+ createId + ", createDate=" + createDate + ", remark=" + remark + ", remark2=" + remark2 + ", remark3="
				+ remark3 + ", payTimeStr=" + payTimeStr + ", amountStr=" + amountStr + ", codes=" + codes + "]";
	}
    
}
