package com.lj.business.member.dto;

import java.util.ArrayList;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindGmDistantPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7554543169594917052L;
	
	/**
	 * 商户号(必填)
	 */
	private String merchantNo;
	
	/**
	 * 终端编号
	 */
	
	
	/**
	 * 产品code
	 */
	private String productCode;
	
	
	/**
	 * 经度(必填)
	 */
	private String longitude;
	
	/**
	 * 伟度(必填)
	 */
	private String latitude;
	
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 城市code
	 */
	private String cityCode;
	
	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 终端list
	 */
	private List<String> shopList = new ArrayList<String>();

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public List<String> getShopList() {
		return shopList;
	}

	public void setShopList(List<String> shopList) {
		this.shopList = shopList;
	}
	
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGmDistantPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", shopList=");
		builder.append(shopList);
		builder.append("]");
		return builder.toString();
	}

}
