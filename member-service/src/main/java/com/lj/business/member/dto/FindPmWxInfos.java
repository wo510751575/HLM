package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * 
 * 类说明：商户下的微信OpenId结合
 * <p>
 * 详细描述：
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月10日
 */
public class FindPmWxInfos implements Serializable {

	private static final long serialVersionUID = -7967228816554861097L;

	/**
	 * 商户编号(必填).
	 */
	private String merchantNo;
	
	/**
     * 微信wxOpenId数组,存储用于对外接口返回(必填)
     */
    private String[] wxOpenId;

    
	/**
	 * Gets the 商户编号(必填) .
	 * @return the 商户编号(必填) 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号(必填) .
	 * @param merchantNo the new 商户编号(必填) 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
     * 微信wxOpenId数组,存储用于对外接口返回
     */
	public String[] getWxOpenId() {
		return wxOpenId;
	}
	/**
     * 微信wxOpenId数组,存储用于对外接口返回
     */
	public void setWxOpenId(String[] wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmWxBpInfo [merchantNo=").append(merchantNo)
				.append(", wxOpenId=").append(wxOpenId)
				.append("]");
		return builder.toString();
	}

	

}
