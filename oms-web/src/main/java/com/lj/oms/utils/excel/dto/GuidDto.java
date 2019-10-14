package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.ape.common.utils.excel.annotation.ExcelField;

// TODO: Auto-generated Javadoc
/**
 * 类说明：导购导入数据
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 * CreateDate: 2017年7月21日
 */
public class GuidDto implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2900957229659895159L;

	/** 终端代码. */
	@ExcelField(title="终端代码", align=2, sort=2)
	private String shopNoMerchant;
    /**
     * 姓名 .
     */
	@ExcelField(title="员工姓名", align=2, sort=3)
    private String memberName;
	/**
     * 手机号 .
     */
	@ExcelField(title="手机号", align=2, sort=4)
    private String mobile;

    /**
     * 手机串号 .
     */
	@ExcelField(title="手机串号(IMEI)", align=2, sort=5)
    private String imei;
    
    /** 微信帐号. */
	@ExcelField(title="微信帐号", align=2, sort=6)
    private String noWx;
    
    /** 性别. */
	@ExcelField(title="性别", align=2, sort=7)
    private String gender;
    
    /** 入职时间. */
	@ExcelField(title="入职时间(格式：2017-01-01)", align=2, sort=8)
    private String workDate;
    
    /** 职位. */
	@ExcelField(title="职位(导购)", align=2, sort=9)
    private String memberType;

	/** 地区. */
	@ExcelField(title="地区(华东地区、华南地区、华中地区、华北地区、西北地区、西南地区、东北地区、台港澳地区)", align=2, sort=90)
    private String areaCode;
    
    /** 状态. */
	@ExcelField(title="状态(NORMAL正常   FREEZE冻结)", align=2, sort=10)
    private String status;
	
	/**
	 * Gets the member name.
	 *
	 * @return the member name
	 */
	public String getMemberName() {
		return memberName;
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
	 * Sets the member name.
	 *
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	/**
	 * Gets the mobile.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * Sets the mobile.
	 *
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * Gets the imei.
	 *
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	
	/**
	 * Sets the imei.
	 *
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	/**
	 * Gets the no wx.
	 *
	 * @return the wxNo
	 */
	public String getNoWx() {
		return noWx;
	}
	
	/**
	 * Sets the no wx.
	 *
	 * @param noWx the no wx
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the sex
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param sex the sex to set
	 */
	public void setGender(String sex) {
		this.gender = sex;
	}
	
	/**
	 * Gets the work date.
	 *
	 * @return the workDate
	 */
	public String getWorkDate() {
		return workDate;
	}
	
	/**
	 * Sets the work date.
	 *
	 * @param workDate the workDate to set
	 */
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	/**
	 * Gets the member type.
	 *
	 * @return the memberType
	 */
	public String getMemberType() {
		return memberType;
	}
	
	/**
	 * Sets the member type.
	 *
	 * @param memberType the memberType to set
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
    
    /**
     * Gets the shop no merchant.
     *
     * @return the shop no merchant
     */
    public String getShopNoMerchant() {
		return shopNoMerchant;
	}
	
	/**
	 * Sets the shop no merchant.
	 *
	 * @param shopNoMerchant the shop no merchant
	 */
	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}
	
}
