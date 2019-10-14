package com.ye.business.ad.enums;

/**
 * 
 * *类说明：文章类型
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月6日
 */
public enum ArticleType {

	HOT("热文"),

	MY("用户"),

	OTHER("其他");

	ArticleType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
