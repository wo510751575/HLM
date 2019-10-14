package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

// TODO: Auto-generated Javadoc
/**
 * 类说明：终端数据导入
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 * CreateDate: 2017年7月22日
 */
public class ShopImportDto implements Serializable{

	 /** Generate cron. */
	private static final long serialVersionUID = -1191419048593399030L;
	
	/** 终端代码. */
	@ExcelField(title="终端代码", align=2, sort=10)
	private String shopNoMerchant;
	
	/** 终端名称. */
	@ExcelField(title="终端类型", align=2, sort=25)
	private String shopType;


	/** 终端状态. */
	@ExcelField(title="终端状态(已经开业、暂停营业、尚在装修)", align=2, sort=30)
	private String status;
	
	/** 开店时间. */
	@ExcelField(title="开店时间", align=2, sort=35)
	private String openDate;
	
	/** 联系电话. */
	@ExcelField(title="联系电话", align=2, sort=40)
	private String telephone;
	
	
	/** 经营范围. */
	@ExcelField(title="经营范围", align=2, sort=70)
	private String bizScope;
	
	/** 地区. */
	@ExcelField(title="所属区域(华东地区、华南地区、华中地区、华北地区、西北地区、西南地区、东北地区、台港澳地区)", align=2, sort=90)
   private String areaCode;
    
    /** 地址信息. */
	@ExcelField(title="地址信息", align=2, sort=100)
    private String addrInfo;
    
   /** 经度. */
	@ExcelField(title="经度", align=2, sort=120)
    private String longitude;
    
    /** 纬度. */
	@ExcelField(title="纬度", align=2, sort=130)
    private String latitude;
	
	/**
	 * Gets the shop type.
	 *
	 * @return the shop type
	 */
	public String getShopType() {
		return shopType;
	}

	/**
	 * Sets the shop type.
	 *
	 * @param shopType the shop type
	 */
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * Sets the telephone.
	 *
	 * @param telephone the telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	/**
	 * Gets the biz scope.
	 *
	 * @return the biz scope
	 */
	public String getBizScope() {
		return bizScope;
	}
	
	/**
	 * Sets the biz scope.
	 *
	 * @param bizScope the biz scope
	 */
	public void setBizScope(String bizScope) {
		this.bizScope = bizScope;
	}
	
	
	/**
	 * Gets the area code.
	 *
	 * @return the area code
	 */
	public String getAreaCode() {
		return areaCode;
	}
	
	/**
	 * Sets the area code.
	 *
	 * @param areaCode the area code
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	/**
	 * Gets the addr info.
	 *
	 * @return the addr info
	 */
	public String getAddrInfo() {
		return addrInfo;
	}
	
	/**
	 * Sets the addr info.
	 *
	 * @param addrInfo the addr info
	 */
	public void setAddrInfo(String addrInfo) {
		this.addrInfo = addrInfo;
	}
	
	
	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * Sets the longitude.
	 *
	 * @param longitude the longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * Sets the latitude.
	 *
	 * @param latitude the latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the shop no merchant.
	 *
	 * @return the shopNoMerchant
	 */
	public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	/**
	 * Sets the shop no merchant.
	 *
	 * @param shopNoMerchant the shopNoMerchant to set
	 */
	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}

	/**
	 * Gets the open date.
	 *
	 * @return the openDate
	 */
	public String getOpenDate() {
		return openDate;
	}

	/**
	 * Sets the open date.
	 *
	 * @param openDate the openDate to set
	 */
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
    
}
