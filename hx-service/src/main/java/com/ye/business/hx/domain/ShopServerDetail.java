package com.ye.business.hx.domain;

import java.util.Date;

public class ShopServerDetail {
    /** CODE*/
    private String code;

    /** 门诊服务code(fk:shop_server.code)*/
    private String shopServerCode;

    /** 门诊服务code(fk:shop_server.code)*/
    private String serverCode;

    /** 服务名称*/
    private String serverName;

    /** 客户类型*/
    private String userType;

    /** 单价（分为单位）*/
    private Long price;

    /** 总数量*/
    private Integer serverNum;

    /** 已使用数量*/
    private Integer useNum;

    /** 剩余数量*/
    private Integer unuseNum;

    /** 冻结数量*/
    private Integer freezeNum;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;
    
    /** 是否到店*/
    private String isShop;
    /** 描述*/
    private String serverDesc;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getShopServerCode() {
        return shopServerCode;
    }

    public void setShopServerCode(String shopServerCode) {
        this.shopServerCode = shopServerCode == null ? null : shopServerCode.trim();
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getServerNum() {
        return serverNum;
    }

    public void setServerNum(Integer serverNum) {
        this.serverNum = serverNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public Integer getUnuseNum() {
        return unuseNum;
    }

    public void setUnuseNum(Integer unuseNum) {
        this.unuseNum = unuseNum;
    }

    public Integer getFreezeNum() {
        return freezeNum;
    }

    public void setFreezeNum(Integer freezeNum) {
        this.freezeNum = freezeNum;
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
	
	
}