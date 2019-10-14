package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：管理人员
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月19日
 */
public class ManagerMemberShopDto implements Serializable{

	 /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -1041291338095116450L;
	
    
	/**
     * 终端代码
     */
	@ExcelField(title="终端代码", align=2, sort=1)
    private String shopNoMerchant;
	
    /**
     * 分店名称
     */
	@ExcelField(title="终端名称", align=2, sort=2)
    private String memberNameShop;
	/**
	 * 管理人员姓名
	 */
	@ExcelField(title="员工姓名", align=2, sort=3)
	private String memberName;

	/**
	 * 微信帐号
	 */
	@ExcelField(title="微信帐号", align=2, sort=4)
	private String noWx;
	
    /**
     * 性别
     */
	@ExcelField(title="性别", align=2, sort=5)
    private String sex;

    /**
     * 状态
     */
	@ExcelField(title="状态", align=2, sort=6)
    private String status;
    /**
     * 手机号码
     */
	@ExcelField(title="手机号码", align=2, sort=7)
    private String mobile;
    /**
     * 手机串号
     */
	@ExcelField(title="手机串号", align=2, sort=8)
    private String imei;
   /**
    * 年龄
    */
	@ExcelField(title="年龄", align=0, sort=9)
    private Integer age;
	
   /**
   *入职时间
   */
	@ExcelField(title="入职时间", align=2, sort=10)
	private Date workDate;
  
  /**
   * 职位
           店长：SHOP
           老板：BOSS .
   */
	@ExcelField(title="职位", align=2, sort=11)
  private String memberType;
	
	/**
	 * 职位
           店长：SHOP
           老板：BOSS .
	 */
	@ExcelField(title="所属区域", align=2, sort=12)
	private String areaName;
	
public String getMemberName() {
	return memberName;
}

public void setMemberName(String memberName) {
	this.memberName = memberName;
}

public String getMemberNameShop() {
	return memberNameShop;
}

public void setMemberNameShop(String memberNameShop) {
	this.memberNameShop = memberNameShop;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
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

public Integer getAge() {
	return age;
}

public void setAge(Integer age) {
	this.age = age;
}

public Date getWorkDate() {
	return workDate;
}

public void setWorkDate(Date workDate) {
	this.workDate = workDate;
}

public String getShopNoMerchant() {
	return shopNoMerchant;
}

public void setShopNoMerchant(String shopNoMerchant) {
	this.shopNoMerchant = shopNoMerchant;
}

public String getNoWx() {
	return noWx;
}

public void setNoWx(String noWx) {
	this.noWx = noWx;
}

public String getMemberType() {
	return memberType;
}

public void setMemberType(String memberType) {
	this.memberType = memberType;
}

public String getAreaName() {
	return areaName;
}

public void setAreaName(String areaName) {
	this.areaName = areaName;
}

@Override
public String toString() {
	return "ManagerMemberShopDto [shopNoMerchant=" + shopNoMerchant
			+ ", memberNameShop=" + memberNameShop + ", memberName="
			+ memberName + ", noWx=" + noWx + ", sex=" + sex + ", status="
			+ status + ", mobile=" + mobile + ", imei=" + imei + ", age=" + age
			+ ", workDate=" + workDate + ", memberType=" + memberType
			+ ", areaName=" + areaName + "]";
}
   
   
   
   
 
}


