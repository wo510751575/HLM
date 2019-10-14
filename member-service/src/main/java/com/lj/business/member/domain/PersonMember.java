package com.lj.business.member.domain;

import java.util.Date;

/**
 * 类说明：客户扩展信息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 *
 * @author 段志鹏
 *   
 * CreateDate: 2017年8月15日
 * @Company: 扬恩科技有限公司
 */
public class PersonMember {
    /**
     * CODE .
     */
    private String code;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 装修进度 .
     */
    private String spruceUp;

    /**
     * 所在楼盘 .
     */
    private String houses;

    /**
     * 是否关注 .
     */
    private String keepEye;

    /**
     * 紧急客户 .
     */
    private String urgencyPm;

    /**
     * 所需产品CODE .
     */
    private String bomCode;

    /**
     * 产品名称 .
     */
    private String bomName;

    /**
     * 昵称备注_微信 .
     */
    private String nickNameRemarkWx;
    /**
     * 昵称备注_本地 .
     */
    private String nickNameRemarkLocal;

    /**
     * 最近联系时间 .
     */
    private Date contactDateLast;

    /**
     * 对比品牌 .
     */
    private String brandCompare;

    /**
     * 客户特质 .
     */
    private String clientSpecial;
    
    /**
     * 消费频率(天)
     */
    private Integer consumeFrequency;

    /**
     * 扫码地址 .
     */
    private String scanAddress;

    /**
     * 纬度 .
     */
    private String latitude;

    /**
     * 经度 .
     */
    private String longitude;

    /**
     * 初次介绍 .
     */
    private String firstIntroduce;
    
    /**
     * 预计购买时间 .
     */
    private Date expectPurchaseTime;
    
    /**
     * 到店时间 .
     */
    private Date expTime;
    
    /**
     * 是否微信好友：0不是、1是
     */
    private Integer wxFriends;
    
    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子
     */
    private Integer addType;
    
    /**
     * 版本号
     */
    private Long version;

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
    private String remark2;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 客户分类CODE .
     */
    private String pmTypeCode;
    
    /**
     * 客户分类名称 .
     */
    private String pmTypeName;
    
    /**
     * 终端微信
     */
    private String shopWx;
    
	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public String getPmTypeCode() {
		return pmTypeCode;
	}

	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	public String getPmTypeName() {
		return pmTypeName;
	}

