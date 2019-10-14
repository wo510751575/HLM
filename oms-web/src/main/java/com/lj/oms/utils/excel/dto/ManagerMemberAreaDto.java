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
public class ManagerMemberAreaDto implements Serializable{

	 /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -1041291338095116450L;
	
	/**
	 * 管理人员姓名
	 */
	@ExcelField(title="员工姓名", align=2, sort=1)
	private String memberName;

	/**
     * 性别
     */
	@ExcelField(title="性别", align=2, sort=2)
    private String sex;

    /**
    * 年龄
    */
	@ExcelField(title="年龄", align=0, sort=3)
    private Integer age;
	
	
    /**
     * 手机号码
     */
	@ExcelField(title="手机号码", align=2, sort=4)
    private String mobile;
	
   /**
   *入职时间
   */
	@ExcelField(title="入职时间", align=2, sort=5)
	private Date workDate;
  
	 /**
	   *所属区域
	   */
	@ExcelField(title="所属区域", align=2, sort=6, dictType="erp_dict_1")
	private String areaCode;
	
  /**
   * 职位
           店长：SHOP
           老板：BOSS .
   */
	@ExcelField(title="职位", align=2, sort=7)
	private String memberType;
	
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
	public String getMemberType() {
		return memberType;
	}
	
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

 
}


