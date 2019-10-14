package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 类说明：终端数据导出
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 * CreateDate: 2017年7月19日
 */
public class ShopExportDto implements Serializable{

	 /** Generate cron. */
	private static final long serialVersionUID = 1651893109038300480L;
	
	/** 终端代码. */
	@ExcelField(title="终端代码", align=2, sort=1)
	private String shopNoMerchant;
		
	/** 
	 * 店长名称. 
	 * 
	 */
	@ExcelField(title="终端类型", align=2, sort=3)
	private String shopType;
	 /** 
	  * 店长名称. 
	  * 
	  */
	@ExcelField(title="店长", align=2, sort=4)
    private String memberNameShop;
    
    /**
     *  导购数量.
     */
	@ExcelField(title="导购数量", align=3, sort=5)
    private String guidNum;
    
    /** 客户数量. */
	@ExcelField(title="客户数量", align=3, sort=6)
    private String personNum;
    
    /** 店员数量. */
	@ExcelField(title="店员数量", align=3, sort=7)
    private String clerkNum;
	
	 /** 成单金额. */
	@ExcelField(title="成单金额", align=3, sort=8)
    private String orderAmount;
		
    /**
     * 经营范围 .
     */
	@ExcelField(title="经营范围", align=2, sort=9)
    private String bizScope;
    /**
     * 联系电话 .
     */
	@ExcelField(title="联系电话", align=2, sort=10)
    private String telephone;
    /** 
     * 开店时间.
     * */
	@ExcelField(title="开店时间", align=2, sort=11)
    private Date openDate;
     
    /** 终端状态. */
	@ExcelField(title="终端状态", align=2, sort=12)
    private String status;

    /**
     * 终端地址.
     */
	@ExcelField(title="终端地址", align=2, sort=13)
    private String addrInfo;
    
	 /**
	   *所属区域
	   */
	@ExcelField(title="所属区域", align=2, sort=14, dictType="erp_dict_1")
	private String areaCode;

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	/**
	 * Gets the member name shop.
	 *
	 * @return the member name shop
	 */
	public String getMemberNameShop() {
		return memberNameShop;
	}

	/**
	 * Sets the member name shop.
	 *
	 * @param memberNameShop the member name shop
	 */
	public void setMemberNameShop(String memberNameShop) {
		this.memberNameShop = memberNameShop;
	}

	/**
	 * Gets the guid num.
	 *
	 * @return the guid num
	 */
	public String getGuidNum() {
		return guidNum;
	}

	/**
	 * Sets the guid num.
	 *
	 * @param guidNum the guid num
	 */
	public void setGuidNum(String guidNum) {
		this.guidNum = guidNum;
	}

	/**
	 * Gets the person num.
	 *
	 * @return the person num
	 */
	public String getPersonNum() {
		return personNum;
	}

	/**
	 * Sets the person num.
	 *
	 * @param personNum the person num
	 */
	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	/**
	 * Gets the clerk num.
	 *
	 * @return the clerk num
	 */
	public String getClerkNum() {
		return clerkNum;
	}

	/**
	 * Sets the clerk num.
	 *
	 * @param clerkNum the clerk num
	 */
	public void setClerkNum(String clerkNum) {
		this.clerkNum = clerkNum;
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
	
	public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getAddrInfo() {
		return addrInfo;
	}

	public void setAddrInfo(String addrInfo) {
		this.addrInfo = addrInfo;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * Gets the open date.
	 *
	 * @return the open date
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * Sets the open date.
	 *
	 * @param openDate the open date
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopExportDto [shopNoMerchant=");
		builder.append(shopNoMerchant);
		builder.append(", shopType=");
		builder.append(shopType);
		builder.append(", memberNameShop=");
		builder.append(memberNameShop);
		builder.append(", guidNum=");
		builder.append(guidNum);
		builder.append(", personNum=");
		builder.append(personNum);
		builder.append(", clerkNum=");
		builder.append(clerkNum);
		builder.append(", orderAmount=");
		builder.append(orderAmount);
		builder.append(", bizScope=");
		builder.append(bizScope);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", addrInfo=");
		builder.append(addrInfo);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append("]");
		return builder.toString();
	}

}
