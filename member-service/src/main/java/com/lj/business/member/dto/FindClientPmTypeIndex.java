package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 
 * 
 * 类说明：客情管理-列表查询-入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月23日
 */
public class FindClientPmTypeIndex extends PageParamEntity {

	private static final long serialVersionUID = 569454911068154876L;

	/**
     * 商户编号(必填) .
     */
    private String merchantNo;
    
    /**
     * 分店编号(必填)  .
     */
    
    
    /**
     * 导购编号(必填)  .
     */
    private String memberNoGm;
    
    /**
     * 客户分类CODE(必填) .
     */
    private String pmTypeCode;

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	/**
	 * @return the pmTypeCode
	 */
	public String getPmTypeCode() {
		return pmTypeCode;
	}

	/**
	 * @param pmTypeCode the pmTypeCode to set
	 */
	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
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
		builder.append("FindClientPmTypeIndex [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", pmTypeCode=");
		builder.append(pmTypeCode);
		builder.append("]");
		return builder.toString();
	}
    
}
