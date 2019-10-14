package com.lj.business.weixin.dto.imSensitiveWords;

import java.io.Serializable;

public class FindImSensitiveWords implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7106836321889365646L; 
	/**
	 * code
	 */
	private String code;

    /**
     * 敏感词 .
     */
    private String word;

    /**
     * 版本号 .
     */
    private Long version;

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;

    
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FindImSensitiveWords [word=" + word + ", version=" + version
				+ ", status=" + status + "]";
	}
    
    
    
}
