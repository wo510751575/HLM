package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class AddShop.
 */
public class AddShop implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8254880895158403047L;

	/**
     * CODE .
     */
    private String code;

    
    /** 终端类型. */
   private String shopType;
   
   /** 分公司编号. */
   private String companyNo;
   
   /** 分公司名称. */
   private String companyName;
   
    /**
     * 商户编号 .
     */
    private String memberNoMerchant;
    
    /**
     * 商户名称 .
     */
    private String memberNameMerchant;

    /**
     * 会员状态 
             NORMAL正常、
             CANCEL注销。
              .
     */
    private String status;

    /**
     * 联系电话 .
     */
    private String telephone;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 邮箱 .
     */
    private String email;

    /**
     * 住址 .
     */
    private String address;

    /**
     * 区域CODE .
     */
    private String areaCode;
    
    /**
     * 区域名称 .
     */
    private String areaName;

    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 市CODE .
     */
    private String cityCode;

    /**
     * 市区CODE .
     */
    private String cityAreaCode;

    /**
     * 地址信息 .
     */
    private String addrInfo;

    /**
     * LOGO图片 .
     */
    private String logoAddr;

    /**
     * 经度 .
     */
    private String longitude;

    /**
     * 纬度 .
     */
    private String latitude;

    /**
     * 经营范围 .
     */
    private String bizScope;

    /**
     * 终端二维码地址 .
     */
    private String qcord;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新时间 .
     */
    private Date updateDate;
    
    /** 开始时间. */
    private Date openDate;

    
    /**
     * 备注 .
     */
    private String remark4;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;
    
    /** 终端代码. */
    private String shopNoMerchant;
    
    /** 当前页数. */
    private Integer pageNo;
    
    /** 销售分类. */
    private String mecShopType;
    /** 销售渠道. */
    private String mecShopChannel;
    /** 经营品类. */
    private String mecShopCategory;
    /** 销售组织. */
    private String mecShopOrg;
    
	 /** 关店日期_SAP. */
    private String closeDateSap;
    
    /** 区域编码_SAP. */
    private String bubasAreaSap;
    
    /** 区域名称_SAP. */
    private String areaNameSap;
    
    
    
	/**
	 * Gets the 当前页数.
	 *
	 * @return the 当前页数
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * Sets the 当前页数.
	 *
	 * @param pageNo the new 当前页数
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
     * Gets the 经营品类.
     *
     * @return the 经营品类
     */
    public String getMecShopCategory() {
		return mecShopCategory;
	}

	/**
	 * Sets the 经营品类.
	 *
	 * @param mecShopCategory the new 经营品类
	 */
	public void setMecShopCategory(String mecShopCategory) {
		this.mecShopCategory = mecShopCategory;
	}

	/**
	 * Gets the 销售组织.
	 *
	 * @return the 销售组织
	 */
	public String getMecShopOrg() {
		return mecShopOrg;
	}

	/**
	 * Sets the 销售组织.
	 *
	 * @param mecShopOrg the new 销售组织
	 */
	public void setMecShopOrg(String mecShopOrg) {
		this.mecShopOrg = mecShopOrg;
	}

	/**
	 * Gets the 销售分类.
	 *
	 * @return the 销售分类
	 */
	public String getMecShopType() {
		return mecShopType;
	}

	/**
	 * Sets the 销售分类.
	 *
	 * @param mecShopType the new 销售分类
	 */
	public void setMecShopType(String mecShopType) {
		this.mecShopType = mecShopType;
	}

	/**
	 * Gets the 销售渠道.
	 *
	 * @return the 销售渠道
	 */
	public String getMecShopChannel() {
		return mecShopChannel;
	}

	/**
	 * Sets the 销售渠道.
	 *
	 * @param mecShopChannel the new 销售渠道
	 */
	public void setMecShopChannel(String mecShopChannel) {
		this.mecShopChannel = mecShopChannel;
	}
    
    
    /**
     * Gets the 终端类型.
     *
     * @return the 终端类型
     */
    public String getShopType() {
		return shopType;
	}

	/**
	 * Sets the 终端类型.
	 *
	 * @param shopType the new 终端类型
	 */
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	/**
	 * Gets the 终端代码.
	 *
	 * @return the shopNoMerchant
	 */
	public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	/**
	 * Sets the 终端代码.
	 *
	 * @param shopNoMerchant the shopNoMerchant to set
	 */
	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}
    
    /**
     * Gets the 开始时间.
     *
     * @return the openDate
     */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * Sets the 开始时间.
	 *
	 * @param openDate the openDate to set
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	/**
     * CODE .
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     * @return the member no merchant
     */
    public String getMemberNoMerchant() {
        return memberNoMerchant;
    }

    /**
     * 商户编号 .
     *
     * @param memberNoMerchant the new member no merchant
     */
    public void setMemberNoMerchant(String memberNoMerchant) {
        this.memberNoMerchant = memberNoMerchant == null ? null : memberNoMerchant.trim();
    }

    /**
     * 商户名称 .
     *
     * @return the member name merchant
     */
    public String getMemberNameMerchant() {
        return memberNameMerchant;
    }

    /**
     * 商户名称 .
     *
     * @param memberNameMerchant the new member name merchant
     */
    public void setMemberNameMerchant(String memberNameMerchant) {
        this.memberNameMerchant = memberNameMerchant == null ? null : memberNameMerchant.trim();
    }

    /**
     * 会员状态 
     *              NORMAL正常、
     *              CANCEL注销。
     *               .
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 会员状态 
     *              NORMAL正常、
     *              CANCEL注销。
     *               .
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 联系电话 .
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 联系电话 .
     *
     * @param telephone the new telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 手机号 .
     *
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号 .
     *
     * @param mobile the new mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 邮箱 .
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱 .
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 住址 .
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住址 .
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 区域CODE .
     *
     * @return the area code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 区域CODE .
     *
     * @param areaCode the new area code
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 省CODE .
     *
     * @return the province code
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 省CODE .
     *
     * @param provinceCode the new province code
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 市CODE .
     *
     * @return the city code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 市CODE .
     *
     * @param cityCode the new city code
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 市区CODE .
     *
     * @return the city area code
     */
    public String getCityAreaCode() {
        return cityAreaCode;
    }

    /**
     * 市区CODE .
     *
     * @param cityAreaCode the new city area code
     */
    public void setCityAreaCode(String cityAreaCode) {
        this.cityAreaCode = cityAreaCode == null ? null : cityAreaCode.trim();
    }

    /**
     * 地址信息 .
     *
     * @return the addr info
     */
    public String getAddrInfo() {
        return addrInfo;
    }

    /**
     * 地址信息 .
     *
     * @param addrInfo the new addr info
     */
    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo == null ? null : addrInfo.trim();
    }

    /**
     * LOGO图片 .
     *
     * @return the logo addr
     */
    public String getLogoAddr() {
        return logoAddr;
    }

    /**
     * LOGO图片 .
     *
     * @param logoAddr the new logo addr
     */
    public void setLogoAddr(String logoAddr) {
        this.logoAddr = logoAddr == null ? null : logoAddr.trim();
    }

    /**
     * 经度 .
     *
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 经度 .
     *
     * @param longitude the new longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 纬度 .
     *
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 纬度 .
     *
     * @param latitude the new latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 经营范围 .
     *
     * @return the biz scope
     */
    public String getBizScope() {
        return bizScope;
    }

    /**
     * 经营范围 .
     *
     * @param bizScope the new biz scope
     */
    public void setBizScope(String bizScope) {
        this.bizScope = bizScope == null ? null : bizScope.trim();
    }

    /**
     * 终端二维码地址 .
     *
     * @return the qcord
     */
    public String getQcord() {
        return qcord;
    }

    /**
     * 终端二维码地址 .
     *
     * @param qcord the new qcord
     */
    public void setQcord(String qcord) {
        this.qcord = qcord == null ? null : qcord.trim();
    }

    /**
     * 创建人 .
     *
     * @return the creates the id
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     * @param createId the new creates the id
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     * @return the creates the date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     * @param createDate the new creates the date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新人 .
     *
     * @return the update id
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     * @param updateId the new update id
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 更新时间 .
     *
     * @return the update date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     * @param updateDate the new update date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 备注 .
     *
     * @return the remark 4
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     * @param remark4 the new remark 4
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 备注 .
     *
     * @return the remark 3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     * @param remark3 the new remark 3
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     * @param remark the new remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     * @return the remark 2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     * @param remark2 the new remark 2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
    
	/**
	 * Gets the 区域名称 .
	 *
	 * @return the 区域名称 
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the 区域名称 .
	 *
	 * @param areaName the new 区域名称 
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

    /**
     * Gets the 分公司编号.
     *
     * @return the 分公司编号
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * Sets the 分公司编号.
     *
     * @param companyNo the new 分公司编号
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * Gets the 分公司名称.
     *
     * @return the 分公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the 分公司名称.
     *
     * @param companyName the new 分公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddShop [code=").append(code)
				.append(", shopType=").append(shopType).append(", companyNo=")
				.append(companyNo).append(", companyName=").append(companyName)
				.append(", memberNoMerchant=").append(memberNoMerchant)
				.append(", memberNameMerchant=").append(memberNameMerchant)
				.append(", status=").append(status).append(", telephone=")
				.append(telephone).append(", mobile=").append(mobile)
				.append(", email=").append(email).append(", address=")
				.append(address).append(", areaCode=").append(areaCode)
				.append(", areaName=").append(areaName)
				.append(", provinceCode=").append(provinceCode)
				.append(", cityCode=").append(cityCode)
				.append(", cityAreaCode=").append(cityAreaCode)
				.append(", addrInfo=").append(addrInfo).append(", logoAddr=")
				.append(logoAddr).append(", longitude=").append(longitude)
				.append(", latitude=").append(latitude).append(", bizScope=")
				.append(bizScope).append(", qcord=").append(qcord)
				.append(", createId=").append(createId).append(", createDate=")
				.append(createDate).append(", updateId=").append(updateId)
				.append(", updateDate=").append(updateDate)
				.append(", openDate=").append(openDate).append(", remark4=")
				.append(remark4).append(", remark3=").append(remark3)
				.append(", remark=").append(remark).append(", remark2=")
				.append(remark2).append(", shopNoMerchant=")
				.append(shopNoMerchant).append(", pageNo=").append(pageNo)
				.append(", mecShopType=").append(mecShopType)
				.append(", mecShopChannel=").append(mecShopChannel)
				.append(", mecShopCategory=").append(mecShopCategory)
				.append(", mecShopOrg=").append(mecShopOrg)
				.append(", closeDateSap=").append(closeDateSap)
				.append(", bubasAreaSap=").append(bubasAreaSap)
				.append(", areaNameSap=").append(areaNameSap).append("]");
		return builder.toString();
	}

	/**
	 * Gets the 关店日期_SAP.
	 *
	 * @return the 关店日期_SAP
	 */
	public String getCloseDateSap() {
		return closeDateSap;
	}

	/**
	 * Sets the 关店日期_SAP.
	 *
	 * @param closeDateSap the new 关店日期_SAP
	 */
	public void setCloseDateSap(String closeDateSap) {
		this.closeDateSap = closeDateSap;
	}

	/**
	 * Gets the 区域编码_SAP.
	 *
	 * @return the 区域编码_SAP
	 */
	public String getBubasAreaSap() {
		return bubasAreaSap;
	}

	/**
	 * Sets the 区域编码_SAP.
	 *
	 * @param bubasAreaSap the new 区域编码_SAP
	 */
	public void setBubasAreaSap(String bubasAreaSap) {
		this.bubasAreaSap = bubasAreaSap;
	}

	/**
	 * Gets the 区域名称_SAP.
	 *
	 * @return the 区域名称_SAP
	 */
	public String getAreaNameSap() {
		return areaNameSap;
	}

	/**
	 * Sets the 区域名称_SAP.
	 *
	 * @param areaNameSap the new 区域名称_SAP
	 */
	public void setAreaNameSap(String areaNameSap) {
		this.areaNameSap = areaNameSap;
	}

}