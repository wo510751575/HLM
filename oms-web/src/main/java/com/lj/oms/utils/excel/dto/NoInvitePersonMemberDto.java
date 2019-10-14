package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 类说明:非邀约型客户
 * <p>
 * 详细描述:
 *   
 *
 * @author 李端强
 *   
 * CreateDate: 2017年12月8日11:22:42
 * @Company: 扬恩科技有限公司
 */
public class NoInvitePersonMemberDto implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8475436664919196432L;

	/** 头像地址. */
	@ExcelField(title="头像地址", align=2, sort=10)
    private String headAddress;
	
	 /** 客户名称. */
	@ExcelField(title="客户名称", align=2, sort=20)
    private String memberName;
	
	 /** 手机. */
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
	
	/** 年龄. */
	@ExcelField(title="年龄", align=2, sort=50)
    private Integer age;
	
	/** 职业. */
	@ExcelField(title="职业", align=2, sort=60)
    private String job;
	
	/** 客户分组. */
	@ExcelField(title="客户分组(意向客户)(单选)", align=0, sort=70)
    private String pmTypeType;
	
	
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
	
	/** 客户来源 : SHOP_SACN("到店扫码"), 	NO_SHOP_SACN("未到店扫码"), 	OLD("老客户转介绍"), 	NET("网络推广"), 	OTHER("其他"). */
	@ExcelField(title="会员来源(到店扫码,未到店扫码,老客户转介绍,网络推广,其他) ", align=0, sort=110)
    private String memberSrc;

	/** 所属导购手机号. */
	@ExcelField(title="所属导购手机号(使用文本格式)", align=2, sort=120)
    private String mobileGm;
	
	/** 乐莎莎-客户标签. */
	@ExcelField(title="标签(正畸)(使用英文格式逗号)", align=2, sort=20)
	private String title;
	
	/** 乐莎莎-客户城市. */
	@ExcelField(title="城市(岳阳市)", align=2, sort=10)
	private String city;
	
	/**
	 * Gets the mobile gm.
	 *
	 * @return the mobileGm
	 */
	public String getMobileGm() {
		return mobileGm;
	}

	/**
	 * Sets the mobile gm.
	 *
	 * @param mobileGm the mobileGm to set
	 */
	public void setMobileGm(String mobileGm) {
		this.mobileGm = mobileGm;
	}

	/**
	 * Gets the head address.
	 *
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * Sets the head address.
	 *
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * Gets the member name.
	 *
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
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
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Gets the job.
	 *
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Sets the job.
	 *
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * Gets the pm type type.
	 *
	 * @return the pmTypeType
	 */
	public String getPmTypeType() {
		return pmTypeType;
	}

	/**
	 * Sets the pm type type.
	 *
	 * @param pmTypeType the pmTypeType to set
	 */
	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

	/**
	 * Gets the bom name.
	 *
	 * @return the bomName
	 */
	public String getBomName() {
		return bomName;
	}

	/**
	 * Sets the bom name.
	 *
	 * @param bomName the bomName to set
	 */
	public void setBomName(String bomName) {
		this.bomName = bomName;
	}

	/**
	 * Gets the houses.
	 *
	 * @return the houses
	 */
	public String getHouses() {
		return houses;
	}

	/**
	 * Sets the houses.
	 *
	 * @param houses the houses to set
	 */
	public void setHouses(String houses) {
		this.houses = houses;
	}

	/**
	 * Gets the spruce up.
	 *
	 * @return the spruceUp
	 */
	public String getSpruceUp() {
		return spruceUp;
	}

	/**
	 * Sets the spruce up.
	 *
	 * @param spruceUp the spruceUp to set
	 */
	public void setSpruceUp(String spruceUp) {
		this.spruceUp = spruceUp;
	}

	/**
	 * Gets the member src.
	 *
	 * @return the memberSrc
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	/**
	 * Sets the member src.
	 *
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonMemberDto [headAddress=" + headAddress + ", memberName="
				+ memberName + ", mobile=" + mobile + ", sex=" + sex + ", age="
				+ age + ", job=" + job + ", pmTypeType=" + pmTypeType
				+ ", bomName=" + bomName + ", houses=" + houses + ", spruceUp="
				+ spruceUp + ", memberSrc=" + memberSrc + "]";
	}
	
}
