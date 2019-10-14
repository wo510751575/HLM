package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShopServerDto implements Serializable { 

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

    /** 服务code*/
    private String serverCode;

    /** 服务名称*/
    private String serverName;

    /** 价格*/
    private Long price;

    /** 原价（分为单位）*/
    private Long originalPrice;

    /** 订单号*/
    private String orderNo;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;

    /**服务项列表**/
    private List<ShopServerDetailDto> serverDetails;
    
    /** 下单人编号*/
    private String memberNoGuid;

    /** 下单人姓名*/
    private String memberNameGuid;

    /** 下单人电话*/
    private String mobile;
    
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

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode == null ? null : serverCode.trim();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

	public List<ShopServerDetailDto> getServerDetails() {
		return serverDetails;
	}

	public void setServerDetails(List<ShopServerDetailDto> serverDetails) {
		this.serverDetails = serverDetails;
	}

	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid;
	}

	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	public void setMemberNameGuid(String memberNameGuid) {
		this.memberNameGuid = memberNameGuid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "ShopServerDto [code=" + code + ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", serverCode=" + serverCode + ", serverName="
				+ serverName + ", price=" + price + ", originalPrice=" + originalPrice + ", orderNo=" + orderNo
				+ ", updateId=" + updateId + ", updateDate=" + updateDate + ", createId=" + createId + ", createDate="
				+ createDate + ", serverDetails=" + serverDetails + ", memberNoGuid=" + memberNoGuid
				+ ", memberNameGuid=" + memberNameGuid + ", mobile=" + mobile + "]";
	}
    
}
