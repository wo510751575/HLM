package com.lj.business.cm.dto.merchantParams;

import com.lj.base.core.pagination.PageParamEntity; 

/**
 * The Class FindMerchantParamsPage.
 */
public class FindMerchantParamsPage extends PageParamEntity { 

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2737888558653360864L;
	/**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 系统参数名 .
     */
    private String sysParamName;
    
    /**
     * 商户名称
     */
    private String merchantName;
    
    /**
     * 分组
     */
    private String groupName;
    
    /**
     * '是否只有运维能修改，0代表业务也能修改，1代表只有运维能修改'
     */
    private String onlyAdminModify;

	public String getOnlyAdminModify() {
		return onlyAdminModify;
	}

	public void setOnlyAdminModify(String onlyAdminModify) {
		this.onlyAdminModify = onlyAdminModify;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getSysParamName() {
		return sysParamName;
	}

	public void setSysParamName(String sysParamName) {
		this.sysParamName = sysParamName;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMerchantParamsPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", sysParamName=");
		builder.append(sysParamName);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", onlyAdminModify=");
		builder.append(onlyAdminModify);
		builder.append("]");
		return builder.toString();
	}
    
}
