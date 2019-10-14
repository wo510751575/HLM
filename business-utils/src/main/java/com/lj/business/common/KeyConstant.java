
package com.lj.business.common;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：Map传值去除依赖时用,对一些常用key值进行封装
 * 
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 * 
 *         CreateDate: 2017年7月1日
 */
public class KeyConstant {

	/**
	 * 门店微信通讯录同步redis缓存锁前缀
	 */
	public static final String MEMBER_SYNC_CACHE_PREFIX = "MBRSYNC-";

	/**
	 * 客户认领redis缓存锁前缀
	 */
	public static final String MEMBER_CLAIM_CACHE_PREFIX = "MBRCLAIM-";

	/**
	 * 客户认领消息缓存前缀
	 */
	public static final String MEMBER_CLAIM_MSG_CACHE_PREFIX = "MBRCLAIMMSG-";

	/**
	 * 外部接口通过搜索添加好友前缀
	 */
	public static final String API_SEARCH_ADD_PREFIX = "APISEARCHADD-";

	/**
	 * OMS接口通过搜索添加好友前缀
	 */
	public static final String OMS_SEARCH_ADD_PREFIX = "OMSSEARCHADD-";

	/**
	 * OMS接口通过搜索添加好友,返回微信基本信息
	 */
	public static final String OMS_SEARCH_ADD_RET_PREFIX = "OMSSEARCHADDRET-";

	/**
	 * OMS好友列表中点击添加好友,add_friends表已存在记录
	 */
	public static final String OMS_FRIEND_LIST_SEARCH_ADD_PREFIX = "OMSFRIENDLISTSEARCHADDPREFIX-";

	/**
	 * OMS客户列表中点击添加好友,pmb表已存在记录
	 */
	public static final String OMS_PMB_LIST_SEARCH_ADD_PREFIX = "OMSPMBLISTSEARCHADDPREFIX-";

	/**
	 * 门店微信添加好友每日限制-缓存分组名称
	 */
	public static final String ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_CACHE_GROUP_NAME = "AF-DAYLIMITWX";

	/**
	 * 门店微信添加好友每日限制-参数KEY
	 */
	public static final String ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_KEY = "search_add_time";
	/**
	 * 优惠券群发计数和状态信息-参数KEY
	 */
	public static final String COUPON_MULTI_PUSH_KEY = "COUPON_MULTI_PUSH:";
	/**
	 * 优惠券分享-参数KEY
	 */
	public static final String COUPON_SHARE = "COUPON_SHARE:";
	/**
	 * 添加好友发红包-参数KEY
	 */
	public static final String RED_PACK_SEND = "RED_PACK_SEND:";
	/**
	 * 积分商城，注册登录验证码-参数KEY
	 */
	public static final String VERIFICATION_CODE = "VERIFICATION_CODE:";
	/**
	 * 积分商城，CRM系统登录TOKEN-参数KEY
	 */
	public static final String CRM_LOGIN_TOKEN = "CRM_LOGIN_TOKEN:";
	/**
	 * 积分商城，CRM系统登录TOKEN有效期-参数KEY
	 */
	public static final String CRM_LOGIN_TOKEN_VALID_DATE = "CRM_LOGIN_TOKEN_VALID_DATE:";
	/**
	 * 终端朋友圈自动下载图片-参数KEY
	 */
	public static final String ZK_FRIENDS_AUTO = "ZK_FRIENDS_AUTO:";
	/**
	 * 导购删除朋友圈记录操作人
	 */
	public static final String DEL_FRIENDS_OPERATOR = "DEL_FRIENDS_OPERATOR:";
}
