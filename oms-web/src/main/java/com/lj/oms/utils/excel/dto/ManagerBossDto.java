package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

// TODO: Auto-generated Javadoc
/**
 * 
 * 
 * 类说明：总监数据导入模板
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年9月5日
 */
public class ManagerBossDto implements Serializable{

	 /** Generate cron. */
	private static final long serialVersionUID = -477286176803168419L;
    /**
     * 姓名 .
     */
	@ExcelField(title="员工姓名", align=2, sort=4)
    private String memberName;
    /**
     * 手机号 .
     */
	@ExcelField(title="手机号", align=3, sort=5)
    private String mobile;
	/**
	 * 手机串号
	 */
	@ExcelField(title="手机串号", align=3, sort=6)
	private String imei;
	
    /** 微信帐号. */
	@ExcelField(title="微信帐号", align=3, sort=7)
    private String NoWx;
    
    /** 性别. */
	@ExcelField(title="性别", align=2, sort=8)
    private String sex;
    
    /** 入职时间. */
	@ExcelField(title="入职时间(格式：2017-01-01)", align=2, sort=10)
    private String workDate;
    
    /** 职位. */
	@ExcelField(title="职位(总监)", align=2, sort=11)
    private String memberType;
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * Gets the member name.
	 *
	 * @return the member name
	 */
	public String getMemberName() {
		return memberName;
	}
	
	/**
	 * Sets the member name.
	 *
	 * @param memberName the member name
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
	 * @param mobile the mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * Gets the no wx.
	 *
	 * @return the no wx
	 */
	public String getNoWx() {
		return NoWx;
	}
	
	/**
	 * Sets the no wx.
	 *
	 * @param noWx the no wx
	 */
	public void setNoWx(String noWx) {
		NoWx = noWx;
	}
	
	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * Sets the sex.
	 *
	 * @param sex the sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * Gets the work date.
	 *
	 * @return the work date
	 */
	public String getWorkDate() {
		return workDate;
	}
	
	/**
	 * Sets the work date.
	 *
	 * @param workDate the work date
	 */
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	/**
	 * Gets the member type.
	 *
	 * @return the member type
	 */
	public String getMemberType() {
		return memberType;
	}
	
	/**
	 * Sets the member type.
	 *
	 * @param memberType the member type
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
}