	public void setPmTypeName(String pmTypeName) {
		this.pmTypeName = pmTypeName;
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
     * 客户编号 .
     *
     * @return the member no
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     * @param memberNo the new member no
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 客户名称 .
     *
     * @return the member name
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 客户名称 .
     *
     * @param memberName the new member name
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 导购编号 .
     *
     * @return the member no gm
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     * @param memberNoGm the new member no gm
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     * @return the member name gm
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     * @param memberNameGm the new member name gm
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 商户编号 .
     *
     * @return the merchant no
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     * @param merchantNo the new merchant no
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     * @return the merchant name
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     * @param merchantName the new merchant name
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 装修进度 .
     *
     * @return the spruce up
     */
    public String getSpruceUp() {
        return spruceUp;
    }

    /**
     * 装修进度 .
     *
     * @param spruceUp the new spruce up
     */
    public void setSpruceUp(String spruceUp) {
        this.spruceUp = spruceUp == null ? null : spruceUp.trim();
    }

    /**
     * 所在楼盘 .
     *
     * @return the houses
     */
    public String getHouses() {
        return houses;
    }

    /**
     * 所在楼盘 .
     *
     * @param houses the new houses
     */
    public void setHouses(String houses) {
        this.houses = houses == null ? null : houses.trim();
    }

    /**
     * 是否关注 .
     *
     * @return the keep eye
     */
    public String getKeepEye() {
        return keepEye;
    }

    /**
     * 是否关注 .
     *
     * @param keepEye the new keep eye
     */
    public void setKeepEye(String keepEye) {
        this.keepEye = keepEye == null ? null : keepEye.trim();
    }

    /**
     * 紧急客户 .
     *
     * @return the urgency pm
     */
    public String getUrgencyPm() {
        return urgencyPm;
    }

    /**
     * 紧急客户 .
     *
     * @param urgencyPm the new urgency pm
     */
    public void setUrgencyPm(String urgencyPm) {
        this.urgencyPm = urgencyPm == null ? null : urgencyPm.trim();
    }

    /**
     * 所需产品CODE .
     *
     * @return the bom code
     */
    public String getBomCode() {
        return bomCode;
    }

    /**
     * 所需产品CODE .
     *
     * @param bomCode the new bom code
     */
    public void setBomCode(String bomCode) {
        this.bomCode = bomCode == null ? null : bomCode.trim();
    }

    /**
     * 产品名称 .
     *
     * @return the bom name
     */
    public String getBomName() {
        return bomName;
    }

    /**
     * 产品名称 .
     *
     * @param bomName the new bom name
     */
    public void setBomName(String bomName) {
        this.bomName = bomName == null ? null : bomName.trim();
    }

    /**
     * 昵称备注_微信 .
     *
     * @return the nick name remark wx
     */
    public String getNickNameRemarkWx() {
        return nickNameRemarkWx;
    }

    /**
     * 昵称备注_微信 .
     *
     * @param nickNameRemarkWx the new nick name remark wx
     */
    public void setNickNameRemarkWx(String nickNameRemarkWx) {
        this.nickNameRemarkWx = nickNameRemarkWx == null ? null : nickNameRemarkWx.trim();
    }

    /**
     * 最近联系时间 .
     *
     * @return the contact date last
     */
    public Date getContactDateLast() {
        return contactDateLast;
    }

    /**
     * 最近联系时间 .
     *
     * @param contactDateLast the new contact date last
     */
    public void setContactDateLast(Date contactDateLast) {
        this.contactDateLast = contactDateLast;
    }

    /**
     * 对比品牌 .
     *
     * @return the brand compare
     */
    public String getBrandCompare() {
        return brandCompare;
    }

    /**
     * 对比品牌 .
     *
     * @param brandCompare the new brand compare
     */
    public void setBrandCompare(String brandCompare) {
        this.brandCompare = brandCompare == null ? null : brandCompare.trim();
    }

    /**
     * 客户特质 .
     *
     * @return the client special
     */
    public String getClientSpecial() {
        return clientSpecial;
    }

    /**
     * 客户特质 .
     *
     * @param clientSpecial the new client special
     */
    public void setClientSpecial(String clientSpecial) {
        this.clientSpecial = clientSpecial == null ? null : clientSpecial.trim();
    }

    /**
     * 消费频率(天)
     */
	public Integer getConsumeFrequency() {
		return consumeFrequency;
	}

	/**
     * 消费频率(天)
     */
	public void setConsumeFrequency(Integer consumeFrequency) {
		this.consumeFrequency = consumeFrequency;
	}

	/**
     * 扫码地址 .
     *
     * @return the scan address
     */
    public String getScanAddress() {
        return scanAddress;
    }

    /**
     * 扫码地址 .
     *
     * @param scanAddress the new scan address
     */
    public void setScanAddress(String scanAddress) {
        this.scanAddress = scanAddress == null ? null : scanAddress.trim();
    }

    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子
     */
	public Integer getAddType() {
		return addType;
	}

	/**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子
     */
	public void setAddType(Integer addType) {
		this.addType = addType;
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
     * 初次介绍 .
     *
     * @return the first introduce
     */
    public String getFirstIntroduce() {
        return firstIntroduce;
    }

    /**
     * 初次介绍 .
     *
     * @param firstIntroduce the new first introduce
     */
    public void setFirstIntroduce(String firstIntroduce) {
        this.firstIntroduce = firstIntroduce == null ? null : firstIntroduce.trim();
    }

    public Date getExpectPurchaseTime() {
		return expectPurchaseTime;
	}

	public void setExpectPurchaseTime(Date expectPurchaseTime) {
		this.expectPurchaseTime = expectPurchaseTime;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	/**
     * 是否微信好友：0不是、1是
     */
	public Integer getWxFriends() {
		return wxFriends;
	}

	/**
     * 是否微信好友：0不是、1是
     */
	public void setWxFriends(Integer wxFriends) {
		this.wxFriends = wxFriends;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
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
     * Gets the nick name remark local.
     *
     * @return the nick name remark local
     */
    public String getNickNameRemarkLocal() {
		return nickNameRemarkLocal;
	}

	/**
	 * Sets the nick name remark local.
	 *
	 * @param nickNameRemarkLocal the new nick name remark local
	 */
	public void setNickNameRemarkLocal(String nickNameRemarkLocal) {
		this.nickNameRemarkLocal = nickNameRemarkLocal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonMember [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", spruceUp=");
		builder.append(spruceUp);
		builder.append(", houses=");
		builder.append(houses);
		builder.append(", keepEye=");
		builder.append(keepEye);
		builder.append(", urgencyPm=");
		builder.append(urgencyPm);
		builder.append(", bomCode=");
		builder.append(bomCode);
		builder.append(", bomName=");
		builder.append(bomName);
		builder.append(", nickNameRemarkWx=");
		builder.append(nickNameRemarkWx);
		builder.append(", nickNameRemarkLocal=");
		builder.append(nickNameRemarkLocal);
		builder.append(", contactDateLast=");
		builder.append(contactDateLast);
		builder.append(", brandCompare=");
		builder.append(brandCompare);
		builder.append(", clientSpecial=");
		builder.append(clientSpecial);
		builder.append(", consumeFrequency=");
		builder.append(consumeFrequency);
		builder.append(", scanAddress=");
		builder.append(scanAddress);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", firstIntroduce=");
		builder.append(firstIntroduce);
		builder.append(", expectPurchaseTime=");
		builder.append(expectPurchaseTime);
		builder.append(", expTime=");
		builder.append(expTime);
		builder.append(", wxFriends=");
		builder.append(wxFriends);
		builder.append(", addType=");
		builder.append(addType);
		builder.append(", version=");
		builder.append(version);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", pmTypeCode=");
		builder.append(pmTypeCode);
		builder.append(", pmTypeName=");
		builder.append(pmTypeName);
		builder.append("]");
		return builder.toString();
	}

}