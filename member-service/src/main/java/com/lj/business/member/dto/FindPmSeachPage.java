package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity;
import com.lj.base.core.util.StringUtils;

/**
 * The Class FindPmSeachPage.
 */
public class FindPmSeachPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8273481863869779167L;
	
	
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
     * 查询KEY .
     */
    private String searchKey;
    
    /**
     * 会员编号，多个用英文逗号,隔开
     */
    private String memberNos;
    
    public String[] getMemberNoList() {
    	if(StringUtils.isNotEmpty(memberNos)) {
    		return memberNos.split(",");
    	}
    	return null;
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


	/**
	 * Gets the 查询KEY .
	 *
	 * @return the 查询KEY 
	 */
	public String getSearchKey() {
		return searchKey;
	}

	/**
	 * Sets the 查询KEY .
	 *
	 * @param searchKey the new 查询KEY 
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @return the memberNos
	 */
	public String getMemberNos() {
		return memberNos;
	}

	/**
	 * @param memberNos the memberNos to set
	 */
	public void setMemberNos(String memberNos) {
		this.memberNos = memberNos;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmSeachPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", searchKey=");
		builder.append(searchKey);
		builder.append(", memberNos=");
		builder.append(memberNos);
		builder.append("]");
		return builder.toString();
	}
    
}
