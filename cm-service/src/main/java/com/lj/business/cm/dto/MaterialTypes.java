package com.lj.business.cm.dto;

import java.io.Serializable;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月24日下午2:23:34
 */
public class MaterialTypes implements Serializable {

	private static final long serialVersionUID = 5276890211096144424L;
	
	private String code;
	private String type;
	private String typeName;
	private String serviceName;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	
	

}
