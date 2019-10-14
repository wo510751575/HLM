package com.lj.business.member.dto.fitUpInfo;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindFitUpInfoPage.
 */
public class FindFitUpInfoPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9184566391570929120L; 

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindFitUpInfoPage [style=").append(style)
				.append(", fullName=").append(fullName).append(", houseType=")
				.append(houseType).append(", mobile=").append(mobile)
				.append("]");
		return builder.toString();
	}


}
