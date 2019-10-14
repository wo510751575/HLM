package com.lj.business.api.dto;

/**
 * 
 * 
 * 类说明：根据导购编号和商户号查询客户信息Dto
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 杨杰
 * 
 *         CreateDate: 2017年9月5日
 */
public class FindMemberRecordDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2093759927861905877L;

	/**
	 * 导购编号 .
	 */
	private String memberNoGm;
	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

}
