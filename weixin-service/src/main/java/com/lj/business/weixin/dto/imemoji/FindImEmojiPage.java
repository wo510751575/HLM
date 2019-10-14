package com.lj.business.weixin.dto.imemoji;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindImEmojiPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 511204941279195620L; 


    /**
     * 表情包CODE .
     */
    private String packageCode;

    /**
     * 表情名称 .
     */
    private String emojiName;

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getEmojiName() {
		return emojiName;
	}

	public void setEmojiName(String emojiName) {
		this.emojiName = emojiName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImEmojiPage [packageCode=");
		builder.append(packageCode);
		builder.append(", emojiName=");
		builder.append(emojiName);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
    
}
