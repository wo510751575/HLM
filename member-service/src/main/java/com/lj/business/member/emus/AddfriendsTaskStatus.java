package com.lj.business.member.emus;

/**
 * 加粉任务状态
 * @author wo510
 *1 已启动 2 已暂停 3 执行中 4 已完成
 */
public enum AddfriendsTaskStatus {
	
	/**
	 * 已启动
	 */
	START("1","已启动"),
	/**
	 * 已暂停
	 */
	STOP("2","已暂停"),
	/**
	 * 执行中
	 */
	EXECUTING("3","执行中"),
	/**
	 * 已完成
	 */
	SUCCESS("4","已完成"),
	;
	
	private String code;
	private String name;

	private AddfriendsTaskStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
