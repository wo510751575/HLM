package com.lj.business.weixin.dto.smallprogram;

import java.io.Serializable;

public class FindWxSmallProgram implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7819077618922544454L; 

	private String code;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 小程序用户名 .
     */
    private String spAppid;
    
    /**
     * 小程序pagePath地址
     */
    private String spPagePath;

    /**
     * 商户编号 .
     */
    private String merchantNo;

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
	 * @return the spAppid
	 */
	public String getSpAppid() {
		return spAppid;
	}

	/**
	 * @param spAppid the spAppid to set
	 */
	public void setSpAppid(String spAppid) {
		this.spAppid = spAppid;
	}


	/**
	 * @return the spPagePath
	 */
	public String getSpPagePath() {
		return spPagePath;
	}

	/**
	 * @param spPagePath the spPagePath to set
	 */
	public void setSpPagePath(String spPagePath) {
		this.spPagePath = spPagePath;
	}
	
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxSmallProgram [code=");
		builder.append(code);
		builder.append(", noWxZk=");
		builder.append(noWxZk);
		builder.append(", spAppid=");
		builder.append(spAppid);
		builder.append(", spPagePath=");
		builder.append(spPagePath);
		builder.append("]");
		return builder.toString();
	}

}
