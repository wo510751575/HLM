package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class FindPersonMemberReturn.
 */
public class FindPersonMemberAgeStatisticsReturn implements Serializable {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8812969072108321896L;

	/**
	 * 年龄数
	 */
	private Integer ageCount;

	/**
	 * 分店编号
	 */
	

	public Integer getAgeCount() {
		return ageCount;
	}

	public void setAgeCount(Integer ageCount) {
		this.ageCount = ageCount;
	}

}
