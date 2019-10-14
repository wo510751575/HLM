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
public class GuidImportDto implements Serializable {

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
	@ExcelField(title="员工姓名", align=2, sort=2)
    private String memberName;

	/**
     * 手机号
     */
	@ExcelField(title="手机号码（只能文本格式）", align=3, sort=3)
    private String mobile;
    /**
     * 手机串号
     */
//	@ExcelField(title="手机串号", align=3, sort=4)
//    private String imei;
	
	/**
	 * 年龄
	 */
	@ExcelField(title="年龄", align=2, sort=4)
	private String age;
    
    /**
     * 性别
     */
	@ExcelField(title="性别", align=2, sort=5)
    private String gender;
    /**
     * 职位
     */
	@ExcelField(title="职位(导购)", align=2, sort=11)
    private String memberType;


	/**
     * 微信账号
     */    
	@ExcelField(title="微信账号", align=3, sort=6)
    private String noWx;
    /**
     * 入职时间
     */
	@ExcelField(title="入职时间(格式：2017-01-01)", align=2, sort=8)
    private String workDate;
	
	/** 地区. */
	@ExcelField(title="地区(华东地区、华南地区、华中地区、华北地区、西北地区、西南地区、东北地区、台港澳地区)", align=2, sort=90)
    private String areaCode;  

	/**
     * 状态
     */
	@ExcelField(title="状态(NORMAL正常   FREEZE冻结)", align=2, sort=9)
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuidImportDto [shopNoMerchant=");
		builder.append(shopNoMerchant);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", memberType=");
		builder.append(memberType);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}
  
}
