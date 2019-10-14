package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：客户添加方式
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月10日
 */
public enum PmAddType {
	
	/** 导购扫码添加 */
	GM_SCAN(1, "导购扫码添加"),
	
	/** 客户扫码添加 */
	PM_SCAN(2, "客户扫码添加"),
	
	/** 导购手动新增 */
	GM_ADD(3, "导购手动新增"),
	
	/** 微信自动导入 */
	WX_SYNC(4, "微信自动导入"),
	
	/** 手机号搜索 */
	MOBILE(5, "手机号搜索"),
	
	/** 微信号搜索 */
	WXNO(6, "微信号搜索"),
	
	/** QQ号搜索 */
	QQ(7, "QQ号搜索"),
	;
	
	private int type;		// 添加类型取值
	private String name;	// 添加类型名称

	PmAddType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
