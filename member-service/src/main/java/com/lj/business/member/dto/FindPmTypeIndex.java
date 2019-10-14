package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class FindPmTypeIndex.
 */
public class FindPmTypeIndex implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2121866154172654955L;
	
	

    /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    
    /**
     * 导购编号(必填,分店编号,导购编号 2选1 )  .
     */
    private String memberNoGm;
    
    /**
     * 导购微信号
     */
    private String noWxGm;
    
    /**
     * 分店编号(必填,分店编号,导购编号 2选1 ) .
     */
    



	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

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

	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmTypeIndex [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append("]");
		return builder.toString();
	}

}
