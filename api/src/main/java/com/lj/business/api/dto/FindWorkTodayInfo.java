package com.lj.business.api.dto;

import java.io.Serializable;

/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 * 
 * CreateDate: 2017年7月1日
 */
public class FindWorkTodayInfo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1678344254859318301L;
	


    /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    
    /**
     * 导购编号（必填） .
     */
    private String memberNoGm;


	/**
	 * Gets the 导购编号（必填） .
	 *
	 * @return the 导购编号（必填） 
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号（必填） .
	 *
	 * @param memberNoGm the new 导购编号（必填） 
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 商户编号(必填) .
	 *
	 * @return the 商户编号(必填) 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号(必填) .
	 *
	 * @param merchantNo the new 商户编号(必填) 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkTodayInfo [merchantNo=").append(merchantNo)
				.append(", memberNoGm=").append(memberNoGm).append("]");
		return builder.toString();
	}
	

}
