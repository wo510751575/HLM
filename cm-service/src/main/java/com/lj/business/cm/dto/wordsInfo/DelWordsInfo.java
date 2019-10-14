package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;

public class DelWordsInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6803745257260989345L; 

	/**
     * CODE .
     */
    private String code;
    
    /**
     * 内容 .
     */
    private String content;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DelWordsInfo [code=");
		builder.append(code);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}
    
}
