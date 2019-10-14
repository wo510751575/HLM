package com.lj.business.weixin.dto.imSensitiveWords;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindImSensitiveWordsPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8531395838769262129L; 
	
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
    private String status;
    /**
     * 
     * 显示序号
     */
    private Integer showIndex;
    
    /**
     * 商户号
     */
   private String merchantNo;
   
    
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImSensitiveWordsPage [word=");
		builder.append(word);
		builder.append(", version=");
		builder.append(version);
		builder.append(", status=");
		builder.append(status);
		builder.append(", showIndex=");
		builder.append(showIndex);
		builder.append("]");
		return builder.toString();
	}

}
