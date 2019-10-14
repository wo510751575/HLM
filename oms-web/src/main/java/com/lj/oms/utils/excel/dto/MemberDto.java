package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：店长导入数据 dto
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月12日
 */
public class MemberDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2900957229659895159L;

	/** 终端代码. */
	@ExcelField(title="终端代码", align=2, sort=1)
	private String shopNoMerchant;
    /**
     * 姓名 .
     */
	@ExcelField(title="员工姓名", align=2, sort=4)
    private String memberName;
    /**
     * 手机号 .
     */
	@ExcelField(title="手机号（只能文本格式）", align=2, sort=5)
    private String mobile;

    /**
     * 手机串号 .
     */
	@ExcelField(title="手机串号(IMEI)", align=2, sort=6)
    private String imei;
    /**
     * 微信帐号
     */
	@ExcelField(title="微信帐号", align=2, sort=7)
    private String NoWx;
    /**
     * 性别
     */
	@ExcelField(title="性别", align=2, sort=8)
    private String sex;
    /**
     * 年龄
     */
	@ExcelField(title="年龄", align=2, sort=9)
    private Integer age;
    /**
     * 入职时间
     */
	@ExcelField(title="入职时间(格式：2017-01-01)", align=2, sort=10)
    private String workDate;
    /**
     * 职位
     */
	@ExcelField(title="职位(店长)", align=2, sort=11)
    private String memberType;
	/** 地区. */
	@ExcelField(title="地区(华东地区、华南地区、华中地区、华北地区、西北地区、西南地区、东北地区、台港澳地区)", align=2, sort=90)
    private String areaCode;  
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
	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the wxNo
	 */
	public String getNoWx() {
		return NoWx;
	}
	/**
	 * @param wxNo the wxNo to set
	 */
	public void setNoWx(String noWx) {
		this.NoWx = noWx;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return the workDate
	 */
	public String getWorkDate() {
		return workDate;
	}
	/**
	 * @param workDate the workDate to set
	 */
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	/**
	 * @return the memberType
	 */
	public String getMemberType() {
		return memberType;
	}
	/**
	 * @param memberType the memberType to set
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
    
	
}
