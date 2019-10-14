package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class FindShopReturn.
 */
public class FindShopReturnAreaCode implements Serializable {

	 /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -2113556965094685845L; 

    /**
     * 区域CODE .
     */
    private String areaCode;
    
    /**
     * 区域名称 .
     */
    private String areaName;

    /**
     * 分店编号 .
     */
    
    
    /**
     * 分店名称
     */
    
    /**
     * 商户编号
     */
    private String memberNoMerchant;

	/**
     * 省code
     */
    private String provinceCode;
    
    /**
     * 地址信息 .
     */
    private String addrInfo;
    
    /**
     * 终端代码
     */
    private String shopNoMerchant;
    /**
     * 城市CODE .
     */
    private String cityCode;
    
    
    public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}

	public String getMemberNoMerchant() {
		return memberNoMerchant;
	}

	public void setMemberNoMerchant(String memberNoMerchant) {
		this.memberNoMerchant = memberNoMerchant;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

    
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public String getAddrInfo() {
		return addrInfo;
	}

	public void setAddrInfo(String addrInfo) {
		this.addrInfo = addrInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindShopReturnAreaCode [areaCode=");
		builder.append(areaCode);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append(", memberNoMerchant=");
		builder.append(memberNoMerchant);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", addrInfo=");
		builder.append(addrInfo);
		builder.append(", shopNoMerchant=");
		builder.append(shopNoMerchant);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append("]");
		return builder.toString();
	}

}
