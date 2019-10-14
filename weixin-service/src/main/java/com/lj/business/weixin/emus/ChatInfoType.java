/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

package com.lj.business.weixin.emus;

/**
 * 
 * 
 * 类说明：聊天记录类型枚举
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月20日
 */
public enum ChatInfoType {
	
	/** 文本 */
	TEXT("1","文本"),
	/** 图片 */
	IMG("3","图片"),
	/** 语音. */
	VOICE("34","语音"),
	/** 微信名片. */
	CARD("42","微信名片"),
	/** 视频. */
	VIDEO("43","视频"),
	/** 图片表情. */
	IMGBROW("47","图片表情"),
	/** 位置. */
	LOCATION("48","位置"),
	/** 分享. */
	SHARE("49","分享"),
	/** 视频聊天. */
	VIDEOCHAT("50","视频聊天"),
	/** 系统信息. */
	SYSTEM("10000","系统信息"),
	/** 对方已收到红包. */
	R_REDPACKET("10001","对方已收到红包"),
	/** 中控已收到红包. */
	S_GETREDPACKET("10007","中控收到红包"),
	/** 中控已收到红包. */
	S_GETTRANSFER("10008","中控收到转账"),
	/** 对方已收取红包. */
	R_GETREDPACKET("10006","对方（客户）已收取红包"),
	/** 收取（客户）对方红包. */
	L_REDPACKET("10002","已收取客户红包"),
	/** 转账. */
	L_TRANSFER("10003","收取客户转账"),
	/** 对方（客户）已收取转账. */
	R_TRANSFER("10005","客户已收取转账"),
	
	/** 系统安全提示. */
	SYSTEMSECURITY("318767153","系统安全提示"),
	/** 发红包. */
	S_REDPACKET("436207665","发红包"),
	/** 转账. */
	TRANSFER("419430449","转账"),

	/**
	 * 以下类型是平台为支持业务新增的类型：
	 * 1、49开头都以微信分享类型发送出去
	 */
	/** 素材分享. */
	MATERIAL_SHARE("490","素材"),
	/** 优惠券分享. */
	COUPON_SHARE("491","优惠券"),
	/** 活动分享. */
	ACTIVITY_SHARE("492","活动"),
	/** 导购名片分享. */
	CARTE_SHARE("493","导购名片"),
	/** 小程序. */
	SMALL_PROGRAM("494","小程序"),
	;
	
//	public static final int TYPE_VERIFY_PASS = -1000;////首次加好友  对方通过好友验证
//	public static final int TYPE_VERIFY_BE_PASSED = -1001;////首次加好友  对方通过好友验证

	ChatInfoType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	private String name;
	private String code;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
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
