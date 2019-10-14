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
public class ManagerMemberDto implements Serializable{

	 /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -1041291338095116450L;
    /**
     * 分店名称
     */
	@ExcelField(title="终端名称", align=2, sort=1)
    private String memberNameShop;
	/**
	 * 管理人员姓名
	 */
	@ExcelField(title="员工姓名", align=2, sort=2)
	private String memberName;

    /**
     * 性别
     */
	@ExcelField(title="性别", align=2, sort=3)
    private String sex;

    /**
     * 状态
     */
	@ExcelField(title="状态", align=2, sort=4)
    private String status;
    /**
     * 手机号码
     */
	@ExcelField(title="手机号码", align=2, sort=5)
    private String mobile;
    /**
     * 手机串号
     */
	@ExcelField(title="手机串号", align=2, sort=6)
    private String imei;
   /**
    * 年龄
    */
	@ExcelField(title="年龄", align=2, sort=7)
    private String age;
   /**
   *入职时间
   */
  @ExcelField(title="入职时间", align=2, sort=8)
   private Date workDate;
  
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

public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}

public Date getWorkDate() {
	return workDate;
}

public void setWorkDate(Date workDate) {
	this.workDate = workDate;
}

@Override
public String toString() {
	return "ManagerMemberDto [memberNameShop=" + memberNameShop
			+ ", memberName=" + memberName + ", sex=" + sex + ", status="
			+ status + ", mobile=" + mobile + ", imei=" + imei + ", age=" + age
			+ ", workDate=" + workDate + "]";
}
   
   
   
   
 
}


