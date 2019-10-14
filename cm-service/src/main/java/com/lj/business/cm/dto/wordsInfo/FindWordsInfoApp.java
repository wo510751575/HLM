package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;

public class FindWordsInfoApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8570105982920744577L; 

	/**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 内容 .
     */
    private String content;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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
		builder.append("FindWordsInfoApp [merchantNo=");
		builder.append(merchantNo);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

}
