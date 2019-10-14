package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ServerDetailDto implements Serializable { 

    /** CODE*/
    private String code;

    /** 服务CODE（fk:server_info.code）*/
    private String serverCode;

    /** 客户类型*/
    private String userType;

    /** 数量*/
    private Integer serverNum;

    /** 单价（分为单位）*/
    private Long price;

    /** 是否到店  */
    private String isShop;
    /** 描述*/
    private String serverDesc;

    /** 创建时间*/
    private Date createDate;
    
    /** 服务CODE（fk:server_info.code） List */
    private List<String> serverCodes;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Integer getServerNum() {
        return serverNum;
    }

    public void setServerNum(Integer serverNum) {
        this.serverNum = serverNum;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

	public String getIsShop() {
		return isShop;
	}

	public void setIsShop(String isShop) {
		this.isShop = isShop;
	}

	public String getServerDesc() {
		return serverDesc;
	}

	public void setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<String> getServerCodes() {
		return serverCodes;
	}

	public void setServerCodes(List<String> serverCodes) {
		this.serverCodes = serverCodes;
	}

	@Override
	public String toString() {
		return "ServerDetailDto [code=" + code + ", serverCode=" + serverCode + ", userType=" + userType
				+ ", serverNum=" + serverNum + ", price=" + price + ", isShop=" + isShop + ", serverDesc=" + serverDesc
				+ ", createDate=" + createDate + ", serverCodes=" + serverCodes + "]";
	}
	
}
