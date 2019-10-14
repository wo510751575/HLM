package com.lj.business.api.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * CreateDate: 2017年7月1日
 * @Company: 扬恩科技有限公司
 */
public class FindWorkTodayInfoShop implements Serializable {


    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5569900436974002339L;
	/**
     * 导购编号（必填） .
     */
    private String memberNoGm;

    
    /**
     * 店长编号（必填） .
     */
    private String memberNoShop;
    
    /** 商户号.（必填） */
    private String merchantNo;
    
    /**
     * 分店编号
     */
    
    

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
	 * Gets the 店长编号（必填） .
	 *
	 * @return the 店长编号（必填） 
	 */
	public String getMemberNoShop() {
		return memberNoShop;
	}

	/**
	 * Sets the 店长编号（必填） .
	 *
	 * @param memberNoShop the new 店长编号（必填） 
	 */
	public void setMemberNoShop(String memberNoShop) {
		this.memberNoShop = memberNoShop;
	}
	
	/**
	 * Gets the merchant no.
	 *
	 * @return the merchant no
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the merchant no.
	 *
	 * @param merchantNo the new merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkTodayInfoShop [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNoShop=");
		builder.append(memberNoShop);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}

}
