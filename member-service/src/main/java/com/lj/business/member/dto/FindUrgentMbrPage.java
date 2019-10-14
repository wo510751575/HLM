package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindUrgentMbrPage.
 */
public class FindUrgentMbrPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8423071218141015432L;
	
	 /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    
    /**
     * 导购编号(必填,分店编号,导购编号 2选1 )  .
     */
    private String memberNoGm;
    
    /**
     * 分店编号(必填，分店编号,导购编号 2选1) .
     */
    
    


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

	/**
	 * Gets the 导购编号(必填,分店编号,导购编号 2选1 )  .
	 *
	 * @return the 导购编号(必填,分店编号,导购编号 2选1 )  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号(必填,分店编号,导购编号 2选1 )  .
	 *
	 * @param memberNoGm the new 导购编号(必填,分店编号,导购编号 2选1 )  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindUrgentMbrPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}
}
