package com.lj.business.weixin.dto.imemoji;

import java.io.Serializable;

public class FindNewImEmojiReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -1109642259027785747L;

	/**
     * CODE .
     */
    private String code;


    /**
     * 表情名称 .
     */
    private String emojiName;

    /**
     * 表情图片地址 .
     */
    private String emojiUrl;

    

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;
    
    /**
     * 显示序号 .
     */
    private Integer showIndex;
    
    /**
     * 版本号 .
     */
    private Long version;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmojiName() {
		return emojiName;
	}

	public void setEmojiName(String emojiName) {
		this.emojiName = emojiName;
	}

	public String getEmojiUrl() {
		return emojiUrl;
	}

	public void setEmojiUrl(String emojiUrl) {
		this.emojiUrl = emojiUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindNewImEmojiReturn [code=");
		builder.append(code);
		builder.append(", emojiName=");
		builder.append(emojiName);
		builder.append(", emojiUrl=");
		builder.append(emojiUrl);
		builder.append(", status=");
		builder.append(status);
		builder.append(", showIndex=");
		builder.append(showIndex);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
    
}
