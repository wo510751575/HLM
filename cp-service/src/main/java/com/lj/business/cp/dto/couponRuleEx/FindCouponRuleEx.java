package com.lj.business.cp.dto.couponRuleEx;

import java.io.Serializable;

public class FindCouponRuleEx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6565896864959872947L;
	
	/**
	 * code
	 */
	private String code;

	/**
	 * 规则code
	 */
	private String ruleCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	
	
}
