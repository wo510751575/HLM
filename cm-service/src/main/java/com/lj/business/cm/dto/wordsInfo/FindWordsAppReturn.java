package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;
import java.util.List;

public class FindWordsAppReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 883241044308399512L;

	/**
     * CODE .
     */
    private String code;
    
    /**
     * 类型名称 .
     */
    private String typeName;
    
    /**
     * 类型排序 .
     */
    private String seq;
    
    /**
     * 话术信息列表 .
     */
    private List<FindWordsInfoReturn> wordsList;

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

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public List<FindWordsInfoReturn> getWordsList() {
		return wordsList;
	}

	public void setWordsList(List<FindWordsInfoReturn> wordsList) {
		this.wordsList = wordsList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWordsAppReturn [code=");
		builder.append(code);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append(", seq=");
		builder.append(seq);
		builder.append(", wordsList=");
		builder.append(wordsList);
		builder.append("]");
		return builder.toString();
	}

}
