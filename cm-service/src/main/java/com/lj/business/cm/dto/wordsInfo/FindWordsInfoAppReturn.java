package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;

public class FindWordsInfoAppReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 883241044308399512L;

	/**
     * CODE .
     */
    private String code;
    
    /**
     * 内容 .
     */
    private String content;
    
    /**
     * 是否默认：0非默认话术、1默认话术 .
     */
    private Integer defaultFlag;

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

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWordsInfoAppReturn [code=");
		builder.append(code);
		builder.append(", content=");
		builder.append(content);
		builder.append(", defaultFlag=");
		builder.append(defaultFlag);
		builder.append("]");
		return builder.toString();
	}

}
