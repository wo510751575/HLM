package com.ye.business.hx.emus;

/**
 * 类说明：就诊状态
 * 
 * @Company: 扬恩科技有限公司
 * @author sjiying CreateDate: 2019年03月08日
 *
 */
public enum VistitingStatus {

	UNCONFIRMED("未确认"),

	TREATMENT("治疗中"),

	CANCEL("取消"),

	FINISHED("治疗完成");

	VistitingStatus(String name) {
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
