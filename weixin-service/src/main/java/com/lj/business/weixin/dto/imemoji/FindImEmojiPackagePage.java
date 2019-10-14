package com.lj.business.weixin.dto.imemoji;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindImEmojiPackagePage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8023294514707203988L; 

	/**
     * 表情包名称 .
     */
    private String packageName;

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
		builder.append("FindImEmojiPackagePage [packageName=");
		builder.append(packageName);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
