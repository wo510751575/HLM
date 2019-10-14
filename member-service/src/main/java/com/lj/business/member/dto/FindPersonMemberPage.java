package com.lj.business.member.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindPersonMemberPage.
 */
public class FindPersonMemberPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7146345596624141287L; 

	/** 会员编号. */
	private String memberNo;		
	
	/** 手机串号. */
	private int imei;		
	
	/** 会员名称. */
	private String memberName;		
	
	/** 手机号. */
	private String mobile;
	
	/** 导购编号. */
	private String memberNoGm;		
	
	/** 客户来源. */
	private String memberSrc;	
	
	/** 客户来源为空. */
	private String memberSrcNull;
	
	/** 开始时间. */
	private Date startTime;			
	
	/** 结束时间. */
	private Date endTime;			
	/** 商户编号. */
	private String merchantNo;		
	
	/** 客户分类CODE. */
	private String pmTypeCode;		
	
	/** 产品code. */
	private String bomCode;			
	
	/** 客户分类类型. */
    private String pmTypeType;
    
    /** 客户分类维度. */
    private String pmTypeDim;
    
    /** 导购姓名. */
    private String memberNameGm;
	/**
	 * 地区id
	 */
	private String areaId;
	/**
	 * 地区名
	 */
	private String areaName;
	/**
	 * 所在地区
	 */
	private String area;
	/**
	 * 所在省
	 */
	private String province;
	/**
	 * 所在市
	 */
	private String city;
	/**
     * 所在区
     */
    private String region;
	/**
	 * 微信号
	 */
	private String noWx;
	
	/**
	 * 关键字
	 */
	private String keyWord;
	
	/**
	 * 类型编号
	 */
	private String typeCode;
	
	/**
	 * 基础表的最后更新时间
	 */
	private Date updateDate;
	
	 /** 旺旺账号*/
    private String noWw;
    
    /** 终端微信*/
    private String shopWx;
    
    /** 终端微信集合*/
    private String[] shopWxs;
	
	public String[] getShopWxs() {
		return shopWxs;
	}

	public void setShopWxs(String[] shopWxs) {
		this.shopWxs = shopWxs;
	}

	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}
	/**
	 * @return the memberSrcNull
	 */
	public String getMemberSrcNull() {
		return memberSrcNull;
	}

	/**
	 * @param memberSrcNull the memberSrcNull to set
	 */
	public void setMemberSrcNull(String memberSrcNull) {
		this.memberSrcNull = memberSrcNull;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}


	/**
	 * Gets the 导购姓名.
	 *
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the 导购姓名.
	 *
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the 客户分类类型.
	 *
	 * @return the pmTypeType
	 */
	public String getPmTypeType() {
		return pmTypeType;
	}

	/**
	 * Sets the 客户分类类型.
	 *
	 * @param pmTypeType the pmTypeType to set
	 */
	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

	/**
	 * Gets the 客户分类维度.
	 *
	 * @return the pmTypeDim
	 */
	public String getPmTypeDim() {
		return pmTypeDim;
	}

	/**
	 * Sets the 客户分类维度.
	 *
	 * @param pmTypeDim the pmTypeDim to set
	 */
	public void setPmTypeDim(String pmTypeDim) {
		this.pmTypeDim = pmTypeDim;
	}

	/**
	 * Gets the 手机串号.
	 *
	 * @return the imei
	 */
	public int getImei() {
		return imei;
	}

	/**
	 * Sets the 手机串号.
	 *
	 * @param imei the imei to set
	 */
	public void setImei(int imei) {
		this.imei = imei;
	}

	/**
	 * Gets the 客户来源.
	 *
	 * @return the memberSrc
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	/**
	 * Sets the 客户来源.
	 *
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
	}

	/**
	 * Gets the 客户分类CODE.
	 *
	 * @return the pmTypeCode
	 */
	public String getPmTypeCode() {
		return pmTypeCode;
	}

	/**
	 * Sets the 客户分类CODE.
	 *
	 * @param pmTypeCode the pmTypeCode to set
	 */
	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	/**
	 * Gets the 产品code.
	 *
	 * @return the bomCode
	 */
	public String getBomCode() {
		return bomCode;
	}

	/**
	 * Sets the 产品code.
	 *
	 * @param bomCode the bomCode to set
	 */
	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
	}


	/**
	 * Gets the 会员编号.
	 *
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Gets the 会员名称.
	 *
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the 会员名称.
	 *
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the 手机号.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the 手机号.
	 *
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the 导购编号.
	 *
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号.
	 *
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 开始时间.
	 *
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Sets the 开始时间.
	 *
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the 结束时间.
	 *
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the 结束时间.
	 *
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the 商户编号.
	 *
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号.
	 *
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Sets the 会员编号.
	 *
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	
	public String getNoWw() {
		return noWw;
	}

	public void setNoWw(String noWw) {
		this.noWw = noWw;
	}

	/**
	 * 基础表最后更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	
	/**
	 * 基础表最后更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPersonMemberPage [memberNo=");
		builder.append(memberNo);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberSrc=");
		builder.append(memberSrc);
		builder.append(", memberSrcNull=");
		builder.append(memberSrcNull);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", pmTypeCode=");
		builder.append(pmTypeCode);
		builder.append(", bomCode=");
		builder.append(bomCode);
		builder.append(", pmTypeType=");
		builder.append(pmTypeType);
		builder.append(", pmTypeDim=");
		builder.append(pmTypeDim);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", areaId=");
		builder.append(areaId);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append(", area=");
		builder.append(area);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", region=");
		builder.append(region);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", keyWord=");
		builder.append(keyWord);
		builder.append(", typeCode=");
		builder.append(typeCode);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", noWw=");
		builder.append(noWw);
		builder.append("]");
		return builder.toString();
	}

}
