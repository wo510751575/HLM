package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明:客户
 *  
 * 
 * <p>
 * 详细描述:
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月12日
 */
public class PersonMemberDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196432L;

	
	 /**
     * 客户名称
     */
	@ExcelField(title="客户名称", align=2, sort=20)
    private String memberName;
	
	 /**
     * 手机
     */
	@ExcelField(title="手机(使用文本格式)", align=3, sort=30)
    private String mobile;
	
	/**
     * 性别
     * 男.
		MALE("男"),
	 ** 女. *
		FEMALE("女");
     */
	@ExcelField(title="性别(男,女)", align=2, sort=40)
    private String sex;
	
	/**
     * 年龄
     */
	@ExcelField(title="年龄", align=2, sort=50)
    private Integer age;
	
	/**
     * 职业
     */
	@ExcelField(title="职业", align=2, sort=60)
    private String job;
	
	/**
     * 客户分组
     */
	@ExcelField(title="客户分组(成单,暂停跟进,紧急,交叉,非意向,意向客户(到店),意向客户(未到店),未分组客户 (使用英文格式括号))", align=0, sort=70)
    private String pmTypeType;
	
	/**
     * 标签
     */
    @ExcelField(title="标签(多个标签使用英文逗号分隔)", align=3, sort=70)
    private String labelName;
	
	  /**
     * 所需产品 .
     */
	@ExcelField(title="所需产品", align=2, sort=80)
    private String bomName;
	
	/**
	 * 所在楼盘 .
	 */
	@ExcelField(title="所在楼盘 ", align=2, sort=90)
	private String houses;
	
	/**
	 * 装修进度 .
	 * NODO("未交房/未装修"),
		HASDO("已装好/添置"),
		DOING("装修中"),
		CHANGE("换产品"),
		OTHER("其他")
	 */
	@ExcelField(title="装修进度(未交房/未装修,已装好/添置,装修中,换产品,其他) ", align=0, sort=100)
	private String spruceUp;
	
	/**
     * 客户来源 :
     * SHOP_SACN("到店扫码"),
	NO_SHOP_SACN("未到店扫码"),
	OLD("老客户转介绍"),
	NET("网络推广"),
	OTHER("其他")
     */
	@ExcelField(title="会员来源(到店扫码,未到店扫码,老客户转介绍,网络推广,其他) ", align=0, sort=110)
    private String memberSrc;

	/**
	 * 所属导购手机号
	 */
	@ExcelField(title="所属导购手机号(使用文本格式)", align=2, sort=120)
    private String mobileGm;
	
	
	/**
	 * @return the mobileGm
	 */
	public String getMobileGm() {
		return mobileGm;
	}

	/**
	 * @param mobileGm the mobileGm to set
	 */
	public void setMobileGm(String mobileGm) {
		this.mobileGm = mobileGm;
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
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the pmTypeType
	 */
	public String getPmTypeType() {
		return pmTypeType;
	}

	/**
	 * @param pmTypeType the pmTypeType to set
	 */
	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

	/**
	 * @return the bomName
	 */
	public String getBomName() {
		return bomName;
	}

	/**
	 * @param bomName the bomName to set
	 */
	public void setBomName(String bomName) {
		this.bomName = bomName;
	}

	/**
	 * @return the houses
	 */
	public String getHouses() {
		return houses;
	}

	/**
	 * @param houses the houses to set
	 */
	public void setHouses(String houses) {
		this.houses = houses;
	}

	/**
	 * @return the spruceUp
	 */
	public String getSpruceUp() {
		return spruceUp;
	}

	/**
	 * @param spruceUp the spruceUp to set
	 */
	public void setSpruceUp(String spruceUp) {
		this.spruceUp = spruceUp;
	}

	/**
	 * @return the memberSrc
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PersonMemberDto [memberName=");
        builder.append(memberName);
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append(", sex=");
        builder.append(sex);
        builder.append(", age=");
        builder.append(age);
        builder.append(", job=");
        builder.append(job);
        builder.append(", pmTypeType=");
        builder.append(pmTypeType);
        builder.append(", labelName=");
        builder.append(labelName);
        builder.append(", bomName=");
        builder.append(bomName);
        builder.append(", houses=");
        builder.append(houses);
        builder.append(", spruceUp=");
        builder.append(spruceUp);
        builder.append(", memberSrc=");
        builder.append(memberSrc);
        builder.append(", mobileGm=");
        builder.append(mobileGm);
        builder.append("]");
        return builder.toString();
    }

}
