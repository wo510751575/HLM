package com.lj.business.api.common;

import com.lj.business.member.constant.PublicConstants;

/**
 * 
 * 
 * 类说明：常量类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class Constans {
	
	public static final int LIMIT = 100000;
	
	/**
	 * 头像访问地址
	 */
	public static final String HEAD_IMG_ADDR = PublicConstants.HEAD_IMG_ADDR;
	/**
	 * 头像存储路径
	 */
	public static final String HEAD_IMG_PATH = PublicConstants.HEAD_IMG_PATH;
	/**
	 * 头像组
	 */
	public static final String HEAD_IMG = PublicConstants.HEAD_IMG;
	/**
	 * 维修物品访问地址
	 */
	public static final String REPAIR_IMG_ADDR = "repairAddr";
	/**
	 * 维修物品存储路径
	 */
	public static final String REPAIR_IMG_PATH = "repairPath";

	public static final String REPAIR_IMG = "repairImg";
	/**
	 * 放行物品访问地址
	 */
	public static final String RELEASE_IMG_ADDR = "releasepassAddr";
	/**
	 * 放行物品存储路径
	 */
	public static final String RELEASE_IMG_PATH = "releasepassPath";

	public static final String RELEASE_IMG = "releasepassImg";
	/**
	 * 话费充值
	 */
	public static final String RECHARGE_DEALER_NO = "RechargeDealerNo";
	/**
	 * 话费充值收银台回调地址
	 */
	public static final String RESULTURL = "resultUrl";
	/**
	 * 话费充值
	 */
	public static final String MOBILEFEE = "mobileFee";
	/**
	 * 放行条已申请
	 */
	public static final byte RELEASE_APPLY =1 ;
	/**
	 * 维修已申请
	 */
	public static final byte REPAIR_APPLY =1 ;
	
	
}
