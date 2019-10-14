package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：员工数据导出Dto
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月18日
 */
public class GuidExportDto implements Serializable {

	 /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = 4800454545051696114L;
	
	/** 终端代码. */
	@ExcelField(title="终端代码", align=2, sort=1)
	private String shopNoMerchant;
	
    /**
     *导购姓名 
     */
	@ExcelField(title="员工姓名", align=2, sort=3)
    private String memberName;

	/**
     * 手机号
     */
	@ExcelField(title="手机号码", align=3, sort=4)
    private String mobile;
    /**
     * 手机串号
     */
	@ExcelField(title="手机串号", align=3, sort=5)
    private String imei;
    
    /**
    * 年龄
    */
	@ExcelField(title="年龄", align=0, sort=6)
    private Integer age;
	
    /**
     * 性别
     */
	@ExcelField(title="性别", align=2, sort=7)
    private String gender;
	
	/**
     * 微信账号
     */    
	@ExcelField(title="微信账号", align=2, sort=8)
    private String noWx;
	
    /**
     * 入职时间
     */
	@ExcelField(title="入职时间(格式：2017-01-01)", align=2, sort=9)
    private String workDate;
	
	  /**
     * 职位
     */
	@ExcelField(title="职位(导购)", align=2, sort=11)
    private String memberType;
	
	 /**
	   *所属区域
	   */
	@ExcelField(title="所属区域", align=2, sort=12, dictType="erp_dict_1")
    private String areaCode;  
	
	/**
	 *状态
	 */
	@ExcelField(title="状态", align=2, sort=13)
	private String status;  
	
	
    public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}


	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}


	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
  
}
