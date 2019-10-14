/**
 * 
 */
package com.ye.business.hx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;

/**
 * 
 * 
 * 类说明：订单号生成规格
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月13日
 */
public class OrderNoGenUtil {
	private static final String prefix = "HLM";
	/**
	 * 锁对象，可以为任意对象
	 */
	private static  Object lockObj = "lockerOrder";
	/**
	 * 订单号生成计数器
	 */
	private static long orderNumCount = 1L;
	/**
	 * 每毫秒生成订单号数量最大值
	 */
	private static int maxPerMSECSize = 1000;

	public static synchronized String getOrderNo() {
		try {
			// 最终生成的订单号
			String finOrderNum = "";
			synchronized (lockObj) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				if (orderNumCount >= maxPerMSECSize) {
					orderNumCount = 0L;
				}
				// 组装订单号
				String countStr =(maxPerMSECSize + orderNumCount)+"";
				finOrderNum = prefix+ nowLong + countStr.substring(1);
				orderNumCount++;
				
			}
			return finOrderNum;
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.SHOP_ORDER_ADD_ERROR,"订单号获取错误");
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(OrderNoGenUtil.getOrderNo());
		System.out.println(OrderNoGenUtil.getOrderNo());
	}
}
