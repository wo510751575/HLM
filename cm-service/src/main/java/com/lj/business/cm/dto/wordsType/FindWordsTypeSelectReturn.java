package com.lj.business.cm.dto.wordsType;

import java.io.Serializable;

public class FindWordsTypeSelectReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 8695999610946479698L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 类型名称 .
     */
    private String typeName;

    /**
     * 排序 .
     */
    private Integer seq;

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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWordsTypeSelectReturn [code=");
		builder.append(code);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append(", seq=");
		builder.append(seq);
		builder.append("]");
		return builder.toString();
	}

}
