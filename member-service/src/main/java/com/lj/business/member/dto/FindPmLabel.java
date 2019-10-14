package com.lj.business.member.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPmLabel.
 */
public class FindPmLabel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8573132690335487038L; 
	
	/** The code. */
	private String code;
	
	/** 商户编号. */
	private String 	merchantNo;
	
	
	/** 导购编号. */
    private String  memberNoGm;
  
    /** 区域code. */
    private String areaCode;
    
    /** 终端微信. */
    private String  shopWx;
    
    /** 客户编号. */
    private String  memberNo;
    
	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	/**
	 * Gets the area code.
	 *
	 * @return the area code
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the area code.
	 *
	 * @param areaCode the area code
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	/**
	 * Gets the member no gm.
	 *
	 * @return the member no gm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the member no gm.
	 *
	 * @param memberNoGm the member no gm
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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
	 * @param merchantNo the merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmLabel [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", shopWx=");
		builder.append(shopWx);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append("]");
		return builder.toString();
	}

}
