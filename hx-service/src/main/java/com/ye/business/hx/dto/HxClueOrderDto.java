package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class HxClueOrderDto implements Serializable { 

    /** 线索订单号*/
    private String code;

    /** 线索号(fk:CLUE_CODE)*/
    private String clueCode;

    /** 导购编号*/
    private String memberNo;

    /** 导购名称*/
    private String memberName;

    /** 分店编号*/
    private String shopNo;

    /** 分店名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 门诊服务code(fk:shop_server.code)*/
    private String shopServerCode;

    /** 服务名称*/
    private String serverName;

    /** 服务code（fk:server_icode）*/
    private String serverCode;

    /** 客户类型*/
    private String userType;

    /** 客户单价*/
    private Long userPrice;

    /** 状态（FREEZE冻结中，OK已使用，CANCEL已取消）*/
    private String status;

    /** 创建人*/
    private String createId;

    /** 创建日期*/
    private Date createDate;

    /** 更新人*/
    private String updateId;

    /** 更新日期*/
    private Date updateDate;

    /** 备注*/
    private String remark;

    /** 类型（VISITING 接诊 ORDER 派单）*/
    private String type;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getClueCode() {
        return clueCode;
    }

    public void setClueCode(String clueCode) {
        this.clueCode = clueCode == null ? null : clueCode.trim();
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
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

    public String getShopServerCode() {
        return shopServerCode;
    }

    public void setShopServerCode(String shopServerCode) {
        this.shopServerCode = shopServerCode == null ? null : shopServerCode.trim();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode == null ? null : serverCode.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Long getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(Long userPrice) {
        this.userPrice = userPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "HxClueOrderDto [code=" + code + ", clueCode=" + clueCode + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo=" + merchantNo
				+ ", merchantName=" + merchantName + ", shopServerCode=" + shopServerCode + ", serverName=" + serverName
				+ ", serverCode=" + serverCode + ", userType=" + userType + ", userPrice=" + userPrice + ", status="
				+ status + ", createId=" + createId + ", createDate=" + createDate + ", updateId=" + updateId
				+ ", updateDate=" + updateDate + ", remark=" + remark + ", type=" + type + "]";
	}
	
}
