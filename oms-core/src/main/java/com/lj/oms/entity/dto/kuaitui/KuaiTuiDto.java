package com.lj.oms.entity.dto.kuaitui;

import java.io.Serializable;

/**
 * 
 * 
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年1月29日
 */
public class KuaiTuiDto implements Serializable{

	public static final String ROLE_KEY = "kuaitui";
	public static final String OFFICE_NAME_PRE = "快推_";
	/**
	 * 
	 */
	private static final long serialVersionUID = 3482168215231758893L;
	/**
	 * 商户编号
	 */
	private String merchantNo;
	/**
	 * 手机（唯一）
	 */
	private String mobile;
	/**
	 * 商户昵称
	 */
	private String merchantName;

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KuaiTuiDto [merchantNo=");
		builder.append(merchantNo);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append("]");
		return builder.toString();
	}
}
