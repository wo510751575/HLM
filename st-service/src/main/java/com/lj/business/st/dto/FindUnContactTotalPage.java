package com.lj.business.st.dto;

import com.lj.base.core.pagination.PageParamEntity; 

/**
 * The Class FindUnContactTotalPage.
 */
public class FindUnContactTotalPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1646722324218406927L; 

	 /**
     * 导购编号  .
     */
    private String memberNoGm;

	/**
	 * Gets the 导购编号  .
	 *
	 * @return the 导购编号  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号  .
	 *
	 * @param memberNoGm the new 导购编号  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindUnContactTotalPage [memberNoGm=")
				.append(memberNoGm).append("]");
		return builder.toString();
	}
}
