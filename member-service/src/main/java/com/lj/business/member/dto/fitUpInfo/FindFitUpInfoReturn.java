package com.lj.business.member.dto.fitUpInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindFitUpInfoReturn.
 */
public class FindFitUpInfoReturn implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4497810036424305044L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 风格
             北欧：BEI_OU
             新中式：XIN_ZHONG_SHI
              .
     */
    private String style;

    /**
     * 小区全称 .
     */
    private String fullName;

    /**
     * 户型 .
     */
    private String houseType;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 图片地址，逗号分隔 .
     */
    private String imgAddr;

    /**
     * 状态 
             NORMAL正常
              .
     */
    private String status;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

	/**
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 风格 北欧：BEI_OU 新中式：XIN_ZHONG_SHI .
	 *
	 * @return the 风格 北欧：BEI_OU 新中式：XIN_ZHONG_SHI 
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * Sets the 风格 北欧：BEI_OU 新中式：XIN_ZHONG_SHI .
	 *
	 * @param style the new 风格 北欧：BEI_OU 新中式：XIN_ZHONG_SHI 
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * Gets the 小区全称 .
	 *
	 * @return the 小区全称 
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the 小区全称 .
	 *
	 * @param fullName the new 小区全称 
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the 户型 .
	 *
	 * @return the 户型 
	 */
	public String getHouseType() {
		return houseType;
	}

	/**
	 * Sets the 户型 .
	 *
	 * @param houseType the new 户型 
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	/**
	 * Gets the 手机号 .
	 *
	 * @return the 手机号 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the 手机号 .
	 *
	 * @param mobile the new 手机号 
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the 图片地址，逗号分隔 .
	 *
	 * @return the 图片地址，逗号分隔 
	 */
	public String getImgAddr() {
		return imgAddr;
	}

	/**
	 * Sets the 图片地址，逗号分隔 .
	 *
	 * @param imgAddr the new 图片地址，逗号分隔 
	 */
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	/**
	 * Gets the 状态 NORMAL正常 .
	 *
	 * @return the 状态 NORMAL正常 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the 状态 NORMAL正常 .
	 *
	 * @param status the new 状态 NORMAL正常 
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the 备注 .
	 *
	 * @return the 备注 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the 备注 .
	 *
	 * @param remark the new 备注 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * Gets the 创建人 .
	 *
	 * @return the 创建人 
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * Sets the 创建人 .
	 *
	 * @param createId the new 创建人 
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * Gets the 创建时间 .
	 *
	 * @return the 创建时间 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the 创建时间 .
	 *
	 * @param createDate the new 创建时间 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindFitUpInfoReturn [code=").append(code)
				.append(", style=").append(style).append(", fullName=")
				.append(fullName).append(", houseType=").append(houseType)
				.append(", mobile=").append(mobile).append(", imgAddr=")
				.append(imgAddr).append(", status=").append(status)
				.append(", remark=").append(remark).append(", createId=")
				.append(createId).append(", createDate=").append(createDate)
				.append("]");
		return builder.toString();
	}
}
