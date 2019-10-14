package com.lj.business.weixin.dto.imemoji;

import java.io.Serializable;
import java.util.List;

public class FindNewEmojiPackageReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 6942524584193037338L;

	/**
	 * 访问域名
	 */
	private String accessUrl;
	
	/**
	 * 表情包列表
	 */
	private List<NewEmojiPackageReturn> packageList;

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public List<NewEmojiPackageReturn> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<NewEmojiPackageReturn> packageList) {
		this.packageList = packageList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindNewEmojiPackageReturn [accessUrl=");
		builder.append(accessUrl);
		builder.append(", packageList=");
		builder.append(packageList);
		builder.append("]");
		return builder.toString();
	}
	
}
