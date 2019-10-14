package com.ye.business.hx.domain;

import java.util.Date;

public class ServerDetail {
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
	
}