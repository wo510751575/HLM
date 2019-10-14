package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class FindPersonMemberReturn.
 */
public class FindPersonMemberSexStatisticsReturn implements Serializable {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8812969072108321896L;

	/**
	 * 性别数
	 */
	private Integer sexCount;

	/**
	 * 商户编号
	 */
	private String merchantNo;

	/**
	 * 分店编号
	 */
	

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 区域编码
	 */
	private String areaCode;
	
	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * 商户名字
	 */
	

	public Integer getSexCount() {
		return sexCount;
	}

	public void setSexCount(Integer sexCount) {
		this.sexCount = sexCount;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPersonMemberSexStatisticsReturn [sexCount=");
		builder.append(sexCount);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append("]");
		return builder.toString();
	}

}
