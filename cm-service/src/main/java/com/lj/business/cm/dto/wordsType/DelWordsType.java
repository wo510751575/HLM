package com.lj.business.cm.dto.wordsType;

import java.io.Serializable;

public class DelWordsType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1950458800589540508L; 
	/**
     * CODE .
     */
    private String code;
    /**
     * 类型名称 .
     */
    private String typeName;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DelWordsType [code=");
		builder.append(code);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append("]");
		return builder.toString();
	}
    
}
