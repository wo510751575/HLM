package com.lj.business.weixin.dto.imemoji;

import java.io.Serializable;
import java.util.List;

import com.lj.business.weixin.dto.imemoji.FindImEmojiReturn;

public class NewEmojiPackageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2801848380744423913L;

	/**
	 * code
	 */
	private String code;
	/**
	 * packageName
	 */
	private String packageName;
	/**
	 * packageLogo
	 */
	private String packageLogo;
	/**
	 * status
	 */
	private Integer status;
	/**
	 * showIndex
	 */
	private Integer showIndex;
	/**
	 * version
	 */
	private Long version;
	/**
	 * emojiList
	 */
	private List<FindImEmojiReturn> emojiList;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public List<FindImEmojiReturn> getEmojiList() {
		return emojiList;
	}
	public void setEmojiList(List<FindImEmojiReturn> emojiList) {
		this.emojiList = emojiList;
	}
	
	/**
	 * @return the packageLogo
	 */
	public String getPackageLogo() {
		return packageLogo;
	}
	/**
	 * @param packageLogo the packageLogo to set
	 */
	public void setPackageLogo(String packageLogo) {
		this.packageLogo = packageLogo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewEmojiPackageReturn [code=");
		builder.append(code);
		builder.append(", packageName=");
		builder.append(packageName);
		builder.append(", packageLogo=");
		builder.append(packageLogo);
		builder.append(", status=");
		builder.append(status);
		builder.append(", showIndex=");
		builder.append(showIndex);
		builder.append(", version=");
		builder.append(version);
		builder.append(", emojiList=");
		builder.append(emojiList);
		builder.append("]");
		return builder.toString();
	}
	
}
