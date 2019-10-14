package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ServerInfoDto implements Serializable { 

    /** CODE*/
    private String code;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 产品名称*/
    private String serverName;

    /** 价格（分为单位）*/
    private Long price;

    /** 原价（分为单位）*/
    private Long originalPrice;

    /** 状态（use 启用 unuse 禁用）*/
    private String status;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;

    /** 产品说明*/
    private String ctx;

    /** 服务项 */
    List<ServerDetailDto> serverDetails;

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx == null ? null : ctx.trim();
    }

	public List<ServerDetailDto> getServerDetails() {
		return serverDetails;
	}

	public void setServerDetails(List<ServerDetailDto> serverDetails) {
		this.serverDetails = serverDetails;
	}

	@Override
	public String toString() {
		return "ServerInfoDto [code=" + code + ", merchantNo=" + merchantNo + ", merchantName=" + merchantName
				+ ", serverName=" + serverName + ", price=" + price + ", originalPrice=" + originalPrice + ", status="
				+ status + ", updateId=" + updateId + ", updateDate=" + updateDate + ", createId=" + createId
				+ ", createDate=" + createDate + ", ctx=" + ctx + ", serverDetails=" + serverDetails + "]";
	}
    
    
    
}
