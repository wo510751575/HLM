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
public class ShopDto implements Serializable{

	 /** Generate cron. */
	private static final long serialVersionUID = 1651893109038300480L;
	 /** 
	  * 店长名称. 
	  * 
	  */
	@ExcelField(title="店长", align=2, sort=3)
    private String memberNameShop;
    
    /**
     *  导购数量.
     */
	@ExcelField(title="导购数量", align=2, sort=4)
    private String guidNum;
    
    /** 客户数量. */
	@ExcelField(title="客户数量", align=2, sort=5)
    private String personNum;
    
    /** 店员数量. */
	@ExcelField(title="店员数量", align=2, sort=6)
    private String clerkNum;
    /**
     * 经营范围 .
     */
	@ExcelField(title="经营范围", align=2, sort=7)
    private String bizScope;
    /**
     * 联系电话 .
     */
	@ExcelField(title="联系电话", align=2, sort=8)
    private String telephone;
    /** 
     * 开店时间.
     * */
	@ExcelField(title="开店时间", align=2, sort=9)
    private Date openDate;
     
    /** 终端状态. */
	@ExcelField(title="终端状态", align=2, sort=10)
    private String status;

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


    
    
}
