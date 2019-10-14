package com.lj.business.weixin.dto.publicaccount;

import java.io.Serializable;

public class FindWxPublicAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3684634432428765384L; 

	private String code;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 公众号用户名 .
     */
    private String paUsername;

    /**
     * 商户编号 .
     */
    private String merchantNo;
    
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the noWxZk
	 */
	public String getNoWxZk() {
		return noWxZk;
	}

	/**
	 * @param noWxZk the noWxZk to set
	 */
	public void setNoWxZk(String noWxZk) {
		this.noWxZk = noWxZk;
	}

	/**
	 * @return the paUsername
	 */
	public String getPaUsername() {
		return paUsername;
	}

	/**
	 * @param paUsername the paUsername to set
	 */
	public void setPaUsername(String paUsername) {
		this.paUsername = paUsername;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxPublicAccount [code=");
		builder.append(code);
		builder.append(", noWxZk=");
		builder.append(noWxZk);
		builder.append(", paUsername=");
		builder.append(paUsername);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}

}
