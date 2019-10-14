package com.lj.business.member.dto.service.type;

import java.io.Serializable;

public class FindServiceType implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = -1868929624109764850L;
	/**
	 * CODE .
	 */
	private String code;
	/**
	 * 服务CODE .
	 */
	private String serviceCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@Override
	public String toString() {
		return "FindServiceType [code=" + code + ", serviceCode=" + serviceCode
				+ "]";
	}
    
}
