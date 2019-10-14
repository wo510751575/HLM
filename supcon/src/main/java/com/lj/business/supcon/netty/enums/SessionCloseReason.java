package com.lj.business.supcon.netty.enums;

/**
 * 
 * 
 * 类说明：会话关闭原因
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月9日
 */
public enum SessionCloseReason {
	
	/** 正常退出 */
	NORMAL,
	
	/** 异地登录 */
	OFFSITE,
	
	/** 链接超时 */
	OVER_TIME,
	
	/**强制关闭 */
	FORCED,
	
	/** 异常 */
	EXCEPTION,
	

}
